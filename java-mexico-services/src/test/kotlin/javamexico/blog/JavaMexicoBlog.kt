package javamexico.blog

import javamexico.blog.posts.CategoriaService
import javamexico.blog.utils.SessionHandling
import java.util.*
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
class JavaMexicoBlog: Application() {
    private val singletons = HashSet<Any>()
    private val sessionHandling = SessionHandling()

    init {
        val categoriaService = CategoriaService()
        val session = CategoriaService::class.java.getDeclaredField("session")

        session.isAccessible = true
        session.set(categoriaService, sessionHandling)
        singletons.add(categoriaService)
    }

    override fun getSingletons(): Set<Any> {
        return singletons
    }
}