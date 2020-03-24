package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixBannerTest {
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
}
