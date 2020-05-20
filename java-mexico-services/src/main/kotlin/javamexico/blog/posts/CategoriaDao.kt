package javamexico.blog.posts

import javamexico.blog.utils.DataSource
import org.simpleflatmapper.jdbc.JdbcMapperFactory
import kotlin.streams.toList

object CategoriaDao {

    private const val getAllCategoriasQuery = "SELECT idcategoria, nombre FROM categorias"

    data class Categoria(val idCategoria: Int = -1,
                       val nombre: String)

    private var mapperCategoria = JdbcMapperFactory
            .newInstance()
            .addKeys("idcategoria")
            .fieldMapperErrorHandler {
                key, source, target, error, mappingContext ->
                Log.error(Log.Modulo.POSTS,"Error al consultar Categorias $error")
            }
            .newMapper(Categoria::class.java)

    val getAllCategorias: () -> List<Categoria> = {
        Log.debug(Log.Modulo.POSTS, "Obteniendo categorias")
        Log.debug(Log.Modulo.POSTS, "Query: $getAllCategoriasQuery")

        DataSource.mysql.connection.use { con ->
            con.prepareStatement(getAllCategoriasQuery).use { ps ->
                ps.executeQuery().use { rs -> mapperCategoria.stream(rs).toList() }
            }
        }
    }
}