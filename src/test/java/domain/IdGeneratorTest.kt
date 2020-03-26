package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class IdGeneratorTest {
    private val idGenerator = IdGenerator()

    @Test
    fun userIdTest() {
        assertEquals(0, idGenerator.currentUserId)
        assertEquals("u_1", idGenerator.nextUserId())
        assertEquals("u_2", idGenerator.nextUserId())
        assertEquals("u_3", idGenerator.nextUserId())
        assertEquals("u_4", idGenerator.nextUserId())
    }

    @Test
    fun movieIdTest() {
        assertEquals(0, idGenerator.currentMovieId)
        assertEquals("mov_1", idGenerator.nextMovieId())
        assertEquals("mov_2", idGenerator.nextMovieId())
        assertEquals("mov_3", idGenerator.nextMovieId())
        assertEquals("mov_4", idGenerator.nextMovieId())
    }

    @Test
    fun serieIdTest() {
        assertEquals(0, idGenerator.currentSerieId)
        assertEquals("ser_1", idGenerator.nextSerieId())
        assertEquals("ser_2", idGenerator.nextSerieId())
        assertEquals("ser_3", idGenerator.nextSerieId())
        assertEquals("ser_4", idGenerator.nextSerieId())
    }

    @Test
    fun seasonIdTest() {
        assertEquals(0, idGenerator.currentSeasonId)
        assertEquals("sea_1", idGenerator.nextSeasonId())
        assertEquals("sea_2", idGenerator.nextSeasonId())
        assertEquals("sea_3", idGenerator.nextSeasonId())
        assertEquals("sea_4", idGenerator.nextSeasonId())
    }

    @Test
    fun chapterId() {
        assertEquals(0, idGenerator.currentChapterId)
        assertEquals("cha_1", idGenerator.nextChapterId())
        assertEquals("cha_2", idGenerator.nextChapterId())
        assertEquals("cha_3", idGenerator.nextChapterId())
        assertEquals("cha_4", idGenerator.nextChapterId())
    }

    @Test
    fun categoryIdTest() {
        assertEquals(0, idGenerator.currentCategoryId)
        assertEquals("cat_1", idGenerator.nextCategoryId())
        assertEquals("cat_2", idGenerator.nextCategoryId())
        assertEquals("cat_3", idGenerator.nextCategoryId())
        assertEquals("cat_4", idGenerator.nextCategoryId())
    }
}
