package data

import domain.*
import kotlin.random.Random
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val random = Random(100)
val idGenerator = IdGenerator()

private fun readFile(name: String): String {
    return object {}::class.java.classLoader.getResource(name).readText()
}

private fun getCategories(): MutableList<Category> {
    val categoriesString = readFile("genres.json")
    val categoryDataType = object : TypeToken<Array<CategoryData>>() {}.type
    val categories: Array<CategoryData> = Gson().fromJson(categoriesString, categoryDataType)
    return categories.map { Category(it.name) }.toMutableList()
}

private fun lookUpCategories(unqflix: UNQFlix, categories: List<String>): MutableList<Category> {
    return categories.map { c ->
        unqflix.categories.find { it.name == c } ?: unqflix.categories[unqflix.categories.size - 1]
    }.toMutableList()
}

private fun getRandomState(): ContentState {
    val number = random.nextInt(0, 100)
    if (number < 50) return Available()
    return Unavailable()
}

private fun getMovies(): MutableList<MovieData> {
    val moviesString = readFile("movies.json")
    val movieDataType = object : TypeToken<MutableList<MovieData>>() {}.type
    return Gson().fromJson(moviesString, movieDataType)
}

private fun addAllMovies(unqflix: UNQFlix) {
    val movies = getMovies()
    movies.forEach {
        unqflix.addMovie(
            Movie(
                idGenerator.nextMovieId(),
                it.title,
                it.description,
                it.poster,
                getRandomState(),
                it.video,
                it.duration,
                it.actores.toMutableList(),
                it.directors.toMutableList(),
                lookUpCategories(unqflix, it.categories),
                mutableListOf()
            )
        )
    }
}

private fun getSeries(): MutableList<SerieData> {
    val seriesString = readFile("series.json")
    val serieDataType = object : TypeToken<MutableList<SerieData>>() {}.type
    return Gson().fromJson(seriesString, serieDataType)
}

private fun addAllSeries(unqflix: UNQFlix) {
    val series = getSeries()
    series.forEach {
        unqflix.addSerie(
            Serie(
                idGenerator.nextSerieId(),
                it.title,
                it.description,
                it.poster,
                getRandomState(),
                lookUpCategories(unqflix, it.categories),
                getSeasons(unqflix, it.seasons),
                mutableListOf()
            )
        )
    }
}

private fun getSeasons(unqflix: UNQFlix, seasons: List<SeasonData>): MutableList<Season> {
    return seasons.map {
        Season(
            idGenerator.nextSeasonId(),
            it.title,
            it.description,
            it.poster,
            getChapters(unqflix, it.chapters)
        )
    }.toMutableList()
}

private fun getChapters(unqflix: UNQFlix, chapters: List<EpisodeData>): MutableList<Chapter> {
    return chapters.map {
        Chapter(
            idGenerator.nextChapterId(),
            it.title,
            it.description,
            it.duration,
            it.video,
            it.thumbnail
        )
    }.toMutableList()
}

private fun addBanners(unqflix: UNQFlix) {
    val allContent = mutableListOf<Content>()
    allContent.addAll(unqflix.movies)
    allContent.addAll(unqflix.series)
    val banners = MutableList(5) { getRelatedContent(allContent, null) }
    banners.forEach {
        unqflix.addBanner(it)
    }
}

private fun createRelatedContent(unqflix: UNQFlix) {
    val allContent = mutableListOf<Content>()
    allContent.addAll(unqflix.movies)
    allContent.addAll(unqflix.series)
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
    val unqflix = UNQFlix(categories = getCategories())
    addAllMovies(unqflix)
    addAllSeries(unqflix)
    createRelatedContent(unqflix)
    addBanners(unqflix)
    return unqflix
}
