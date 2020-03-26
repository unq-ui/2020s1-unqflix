package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class UNQFlixUsersTest {
    @Test
    fun addUser() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.users.size, 0)

        val user = UserBuilder.any()
        unqflix.addUser(user)
        assertEquals(unqflix.users.size, 1)
        assertEquals(unqflix.users.first().email, user.email)
    }

    @Test
    fun addUserTwoTimesSameUser() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.users.size, 0)

        val user = UserBuilder.any()
        unqflix.addUser(user)
        assertThrows<ExistsException> { unqflix.addUser(user) }
    }
}
