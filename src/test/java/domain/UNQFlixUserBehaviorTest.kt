package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixUserBehaviorTest {
    @Test
    fun addLastSeen() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)
        assertEquals(unQflix.series.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)

        assertEquals(unQflix.users.size, 1)
        assertEquals(user.lastSeen.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unQflix.addSerie(serie)
        assertEquals(unQflix.series.size, 1)

        unQflix.addLastSeen("1", "ser_1")
        assertEquals(user.lastSeen.size, 1)
    }

    @Test
    fun addLastSeenTwoTimes() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)
        assertEquals(unQflix.series.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)

        assertEquals(unQflix.users.size, 1)
        assertEquals(user.lastSeen.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unQflix.addSerie(serie)
        assertEquals(unQflix.series.size, 1)

        unQflix.addLastSeen("1", "ser_1")
        assertEquals(user.lastSeen.size, 1)

        unQflix.addLastSeen("1", "ser_1")
        assertEquals(user.lastSeen.size, 1)
    }

    @Test
    fun addOrDeleteFav() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)
        assertEquals(unQflix.series.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)

        assertEquals(unQflix.users.size, 1)
        assertEquals(user.favorites.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unQflix.addSerie(serie)
        assertEquals(unQflix.series.size, 1)

        unQflix.addOrDeleteFav("1", "ser_1")
        assertEquals(user.favorites.size, 1)

        unQflix.addOrDeleteFav("1", "ser_1")
        assertEquals(user.favorites.size, 0)
    }
}
