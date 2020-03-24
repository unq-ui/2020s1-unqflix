package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertFailsWith

class UNQFlixCategoryTest {
    @Test
    fun addCategory() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.categories.size, 0)

        val category = Category("Terror")
        unQflix.addCategory(category)

        assertEquals(unQflix.categories.size, 1)
        assertEquals(unQflix.categories.first().name, category.name)
    }

    @Test
    fun addCategoryTwoTimesSameCategory() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.categories.size, 0)

        val category = Category("Terror")
        unQflix.addCategory(category)
        assertFailsWith<ExistException> { unQflix.addCategory(category) }
    }
}