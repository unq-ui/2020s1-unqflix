package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.*

class UNQFlixMovieTest {
    @Test
    fun addMovie() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.movies.size, 0)

        val movie = MovieBuilder.any()
        unqflix.addMovie(movie)

        assertEquals(unqflix.movies.size, 1)
        assertEquals(unqflix.movies.first().title, movie.title)
    }

    @Test
    fun addMovieTwoTimesSameMovie() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.movies.size, 0)

        val movie = MovieBuilder.any()
        unqflix.addMovie(movie)
        assertThrows<ExistsException> { unqflix.addMovie(movie.copy()) }
    }

    @Test
    fun deleteMovie() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.movies.size, 0)

        val movie = MovieBuilder.with(id = "mov_1")
        unqflix.addMovie(movie)

        assertEquals(unqflix.movies.size, 1)
        assertEquals(unqflix.movies.first().title, movie.title)

        unqflix.deleteMovie("mov_1")
        assertEquals(unqflix.movies.size, 0)
    }

    @Test
    fun deleteMovieNotFoundId() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.movies.size, 0)

        val movie = MovieBuilder.with(id = "mov_1")
        unqflix.addMovie(movie)

        assertEquals(unqflix.movies.size, 1)
        assertEquals(unqflix.movies.first().title, movie.title)

        unqflix.deleteMovie("mov_2")
        assertEquals(unqflix.movies.size, 1)
    }
}
