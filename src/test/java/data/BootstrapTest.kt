package data

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BootstrapTest {
    @Test
    fun `test than can load bootstrap`() {
        val unqflix = getUNQFlix()
        assertEquals(0, unqflix.users.size)
        assertNotEquals(0, unqflix.movies.size)
        assertNotEquals(0, unqflix.series.size)
        assertNotEquals(0, unqflix.banners.size)
        assertNotEquals(0, unqflix.categories.size)
    }
}
