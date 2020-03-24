package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixSearchTest {
    @Test
    fun searchMovies() {
        val unqflix = UNQFlix()

        val movie = Movie("mov_1", "mov1", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        val movie1 = Movie("mov_2", "mov2", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        val movie2 = Movie("mov_3", "mov3", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        val movie3 = Movie("mov_4", "pepe", "mov1", "mov1", Available(), "video", 210, mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
        unqflix.addMovie(movie)
        unqflix.addMovie(movie1)
        unqflix.addMovie(movie2)
        unqflix.addMovie(movie3)

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

        val serie = Serie("ser_1", "ser1", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie2 = Serie("ser_2", "ser2", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie3 = Serie("ser_3", "ser3", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())
        val serie4 = Serie("ser_4", "lalala", "ser1", "ser1", Available(), mutableListOf(), mutableListOf(), mutableListOf())

        unqflix.addSerie(serie)
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
