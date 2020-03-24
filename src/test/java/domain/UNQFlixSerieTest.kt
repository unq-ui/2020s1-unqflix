package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

class UNQFlixSerieTest {
    @Test
    fun addSerie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().title, serie.title)
    }

    @Test
    fun addSerieTwoTimesSameSerie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)
        assertThrows<ExistException> { unQflix.addSerie(serie.copy()) }
    }

    @Test
    fun addSeason() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unQflix.addSeason(serie.id, season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
    }

    @Test
    fun addSeasonWithoutSeries() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val season = SeasonBuilder.any()
        assertThrows<NotFoundException> { unQflix.addSeason("ser_1", season) }
    }

    @Test
    fun addChapter() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unQflix.addSeason(serie.id, season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)

        val chapter = ChapterBuilder.any()
        unQflix.addChapter(serie.id, season.id, chapter)

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unQflix.series.first().seasons.first().chapters.first().title, chapter.title)
    }

    @Test
    fun addChapterWithoutSeries() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val chapter = ChapterBuilder.any()
        assertThrows<NotFoundException> { unQflix.addChapter("ser_1", "sea_0", chapter) }
    }

    @Test
    fun deleteSerie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().title, serie.title)

        unQflix.deleteSerie(serie.id)
        assertEquals(unQflix.series.size, 0)
    }

    @Test
    fun deleteSerieNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
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

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unQflix.addSeason(serie.id, season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)

        unQflix.deleteSeason(serie.id, season.id)
        assertEquals(unQflix.series.first().seasons.size, 0)
    }

    @Test
    fun deleteSeasonNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.with(id = "sea_0")
        unQflix.addSeason(serie.id, season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)

        unQflix.deleteSeason(serie.id, "sea_1")
        assertEquals(unQflix.series.first().seasons.size, 1)
    }

    @Test
    fun deleteChapter() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.any()
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unQflix.addSeason(serie.id, season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)

        val chapter = ChapterBuilder.any()
        unQflix.addChapter(serie.id, season.id, chapter)

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unQflix.series.first().seasons.first().chapters.first().title, chapter.title)

        unQflix.deleteChapter(serie.id, season.id, chapter.id)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)
    }

    @Test
    fun deleteChapterNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)
        assertEquals(unQflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.with(id = "sea_0")
        unQflix.addSeason(serie.id, season)

        assertEquals(unQflix.series.first().seasons.size, 1)
        assertEquals(unQflix.series.first().seasons.first().title, season.title)
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 0)

        val chapter = ChapterBuilder.with(id = "cha_0")
        unQflix.addChapter(serie.id, season.id, chapter)

        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unQflix.series.first().seasons.first().chapters.first().title, chapter.title)

        unQflix.deleteChapter("ser_1", "sea_0", "cha_1")
        assertEquals(unQflix.series.first().seasons.first().chapters.size, 1)
    }
}
