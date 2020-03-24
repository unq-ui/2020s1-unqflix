package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

class UNQFlixMovieTest {
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
        assertThrows<ExistException> { unQflix.addMovie(movie) }
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
}
