package data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import domain.*
import kotlin.random.Random

data class CategoryData(var id: Int, var name: String)
data class MovieData(val title: String, val desciption: String, val poster: String, val video: String, val duration: Int, val actores: List<String>, val directors: List<String>, val caregories: List<String>)
data class EpisodeData(val title: String, val desciption: String, val duration: Int, val video: String, val thumbnail: String)
data class SeasonData(val title: String, val description: String, val poster: String, val chapters: List<EpisodeData>)
data class SerieData(val title: String, val desciption: String, val poster: String, val caregories: List<String>, val seasons: List<SeasonData>)

val random = Random(100)

private fun readFile(name: String): String {
    return object {}::class.java.classLoader.getResource(name).readText()
}

private fun getCategories(): MutableList<Category> {
    val categoriesString = readFile("genres.json")
    val categoryDataType = object : TypeToken<Array<CategoryData>>() {}.type
    val categories: Array<CategoryData> = Gson().fromJson(categoriesString, categoryDataType)
    return categories.map { Category(it.name) }.toMutableList()
}

private fun lookUpCategories(unqFlix: UNQFlix, caregories: List<String>): MutableList<Category> {
    return caregories.map { c ->
        unqFlix.categories.find { it.name == c }
            ?: unqFlix.categories[unqFlix.categories.size -1]
    }.toMutableList()
}

private fun getRandomState(): ContentState {
    val number = random.nextInt(0,100)
    if (number < 50) return Available()
    return Unavailable()

}

private fun getMovies(): MutableList<MovieData> {
    val moviesString = readFile("movies.json")
    val movieDataType = object : TypeToken<MutableList<MovieData>>() {}.type
    return Gson().fromJson(moviesString, movieDataType)
}

private fun addAllMovies(unqFlix: UNQFlix) {
    val movies = getMovies()
    movies.forEach {
        unqFlix.addMovie(Movie(unqFlix.nextMovieId(), it.title, it.desciption, it.poster, getRandomState(), it.video, it.duration, it.actores.toMutableList(), it.directors.toMutableList(), lookUpCategories(unqFlix, it.caregories), mutableListOf()))
    }
}

private fun getSeries(): MutableList<SerieData> {
    val seriesString = readFile("series.json")
    val serieDataType = object : TypeToken<MutableList<SerieData>>() {}.type
    return Gson().fromJson(seriesString, serieDataType)
}

private fun addAllSeries(unqFlix: UNQFlix) {
    val series = getSeries()
    series.forEach {
        unqFlix.addSerie(
            Serie(
                unqFlix.nextSerieId(),
                it.title,
                it.desciption,
                it.poster,
                getRandomState(),
                lookUpCategories(unqFlix, it.caregories),
                getSeasons(unqFlix, it.seasons),
                mutableListOf()
            )
        )
    }
}

private fun getSeasons(unqFlix: UNQFlix, seasons: List<SeasonData>): MutableList<Season> {
    return seasons.map {
        Season(
            unqFlix.nextSeasonId(),
            it.title,
            it.description,
            it.poster,
            getChapters(unqFlix, it.chapters)
        )
    }.toMutableList()
}

private fun getChapters(unqFlix: UNQFlix, chapters: List<EpisodeData>): MutableList<Chapter> {
    return chapters.map {
        Chapter(unqFlix.nextChapterId(), it.title, it.desciption, it.duration, it.video, it.thumbnail)
    }.toMutableList()
}

private fun addBanners(unqFlix: UNQFlix) {
    val allContent = mutableListOf<Content>()
    allContent.addAll(unqFlix.movies)
    allContent.addAll(unqFlix.series)
    val banners = MutableList(5) { getRelatedContent(allContent, null) }
    banners.forEach {
        unqFlix.addBanner(it)
    }
}

private fun createRelatedContent(unqFlix: UNQFlix) {
    val allContent = mutableListOf<Content>()
    allContent.addAll(unqFlix.movies)
    allContent.addAll(unqFlix.series)
    allContent.forEach { c ->
        c.relatedContent = MutableList(5) { getRelatedContent(allContent, c) }
    }
}

private fun getRelatedContent(allContent: MutableList<Content>, content: Content?): Content {
    val number = random.nextInt(0, allContent.size - 1)
    val relatedContent = allContent[number]
    if (relatedContent === content) getRelatedContent(allContent, content)
    return relatedContent
}

fun getUNQFlix(): UNQFlix {
    val unqFlix = UNQFlix(mutableListOf(), mutableListOf(), getCategories(), mutableListOf(), mutableListOf())
    addAllMovies(unqFlix)
    addAllSeries(unqFlix)
    createRelatedContent(unqFlix)
    addBanners(unqFlix)
    return unqFlix
}
