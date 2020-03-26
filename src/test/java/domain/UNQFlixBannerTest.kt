package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UNQFlixBannerTest {
    @Test
    fun addBanner() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie1 = SerieBuilder.with(id = "ser_1", title = "ser1")
        val serie2 = SerieBuilder.with(id = "ser_2", title = "ser2")
        val serie3 = SerieBuilder.with(id = "ser_3", title = "ser3")
        val serie4 = SerieBuilder.with(id = "ser_4", title = "ser4")
        val serie5 = SerieBuilder.with(id = "ser_5", title = "ser5")
        val serie6 = SerieBuilder.with(id = "ser_6", title = "ser6")

        unqflix.addBanner(serie1)
        unqflix.addBanner(serie2)
        unqflix.addBanner(serie3)
        unqflix.addBanner(serie4)
        unqflix.addBanner(serie5)

        assertEquals(unqflix.banners.size, 5)

        unqflix.addBanner(serie6)
        assertEquals(unqflix.banners.size, 5)
        assertFalse(unqflix.banners.contains(serie1))
    }

    @Test
    fun deleteBanner() {
        val unqflix = UNQFlix()
        assertEquals(unqflix.series.size, 0)

        val serie1 = SerieBuilder.with(id = "ser_1", title = "ser1")
        val serie2 = SerieBuilder.with(id = "ser_2", title = "ser2")

        unqflix.addBanner(serie1)
        unqflix.addBanner(serie2)

        assertEquals(unqflix.banners.size, 2)

        unqflix.deleteBanner(serie2)

        assertEquals(unqflix.banners.size, 1)
        assertTrue(unqflix.banners.contains(serie1))
        assertFalse(unqflix.banners.contains(serie2))
    }
}
