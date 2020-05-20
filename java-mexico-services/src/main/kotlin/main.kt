import javamexico.blog.posts.CategoriaDao
import javamexico.blog.posts.ComentariosDao
import javamexico.blog.usuarios.UsuarioDao
import java.util.*

fun main() {
    val meta = ComentariosDao.ComentarioMetaData(Date(), 100)
    val metau = UsuarioDao.UsuarioMetaData(Date(), false)
    val comentario = ComentariosDao.Comentario(metadata = meta, contenido = "Este es un comentario desde kotlin",
            creador = UsuarioDao.Usuario(idUsuario = 1, metadata = metau, apodo = "apodo", email = "email"));

    println(CategoriaDao.getAllCategorias())
}