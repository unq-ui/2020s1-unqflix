package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

class UNQFlixSerieTest {
    @Test
    fun addSerie() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().title, serie.title)
    }

    @Test
    fun addSerieTwoTimesSameSerie() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)
        assertThrows<ExistsException> { unqflix.addSerie(serie.copy()) }
    }

    @Test
    fun addSeason() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unqflix.addSeason(serie.id, season)

        assertEquals(unqflix.series.first().seasons.size, 1)
        assertEquals(unqflix.series.first().seasons.first().title, season.title)
    }

    @Test
    fun addSeasonWithoutSeries() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val season = SeasonBuilder.any()
        assertThrows<NotFoundException> { unqflix.addSeason("ser_1", season) }
    }

    @Test
    fun addChapter() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unqflix.addSeason(serie.id, season)

        assertEquals(unqflix.series.first().seasons.size, 1)
        assertEquals(unqflix.series.first().seasons.first().title, season.title)
        assertEquals(unqflix.series.first().seasons.first().chapters.size, 0)

        val chapter = ChapterBuilder.any()
        unqflix.addChapter(serie.id, season.id, chapter)

        assertEquals(unqflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unqflix.series.first().seasons.first().chapters.first().title, chapter.title)
    }

    @Test
    fun addChapterWithoutSeries() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val chapter = ChapterBuilder.any()
        assertThrows<NotFoundException> { unqflix.addChapter("ser_1", "sea_0", chapter) }
    }

    @Test
    fun deleteSerie() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().title, serie.title)

        unqflix.deleteSerie(serie.id)
        assertEquals(unqflix.series.size, 0)
    }

    @Test
    fun deleteSerieNotFoundId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().title, serie.title)

        unqflix.deleteSerie("ser_2")
        assertEquals(unqflix.series.size, 1)
    }

    @Test
    fun deleteSeason() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unqflix.addSeason(serie.id, season)

        assertEquals(unqflix.series.first().seasons.size, 1)
        assertEquals(unqflix.series.first().seasons.first().title, season.title)

        unqflix.deleteSeason(serie.id, season.id)
        assertEquals(unqflix.series.first().seasons.size, 0)
    }

    @Test
    fun deleteSeasonNotFoundId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.with(id = "sea_0")
        unqflix.addSeason(serie.id, season)

        assertEquals(unqflix.series.first().seasons.size, 1)
        assertEquals(unqflix.series.first().seasons.first().title, season.title)

        unqflix.deleteSeason(serie.id, "sea_1")
        assertEquals(unqflix.series.first().seasons.size, 1)
    }

    @Test
    fun deleteChapter() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.any()
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.any()
        unqflix.addSeason(serie.id, season)

        assertEquals(unqflix.series.first().seasons.size, 1)
        assertEquals(unqflix.series.first().seasons.first().title, season.title)
        assertEquals(unqflix.series.first().seasons.first().chapters.size, 0)

        val chapter = ChapterBuilder.any()
        unqflix.addChapter(serie.id, season.id, chapter)

        assertEquals(unqflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unqflix.series.first().seasons.first().chapters.first().title, chapter.title)

        unqflix.deleteChapter(serie.id, season.id, chapter.id)
        assertEquals(unqflix.series.first().seasons.first().chapters.size, 0)
    }

    @Test
    fun deleteChapterNotFoundId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie = SerieBuilder.with(id = "ser_1")
        unqflix.addSerie(serie)

        assertEquals(unqflix.series.size, 1)
        assertEquals(unqflix.series.first().seasons.size, 0)

        val season = SeasonBuilder.with(id = "sea_0")
        unqflix.addSeason(serie.id, season)

        assertEquals(unqflix.series.first().seasons.size, 1)
        assertEquals(unqflix.series.first().seasons.first().title, season.title)
        assertEquals(unqflix.series.first().seasons.first().chapters.size, 0)

        val chapter = ChapterBuilder.with(id = "cha_0")
        unqflix.addChapter(serie.id, season.id, chapter)

        assertEquals(unqflix.series.first().seasons.first().chapters.size, 1)
        assertEquals(unqflix.series.first().seasons.first().chapters.first().title, chapter.title)

        unqflix.deleteChapter("ser_1", "sea_0", "cha_1")
        assertEquals(unqflix.series.first().seasons.first().chapters.size, 1)
    }
}
