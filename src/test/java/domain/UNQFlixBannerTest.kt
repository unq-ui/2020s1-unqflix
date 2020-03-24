package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixBannerTest {
    @Test
    fun addBanner() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie1 = SerieBuilder.with(id = "ser_1", title = "ser1")
        val serie2 = SerieBuilder.with(id = "ser_2", title = "ser2")
        val serie3 = SerieBuilder.with(id = "ser_3", title = "ser3")
        val serie4 = SerieBuilder.with(id = "ser_4", title = "ser4")
        val serie5 = SerieBuilder.with(id = "ser_5", title = "ser5")
        val serie6 = SerieBuilder.with(id = "ser_6", title = "ser6")

        unQflix.addBanner(serie1)
        unQflix.addBanner(serie2)
        unQflix.addBanner(serie3)
        unQflix.addBanner(serie4)
        unQflix.addBanner(serie5)

        assertEquals(unQflix.banners.size, 5)

        unQflix.addBanner(serie6)
        assertEquals(unQflix.banners.size, 5)
        assertFalse(unQflix.banners.contains(serie1))
    }

    @Test
    fun deleteBanner() {
        val unQflix = UNQFlix()
        assertEquals(unQflix.series.size, 0)

        val serie1 = SerieBuilder.with(id = "ser_1", title = "ser1")
        val serie2 = SerieBuilder.with(id = "ser_2", title = "ser2")

        unQflix.addBanner(serie1)
        unQflix.addBanner(serie2)

        assertEquals(unQflix.banners.size, 2)

        unQflix.deleteBanner(serie2)

        assertEquals(unQflix.banners.size, 1)
        assertTrue(unQflix.banners.contains(serie1))
        assertFalse(unQflix.banners.contains(serie2))
    }
}
