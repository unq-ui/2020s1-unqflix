package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

class IdTest {
    @Test
    fun `test equalsId`() {
        val id1 = object : Id { override val id: String = "1" }
        val id2 = object : Id { override val id: String = "1" }
        val id3 = object : Id { override val id: String = "2" }
        assertTrue(id1.equalsId(id2))
        assertFalse(id1.equalsId(id3))
    }

    @Test
    fun `dummy throw exception test`() {
        val id1 = object : Id { override val id: String = "1" }
        assertThrows<ExistsException> { throw id1.existsException() }
    }
}
