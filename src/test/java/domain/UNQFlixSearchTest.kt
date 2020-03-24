package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixSearchTest {
    @Test
    fun searchMovies() {
        val unqflix = UNQFlix()

        val movie1 = MovieBuilder.with(id = "mov_1", title = "mov1")
        val movie2 = MovieBuilder.with(id = "mov_2", title = "mov2")
        val movie3 = MovieBuilder.with(id = "mov_3", title = "mov3")
        val movie4 = MovieBuilder.with(id = "mov_4", title = "pepe")
        unqflix.addMovie(movie1)
        unqflix.addMovie(movie2)
        unqflix.addMovie(movie3)
        unqflix.addMovie(movie4)

        assertEquals(unqflix.movies.size, 4)

        val filtered = unqflix.searchMovies("mov")
        assertEquals(filtered.size, 3)

        val filtered2 = unqflix.searchMovies("e")
        assertEquals(filtered2.size, 1)
    }

    @Test
    fun searchSeries() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie1 = SerieBuilder.with(id = "ser_1", title = "ser1")
        val serie2 = SerieBuilder.with(id = "ser_2", title = "ser2")
        val serie3 = SerieBuilder.with(id = "ser_3", title = "ser3")
        val serie4 = SerieBuilder.with(id = "ser_4", title = "lalala")

        unqflix.addSerie(serie1)
        unqflix.addSerie(serie2)
        unqflix.addSerie(serie3)
        unqflix.addSerie(serie4)

        assertEquals(unqflix.series.size, 4)

        val filtered = unqflix.searchSeries("ser")
        assertEquals(filtered.size, 3)

        val filtered2 = unqflix.searchSeries("a")
        assertEquals(filtered2.size, 1)
    }
}
