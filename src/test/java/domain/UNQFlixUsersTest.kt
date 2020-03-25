package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

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
        assertThrows<ExistsException> { unQflix.addUser(user) }
    }
}
