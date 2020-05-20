package javamexico.blog.usuarios

import javamexico.blog.utils.DataSource
import javamexico.blog.utils.GsonParser
import org.simpleflatmapper.jdbc.JdbcMapperFactory
import java.sql.ResultSet
import java.util.*
import kotlin.streams.toList

object UsuarioDao {
    private const val queryAllUsuarios = "SELECT idusuario, metadata, apodo, email, nombre, apellidos FROM usuarios"

    val UsuarioMapperMetaData = { rs: ResultSet, i: Int ->
        val json: String? = rs.getString(i)
        Log.debug(Log.Modulo.USUARIOS, "Metadata Usuario: $json")
        if (json != null) {
            GsonParser.localGson.fromJson(json, UsuarioMetaData::class.java)
        } else {
            UsuarioMetaData()
        }
    }

    data class UsuarioMetaData(val creado: Date = Date(), val activo: Boolean = false)

    data class Usuario(val idUsuario: Int = -1,
                    val metadata: UsuarioMetaData,
                    val apodo: String,
                    val email: String,
                    val nombre: String = "",
                    val apellidos: String = "")

    private var mapper = JdbcMapperFactory
        .newInstance()
        .addKeys("idusuario")
        .fieldMapperErrorHandler { key, source, target, error, mappingContext ->
            Log.error(Log.Modulo.USUARIOS,"Error al $error, $key, $source, $target")
        }
        .addGetterForType(UsuarioMetaData::class.java, UsuarioMapperMetaData)
        .newMapper(Usuario::class.java)

    val findAllUsuarios = {
        Log.debug(Log.Modulo.USUARIOS, "Obteniendo posts")
        Log.debug(Log.Modulo.USUARIOS, "Query: $queryAllUsuarios")

        DataSource.mysql.connection.use { con ->
            con.prepareStatement(queryAllUsuarios).use { ps ->
                ps.executeQuery().use { mapper.stream(it).toList() }
            }
        }
    }
}