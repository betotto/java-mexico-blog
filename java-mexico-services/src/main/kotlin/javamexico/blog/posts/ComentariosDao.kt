package javamexico.blog.posts

import javamexico.blog.usuarios.UsuarioDao
import javamexico.blog.utils.DataSource
import javamexico.blog.utils.GsonParser
import java.sql.ResultSet
import java.sql.Statement
import java.util.*
object ComentariosDao {

    private const val addComentarioQuery = "INSERT INTO comentarios (metadata, contenido, creador, post) VALUES (?, ?, ?, ?)"

    val ComentarioMapperMetaData = { rs: ResultSet, i: Int ->
        val json: String? = rs.getString(i)
        Log.debug(Log.Modulo.POSTS, "Metadata Comentario: $json")
        if (json != null) {
            GsonParser.localGson.fromJson(json, ComentarioMetaData::class.java)
        } else {
            ComentarioMetaData()
        }
    }

    data class ComentarioMetaData(val creado: Date = Date(), val votos: Int = 0)

    data class Comentario(val idComentario: Int = -1,
                    val metadata: ComentarioMetaData,
                    val contenido: String,
                    val creador: UsuarioDao.Usuario)

    val addComentario = { comentario: Comentario, post: Int, usuarioCreador: Int ->
        Log.debug(Log.Modulo.POSTS, "Usuario $usuarioCreador agregando comentario al post $post")

        DataSource.mysql.connection.use { con ->
            con.prepareStatement(addComentarioQuery, Statement.RETURN_GENERATED_KEYS).use { ps ->
                ps.setString(1, GsonParser.localGson.toJson(comentario.metadata))
                ps.setString(2, comentario.contenido)
                ps.setInt(3, usuarioCreador)
                ps.setInt(4, post)
                ps.execute()
                ps.generatedKeys.use { generatedKeys ->
                    generatedKeys.next()
                    comentario.copy(idComentario = generatedKeys.getInt(1))
                }
            }
        }
    }
}