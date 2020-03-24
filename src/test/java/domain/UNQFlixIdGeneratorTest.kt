package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixIdGeneratorTest {
    @Test
    fun getNewUserId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.getNewUserId(), "u_0")
        assertEquals(unQflix.getNewUserId(), "u_1")
        assertEquals(unQflix.getNewUserId(), "u_2")
        assertEquals(unQflix.getNewUserId(), "u_3")
    }

    @Test
    fun getNewMovieId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.getNewMovieId(), "mov_0")
        assertEquals(unQflix.getNewMovieId(), "mov_1")
        assertEquals(unQflix.getNewMovieId(), "mov_2")
        assertEquals(unQflix.getNewMovieId(), "mov_3")
    }

    @Test
    fun getNewSerieId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.getNewSerieId(), "ser_0")
        assertEquals(unQflix.getNewSerieId(), "ser_1")
        assertEquals(unQflix.getNewSerieId(), "ser_2")
        assertEquals(unQflix.getNewSerieId(), "ser_3")
    }

    @Test
    fun getNewSeasonId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.getNewSeasonId(), "sea_0")
        assertEquals(unQflix.getNewSeasonId(), "sea_1")
        assertEquals(unQflix.getNewSeasonId(), "sea_2")
        assertEquals(unQflix.getNewSeasonId(), "sea_3")
    }

    @Test
    fun getNewChapterId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.getNewChapterId(), "cha_0")
        assertEquals(unQflix.getNewChapterId(), "cha_1")
        assertEquals(unQflix.getNewChapterId(), "cha_2")
        assertEquals(unQflix.getNewChapterId(), "cha_3")
    }
}
