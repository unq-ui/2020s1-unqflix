package domain

class UNQFlix(
    val movies: MutableList<Movie> = mutableListOf(),
    val series: MutableList<Serie> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val users: MutableList<User> = mutableListOf(),
    val banners: MutableList<Content> = mutableListOf()
) {
    fun addUser(user: User): Boolean {
        return addToList(user, users) { it.email == user.email }
            ?: throw UserExistsException(user)
    }

    fun addCategory(category: Category): Boolean {
        return addToList(category, categories) { it.name == category.name }
            ?: throw CategoryExistsException(category)
    }

    fun addMovie(movie: Movie): Boolean {
        return addToList(movie, movies) { it.title == movie.title }
            ?: throw MovieExistsException(movie)
    }

    fun addSerie(serie: Serie): Boolean {
        return addToList(serie, series) { it.title == serie.title }
            ?: throw SerieExistsException(serie)
    }

    fun addSeason(idSerie: String, season: Season): Boolean? {
        return addToSerie(idSerie) { it.addSeason(season) }
    }

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
        return series.find { it.id == idSerie }
            ?.deleteSeason(idSeason)
            ?: run { throw NotFoundException("Serie", "id", idSerie) }
    }

    fun deleteChapter(idSerie: String, idSeason: String, idChapter: String): Boolean {
        return series.find { it.id == idSerie }
            ?.deleteChapter(idSeason, idChapter)
            ?: run { throw NotFoundException("Serie", "id", idSerie) }
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

    private fun <T : Id> addToList(item: T, items: MutableList<T>, compare: (T) -> Boolean): Boolean? {
        return if (items.none(compare)) items.add(item) else null
    }

    private fun addToSerie(idSerie: String, addBlock: (s: Serie) -> Boolean): Boolean? {
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
}
