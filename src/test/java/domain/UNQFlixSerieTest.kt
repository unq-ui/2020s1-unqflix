package domain

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Assertions.*

class UNQFlixSerieTest {
    @Test
    fun addSerie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().title, serie.title)
    }

    @Test
    fun addSerieTwoTimesSameSerie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)
        assertFailsWith<ExistException> { unQflix.addSerie(serie) }
    }

    @Test
    fun addSeason() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        unQflix.addSeason("ser_1", season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
    }

    @Test
    fun addSeasonWithoutSeries() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        assertFailsWith<NotFoundException> {unQflix.addSeason("ser_1", season)}
    }

    @Test
    fun addChapter() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        unQflix.addSeason("ser_1", season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)

        val chapter = Chapter("cha_0", "chapter", "chapter", 60, "video", "thumbnail")
        unQflix.addChapter("ser_1", "sea_0", chapter)

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unQflix.series.first().seasons.first().chapters.first().title, chapter.title)
    }

    @Test
    fun addChapterWithoutSeries() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val chapter = Chapter("cha_0", "chapter", "chapter", 60, "video", "thumbnail")
        assertFailsWith<NotFoundException> {unQflix.addChapter("ser_1", "sea_0", chapter)}
    }

    @Test
    fun deleteSerie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().title, serie.title)

        unQflix.deleteSerie("ser_1")

        assertEquals(unQflix.series.size, 0)
    }

    @Test
    fun deleteSerieNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().title, serie.title)

        unQflix.deleteSerie("ser_2")

        assertEquals(unQflix.series.size, 1)
    }

    @Test
    fun deleteSeason() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        unQflix.addSeason("ser_1", season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)

        unQflix.deleteSeason("ser_1", "sea_0")

        assertEquals(unQflix.series.first().seasons.size, 0)
    }

    @Test
    fun deleteSeasonNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        unQflix.addSeason("ser_1", season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)

        unQflix.deleteSeason("ser_1", "sea_1")

        assertEquals(unQflix.series.first().seasons.size, 1)
    }

    @Test
    fun deleteChapter() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        unQflix.addSeason("ser_1", season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)

        val chapter = Chapter("cha_0", "chapter", "chapter", 60, "video", "thumbnail")
        unQflix.addChapter("ser_1", "sea_0", chapter)

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unQflix.series.first().seasons.first().chapters.first().title, chapter.title)

        unQflix.deleteChapter("ser_1", "sea_0", "cha_0")

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)
    }

    @Test
    fun deleteChapterNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = Season("sea_0", "sea0", "sea0", "sea0", mutableListOf())
        unQflix.addSeason("ser_1", season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)

        val chapter = Chapter("cha_0", "chapter", "chapter", 60, "video", "thumbnail")
        unQflix.addChapter("ser_1", "sea_0", chapter)

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unQflix.series.first().seasons.first().chapters.first().title, chapter.title)

        unQflix.deleteChapter("ser_1", "sea_0", "cha_1")

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
    }
}
