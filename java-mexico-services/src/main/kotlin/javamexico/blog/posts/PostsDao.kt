package javamexico.blog.posts

import javamexico.blog.usuarios.UsuariosDao
import javamexico.blog.utils.DataSource
import javamexico.blog.utils.GsonParser
import org.simpleflatmapper.jdbc.JdbcMapperFactory
import java.sql.ResultSet
import java.util.*
import kotlin.streams.toList


object PostsDao {

    private const val queryPostDetail = "SELECT p.idpost, p.metadata, p.titulo, p.contenido, u.idusuario as creador_idusuario, cat.idcategorias as categoria_idcategoria, cat.nombre as categoria_nombre, " +
            "u.metadata as creador_metadata, u.apodo as creador_apodo, u.email as creador_email, u.nombre as creador_nombre, u.apellidos as creador_apellidos," +
            "c.idcomentario as comentarios_idcomentario_, c.metadata as comentarios_metadata, c.contenido as comentarios_contenido, " +
            "u.idusuario as comentarios_creador_idusuario, u.metadata as comentarios_creador_metadata, u.apodo as comentarios_creador_apodo, u.email as comentarios_creador_email, " +
            "u.nombre as comentarios_creador_nombre, u.apellidos as comentarios_creador_apellidos " +
            "FROM posts p LEFT JOIN usuarios u ON p.creador = u.idusuario " +
            "LEFT JOIN categorias cat ON p.categoria = cat.idcategorias " +
            "LEFT JOIN comentarios c ON c.post = p.idpost " +
            "LEFT JOIN usuarios uc on c.creador = uc.idusuario"

    val PostsMapperMetaData = { rs: ResultSet, i: Int ->
        val json: String? = rs.getString(i)
        Log.debug(Log.Modulo.POSTS, "Metadata: $json")
        if (json != null) {
            GsonParser.localGson.fromJson(json, PostMetaData::class.java)
        } else {
            PostMetaData()
        }
    }

    data class PostMetaData(val creado: Date = Date(), val lecturas: Int = 0)

    data class Post(val idPost: Int = -1,
                    val metadata: PostMetaData,
                    val categoria: CategoriaDao.Categoria,
                    val titulo: String,
                    val contenido: String,
                    val creador: UsuariosDao.Usuario,
                    val comentarios: List<ComentariosDao.Comentario>)

    private var mapperWithUsuario = JdbcMapperFactory
        .newInstance()
        .addKeys("idpost")
        .fieldMapperErrorHandler {
            key, source, target, error, mappingContext ->
            Log.error(Log.Modulo.POSTS,"Error al consultar Posts $error")
        }
            .addGetterForType(PostMetaData::class.java, PostsMapperMetaData)
            .addGetterForType(UsuariosDao.UsuarioMetaData::class.java, UsuariosDao.UsuarioMapperMetaData)
            .addGetterForType(ComentariosDao.ComentarioMetaData::class.java, ComentariosDao.ComentarioMapperMetaData)
            .newMapper(Post::class.java)

    fun findAllPostsWithUsuario(): List<Post> {
        Log.debug(Log.Modulo.POSTS, "Obteniendo posts")
        Log.debug(Log.Modulo.POSTS, "Query: $queryPostDetail")

        return DataSource.mysql.connection.use { con ->
            con.prepareStatement(queryPostDetail).use { ps ->
                ps.executeQuery().use { rs -> mapperWithUsuario.stream(rs).toList() }
            }
        }
    }
}