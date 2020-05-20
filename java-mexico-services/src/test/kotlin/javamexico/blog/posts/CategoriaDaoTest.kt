package javamexico.blog.posts

import org.junit.jupiter.api.Test
import kotlin.test.AfterTest
import kotlin.test.assertEquals

class CategoriaDaoTest {

    private val categoriasEnBase = listOf(CategoriaDao.Categoria(1, "DESARROLLO"),
            CategoriaDao.Categoria(2, "TEST"))

    @Test fun `Obtener todas las categorias`() {
        val categorias = CategoriaDao.getAllCategorias()

        assertEquals(categorias.size, 2)
        assertEquals(categorias, categoriasEnBase)
    }

}

