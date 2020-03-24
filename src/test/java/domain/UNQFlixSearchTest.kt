package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixSearchTest {
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
}