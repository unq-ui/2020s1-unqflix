package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixUserBehaviorTest {
    @Test
    fun addLastSeen() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.users.size, 0)
        assertEquals(unqflix.series.size, 0)

        val user = UserBuilder.any()
        unqflix.addUser(user)

        assertEquals(unqflix.users.size, 1)
        assertEquals(user.lastSeen.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unqflix.addSerie(serie)
        assertEquals(unqflix.series.size, 1)

        unqflix.addLastSeen("1", "ser_1")
        assertEquals(user.lastSeen.size, 1)
    }

    @Test
    fun addLastSeenTwoTimes() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.users.size, 0)
        assertEquals(unqflix.series.size, 0)

        val user = UserBuilder.any()
        unqflix.addUser(user)

        assertEquals(unqflix.users.size, 1)
        assertEquals(user.lastSeen.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unqflix.addSerie(serie)
        assertEquals(unqflix.series.size, 1)

        unqflix.addLastSeen("1", "ser_1")
        assertEquals(user.lastSeen.size, 1)

        unqflix.addLastSeen("1", "ser_1")
        assertEquals(user.lastSeen.size, 1)
    }

    @Test
    fun addOrDeleteFav() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.users.size, 0)
        assertEquals(unqflix.series.size, 0)

        val user = UserBuilder.any()
        unqflix.addUser(user)

        assertEquals(unqflix.users.size, 1)
        assertEquals(user.favorites.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unqflix.addSerie(serie)
        assertEquals(unqflix.series.size, 1)

        unqflix.addOrDeleteFav("1", "ser_1")
        assertEquals(user.favorites.size, 1)

        unqflix.addOrDeleteFav("1", "ser_1")
        assertEquals(user.favorites.size, 0)
    }
}
