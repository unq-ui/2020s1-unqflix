package domain

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Assertions.*

class UNQFlixTest {
    @Test
    fun addUser() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)
        assertEquals(unQflix.users.size, 1)
        assertEquals(unQflix.users.first().email, user.email)
    }

    @Test
    fun addUserTwoTimesSameUser() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)
        assertFailsWith<ExistException> { unQflix.addUser(user) }
    }

    @Test
    fun addCategory() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.categories.size, 0)

        val category = Category("Terror")
        unQflix.addCategory(category)

        assertEquals(unQflix.categories.size, 1)
        assertEquals(unQflix.categories.first().name, category.name)
    }

    @Test
    fun addCategoryTwoTimesSameCategory() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.categories.size, 0)

        val category = Category("Terror")
        unQflix.addCategory(category)
        assertFailsWith<ExistException> { unQflix.addCategory(category) }
    }

    @Test
    fun addMovie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.movies.size, 0)

        val movie = Movie("mov_1", "mov1", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addMovie(movie)

        assertEquals(unQflix.movies.size, 1)
        assertEquals(unQflix.movies.first().title, movie.title)
    }

    @Test
    fun addMovieTwoTimesSameMovie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.movies.size, 0)

        val movie = Movie("mov_1", "mov1", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addMovie(movie)
        assertFailsWith<ExistException> { unQflix.addMovie(movie) }
    }

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
    fun addBanner() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie2 = Serie("ser_2", "ser2", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie3 = Serie("ser_3", "ser3", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie4 = Serie("ser_4", "ser4", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie5 = Serie("ser_5", "ser5", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie6 = Serie("ser_6", "ser6", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())

        unQflix.addBanner(serie)
        unQflix.addBanner(serie2)
        unQflix.addBanner(serie3)
        unQflix.addBanner(serie4)
        unQflix.addBanner(serie5)

        assertEquals(unQflix.banners.size, 5)

        unQflix.addBanner(serie6)

        assertEquals(unQflix.banners.size, 5)
        assertFalse(unQflix.banners.contains(serie))
    }

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

    @Test
    fun deleteMovie() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.movies.size, 0)

        val movie = Movie("mov_1", "mov1", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addMovie(movie)

        assertEquals(unQflix.movies.size, 1)
        assertEquals(unQflix.movies.first().title, movie.title)

        unQflix.deleteMovie("mov_1")
        assertEquals(unQflix.movies.size, 0)
    }

    @Test
    fun deleteMovieNotFoundId() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.movies.size, 0)

        val movie = Movie("mov_1", "mov1", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addMovie(movie)

        assertEquals(unQflix.movies.size, 1)
        assertEquals(unQflix.movies.first().title, movie.title)

        unQflix.deleteMovie("mov_2")
        assertEquals(unQflix.movies.size, 1)
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

    @Test
    fun deleteBanner() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie2 = Serie("ser_2", "ser2", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())

        unQflix.addBanner(serie)
        unQflix.addBanner(serie2)

        assertEquals(unQflix.banners.size, 2)

        unQflix.deleteBanner(serie2)

        assertEquals(unQflix.banners.size, 1)
        assertTrue(unQflix.banners.contains(serie))
        assertFalse(unQflix.banners.contains(serie2))
    }

    @Test
    fun searchMovies() {
        val unQflix = UNQFlix()

        val movie = Movie("mov_1", "mov1", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        val movie1 = Movie("mov_2", "mov2", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        val movie2 = Movie("mov_3", "mov3", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        val movie3 = Movie("mov_4", "pepe", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addMovie(movie)
        unQflix.addMovie(movie1)
        unQflix.addMovie(movie2)
        unQflix.addMovie(movie3)

        assertEquals(unQflix.movies.size, 4)

        val filtered = unQflix.searchMovies("mov")
        assertEquals(filtered.size, 3)

        val filtered2 = unQflix.searchMovies("e")
        assertEquals(filtered2.size, 1)
    }

    @Test
    fun searchSeries() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie2 = Serie("ser_2", "ser2", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie3 = Serie("ser_3", "ser3", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie4 = Serie("ser_4", "lalala", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())

        unQflix.addSerie(serie)
        unQflix.addSerie(serie2)
        unQflix.addSerie(serie3)
        unQflix.addSerie(serie4)

        assertEquals(unQflix.series.size, 4)

        val filtered = unQflix.searchSeries("ser")
        assertEquals(filtered.size, 3)

        val filtered2 = unQflix.searchSeries("a")
        assertEquals(filtered2.size, 1)
    }

    @Test
    fun addLastSeen() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.users.size, 0)
        assertEquals(unQflix.series.size, 0)

        val user = UserBuilder.any()
        unQflix.addUser(user)

        assertEquals(unQflix.users.size, 1)
        assertEquals(user.lastSeen.size, 0)

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
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

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
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

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        unQflix.addSerie(serie)

        assertEquals(unQflix.series.size, 1)

        unQflix.addOrDeleteFav("1", "ser_1")

        assertEquals(user.favorites.size, 1)

        unQflix.addOrDeleteFav("1", "ser_1")

        assertEquals(user.favorites.size, 0)
    }

}