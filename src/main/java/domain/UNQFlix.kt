package domain

import support.*

class UNQFlix(
    val movies: MutableList<Movie> = mutableListOf(),
    val series: MutableList<Serie> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val users: MutableList<User> = mutableListOf(),
    val banners: MutableList<Content> = mutableListOf()
) {

    fun addUser(user: User) = addToList(user, users)
    fun addMovie(movie: Movie) = addToList(movie, movies)
    fun addSerie(serie: Serie) = addToList(serie, series)
    fun addCategory(category: Category) = addToList(category, categories)

    fun addSeason(idSerie: String, season: Season) = addToSerie(idSerie) { it.addSeason(season) }

    fun addChapter(idSerie: String, idSeason: String, chapter: Chapter): Boolean? {
        return addToSerie(idSerie) { it.addChapter(idSeason, chapter) }
    }

    fun addBanner(banner: Content): Boolean {
        if (banners.size == 5) {
            banners.remove(banners.first())
        }
        return banners.add(banner)
    }

    fun deleteBanner(banner: Content) = banners.remove(banner)
    fun deleteMovie(movieId: String) = movies.removeIf { it.id == movieId }
    fun deleteSerie(serieId: String) = series.removeIf { it.id == serieId }

    fun deleteSeason(idSerie: String, idSeason: String): Boolean {
        return actionToSerie(idSerie) { it.deleteSeason(idSeason) }
    }

    fun deleteChapter(idSerie: String, idSeason: String, idChapter: String): Boolean {
        return actionToSerie(idSerie) { it.deleteChapter(idSeason, idChapter) }
    }

    fun searchMovies(text: String) = movies.filter { it.title.contains(text, true) }
    fun searchSeries(text: String) = series.filter { it.title.contains(text, true) }

    fun addLastSeen(idUser: String, idContent: String) {
        val user = getById(users, idUser)
        val content = getContentById(idContent)
        user.addLastSeen(content)
    }

    fun addOrDeleteFav(idUser: String, idContent: String) {
        val user = getById(users, idUser)
        val content = getContentById(idContent)
        user.addOrDeleteFav(content)
    }

    private fun addToSerie(idSerie: String, addBlock: (s: Serie) -> Boolean?): Boolean {
        return series.find { it.id == idSerie }
            ?.let { addBlock(it) }
            ?: throw SerieNotFoundException(idSerie)
    }

    private fun <T : Id> getById(list: MutableCollection<T>, id: String): T =
        list.find { it.id == id } ?: throw NotFoundException("Unknown", "id", id)

    private fun getContentById(id: String): Content {
        if (id.startsWith("mov")) return getById(movies, id)
        if (id.startsWith("ser")) return getById(series, id)
        throw NotFoundException("Content", "id", id)
    }

    private fun actionToSerie(idSerie: String, action: (Serie) -> Boolean): Boolean {
        return actionToList(idSerie, series, "Serie", action)
    }
}
