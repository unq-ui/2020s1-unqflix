package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class UNQFlixCategoryTest {
    @Test
    fun addCategory() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.categories.size, 0)

        val category = Category("1", "Terror")
        unqflix.addCategory(category)

        assertEquals(unqflix.categories.size, 1)
        assertEquals(unqflix.categories.first().name, category.name)
    }

    @Test
    fun addCategoryTwoTimesSameCategory() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.categories.size, 0)

        val category = Category("1", "Terror")
        unqflix.addCategory(category)
        assertThrows<ExistsException> { unqflix.addCategory(category) }
    }
}
