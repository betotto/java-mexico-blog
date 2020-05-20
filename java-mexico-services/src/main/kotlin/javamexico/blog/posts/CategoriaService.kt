package javamexico.blog.posts

import javamexico.blog.utils.GsonParser
import javamexico.blog.utils.SessionHandling
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/categorias")
class CategoriaService {

    @Inject
    private lateinit var session: SessionHandling

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todas")
    fun getAllQuestionsApi(): Response {
        val listaCategorias = CategoriaDao.getAllCategorias()
        return if(listaCategorias.isEmpty()) {
            Response.status(404).build()
        } else {
            Response.status(200).entity(GsonParser.localGson.toJson(listaCategorias)).build()
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addCache/{key}/{value}")
    fun addCache(@PathParam("key") key: String, @PathParam("value") value: String): Response {
        session.setString(Pair(key, value))
        return Response.status(200).entity(GsonParser.localGson.toJson(Pair(key, value))).build()
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getCache/{key}")
    fun getChache(@PathParam("key") key: String): Response {
        return Response.status(200).entity(session.getString(key)).build()
    }
}