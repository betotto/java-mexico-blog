import javamexico.blog.posts.ComentariosDao
import javamexico.blog.usuarios.UsuariosDao
import java.util.*

fun main() {
    val meta = ComentariosDao.ComentarioMetaData(Date(), 100)
    val metau = UsuariosDao.UsuarioMetaData(Date(), false)
    val comentario = ComentariosDao.Comentario(metadata = meta, contenido = "Este es un comentario desde kotlin",
            creador = UsuariosDao.Usuario(idUsuario = 1, metadata = metau, apodo = "apodo", email = "email"));

    println(ComentariosDao.addComentario(comentario, 2, comentario.creador.idUsuario))
}