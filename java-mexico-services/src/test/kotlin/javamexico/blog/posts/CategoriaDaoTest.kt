package javamexico.blog.posts

import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object MockObj {
    fun add(a: Int, b: Int) = a + b
}

@Test fun `first test`() {

    mockkObject(MockObj) // applies mocking to an Object

    Assertions.assertEquals(3, MockObj.add(1, 2))

    every { MockObj.add(1, 2) } returns 55

    Assertions.assertEquals(55, MockObj.add(1, 2))

}

