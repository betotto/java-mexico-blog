package javamexico.blog.posts

import com.google.gson.reflect.TypeToken
import io.undertow.Undertow
import javamexico.blog.JavaMexicoBlog
import javamexico.blog.utils.GsonParser
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.test.assertEquals

/**
 * POST /categorias/addCache/{key}/{value}
 * GET  /categorias/getCache/{key}
 * GET	/ategorias/todas
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoriaServiceTest {

    private val port = 9898
    private val host = "0.0.0.0"
    private lateinit var server: UndertowJaxrsServer

    private val categoriasEnBase = listOf(CategoriaDao.Categoria(1, "DESARROLLO"),
            CategoriaDao.Categoria(2, "TEST"))

    @BeforeAll
    fun init() {
        server = UndertowJaxrsServer()
        val serverBuilder = Undertow.builder().addHttpListener(port, host)
        server.start(serverBuilder)
        server.deploy(JavaMexicoBlog::class.java, "/")
    }

    @AfterAll
    fun end() {
        server.stop()
    }

    @Test
    fun `Obtener todas las categorias`() {

        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://$host:$port/categorias/todas"))
                .build()
        val body = client.send(request, HttpResponse.BodyHandlers.ofString()).body()
        val response = GsonParser.localGson.fromJson<List<CategoriaDao.Categoria>>(body, object: TypeToken<List<CategoriaDao.Categoria>>(){}.type)

        assertEquals(response.size, 2)
        response.forEachIndexed { i: Int, categoria: CategoriaDao.Categoria ->
            assertEquals(categoria, categoriasEnBase[i])
        }

        assertEquals(response, categoriasEnBase)
    }

    @Test
    fun `Guardar valor en cache`() {
        val key = "hola"
        val value = "perro"
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("http://$host:$port/categorias/addCache/$key/$value"))
                .build()
        val body = client.send(request, HttpResponse.BodyHandlers.ofString()).body()

        val response = GsonParser.localGson.fromJson<Pair<String, String>>(body, object: TypeToken<Pair<String, String>>(){}.type)
        assertEquals(response, Pair(key, value))
    }

    @Test
    fun `Obtener valor de cache`() {
        val key = "hola"
        val value = "perro"
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
                .uri(URI.create("http://$host:$port/categorias/getCache/$key"))
                .build()
        val body = client.send(request, HttpResponse.BodyHandlers.ofString()).body()

        assertEquals(body, value)
    }
}