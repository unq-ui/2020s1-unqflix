package domain

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Assertions.*

class UNQFlixUsersTest {
    @Test
    fun addUser() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)
        assertEquals(unQflix.users.size, 1)
        assertEquals(unQflix.users.first().email, user.email)
    }

    @Test
    fun addUserTwoTimesSameUser() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)
        assertFailsWith<ExistException> { unQflix.addUser(user) }
    }
}