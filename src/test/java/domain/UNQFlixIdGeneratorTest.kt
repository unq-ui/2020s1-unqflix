package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixIdGeneratorTest {
    @Test
    fun getNewUserId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.getNewUserId(), "u_0")
        assertEquals(unqflix.getNewUserId(), "u_1")
        assertEquals(unqflix.getNewUserId(), "u_2")
        assertEquals(unqflix.getNewUserId(), "u_3")
    }

    @Test
    fun getNewMovieId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.getNewMovieId(), "mov_0")
        assertEquals(unqflix.getNewMovieId(), "mov_1")
        assertEquals(unqflix.getNewMovieId(), "mov_2")
        assertEquals(unqflix.getNewMovieId(), "mov_3")
    }

    @Test
    fun getNewSerieId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.getNewSerieId(), "ser_0")
        assertEquals(unqflix.getNewSerieId(), "ser_1")
        assertEquals(unqflix.getNewSerieId(), "ser_2")
        assertEquals(unqflix.getNewSerieId(), "ser_3")
    }

    @Test
    fun getNewSeasonId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.getNewSeasonId(), "sea_0")
        assertEquals(unqflix.getNewSeasonId(), "sea_1")
        assertEquals(unqflix.getNewSeasonId(), "sea_2")
        assertEquals(unqflix.getNewSeasonId(), "sea_3")
    }

    @Test
    fun getNewChapterId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.getNewChapterId(), "cha_0")
        assertEquals(unqflix.getNewChapterId(), "cha_1")
        assertEquals(unqflix.getNewChapterId(), "cha_2")
        assertEquals(unqflix.getNewChapterId(), "cha_3")
    }
}
