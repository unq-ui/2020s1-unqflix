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

    fun addSeason(idSerie: String, season: Season) = getSerie(idSerie).addSeason(season)

    fun addChapter(idSerie: String, idSeason: String, chapter: Chapter): Boolean {
        return getSerie(idSerie).addChapter(idSeason, chapter)
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

    fun deleteSeason(idSerie: String, idSeason: String) = getSerie(idSerie).deleteSeason(idSeason)

    fun deleteChapter(
        idSerie: String,
        idSeason: String,
        idChapter: String
    ) = getSerie(idSerie).deleteChapter(idSeason, idChapter)

    fun searchMovies(text: String) = searchIn(text, movies)

    fun searchSeries(text: String) = searchIn(text, series)

    fun addLastSeen(idUser: String, idContent: String) {
        addUserContent(idUser, idContent) { user, content -> user.addLastSeen(content) }
    }

    fun addOrDeleteFav(idUser: String, idContent: String) {
        addUserContent(idUser, idContent) { user, content -> user.addOrDeleteFav(content) }
    }

    private fun getSerie(idSerie: String): Serie = itemFromList(idSerie, series, "Serie")

    private fun addUserContent(idUser: String, idContent: String, action: (User, Content) -> Unit) {
        val user = getById(users, idUser)
        val content = getContentById(idContent)
        action(user, content)
    }

    private fun getContentById(id: String): Content {
        if (id.startsWith("mov")) return getById(movies, id)
        if (id.startsWith("ser")) return getById(series, id)
        throw NotFoundException("Content", "id", id)
    }
}
