package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Assertions.*

class IdGeneratorTest {
    companion object {
        @BeforeAll
        @JvmStatic
        internal fun initAll() {
            IdGenerator.resetAll()
        }
    }

    @Test
    fun userIdTest() {
        assertEquals(0, IdGenerator.currentUserId)
        assertEquals("u_1", IdGenerator.nextUserId())
        assertEquals("u_2", IdGenerator.nextUserId())
        assertEquals("u_3", IdGenerator.nextUserId())
        assertEquals("u_4", IdGenerator.nextUserId())
    }

    @Test
    fun movieIdTest() {
        assertEquals(0, IdGenerator.currentMovieId)
        assertEquals("mov_1", IdGenerator.nextMovieId())
        assertEquals("mov_2", IdGenerator.nextMovieId())
        assertEquals("mov_3", IdGenerator.nextMovieId())
        assertEquals("mov_4", IdGenerator.nextMovieId())
    }

    @Test
    fun serieIdTest() {
        assertEquals(0, IdGenerator.currentSerieId)
        assertEquals("ser_1", IdGenerator.nextSerieId())
        assertEquals("ser_2", IdGenerator.nextSerieId())
        assertEquals("ser_3", IdGenerator.nextSerieId())
        assertEquals("ser_4", IdGenerator.nextSerieId())
    }

    @Test
    fun seasonIdTest() {
        assertEquals(0, IdGenerator.currentSeasonId)
        assertEquals("sea_1", IdGenerator.nextSeasonId())
        assertEquals("sea_2", IdGenerator.nextSeasonId())
        assertEquals("sea_3", IdGenerator.nextSeasonId())
        assertEquals("sea_4", IdGenerator.nextSeasonId())
    }

    @Test
    fun chapterId() {
        assertEquals(0, IdGenerator.currentChapterId)
        assertEquals("cha_1", IdGenerator.nextChapterId())
        assertEquals("cha_2", IdGenerator.nextChapterId())
        assertEquals("cha_3", IdGenerator.nextChapterId())
        assertEquals("cha_4", IdGenerator.nextChapterId())
    }
}
