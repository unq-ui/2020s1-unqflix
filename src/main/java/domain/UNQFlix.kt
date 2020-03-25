package domain

class UNQFlix(
    val movies: MutableList<Movie> = mutableListOf(),
    val series: MutableList<Serie> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val users: MutableList<User> = mutableListOf(),
    val banners: MutableList<Content> = mutableListOf()
) {

    fun nextUserId() = IdGenerator.nextUserId()
    fun nextMovieId() = IdGenerator.nextMovieId()
    fun nextSerieId() = IdGenerator.nextSerieId()
    fun nextSeasonId() = IdGenerator.nextSeasonId()
    fun nextChapterId() = IdGenerator.nextChapterId()

    fun addUser(user: User) {
        users.firstOrNull { it.email === user.email }
            ?.let { throw ExistException("User", "email", user.email) }
            ?: run { users.add(user) }
    }

    fun addCategory(category: Category) {
        categories.firstOrNull { it.name === category.name }
            ?.let { throw ExistException("Category", "name", category.name) }
            ?: run { categories.add(category) }
    }

    fun addMovie(movie: Movie) {
        movies.firstOrNull { it.title === movie.title }
            ?.let { throw ExistException("Movie", "title", movie.title) }
            ?: run { movies.add(movie) }
    }

    fun addSerie(serie: Serie) {
        series.firstOrNull { it.title === serie.title }
            ?.let { throw ExistException("Serie", "title", serie.title) }
            ?: run { series.add(serie) }
    }

    fun addSeason(idSerie: String, season: Season) {
        series.find { it.id == idSerie }
            ?.addSeason(season)
            ?: run { throw NotFoundException("Serie", "id", idSerie) }
    }

    fun addChapter(idSerie: String, idSeason: String, chapter: Chapter) {
        series.find { it.id == idSerie }
            ?.addChapter(idSeason, chapter)
            ?: run { throw NotFoundException("Serie", "id", idSerie) }
    }

    fun addBanner(banner: Content) {
        if (banners.size == 5) {
            banners.remove(banners.first())
        }
        banners.add(banner)
    }

    fun deleteBanner(banner: Content) = banners.remove(banner)
    fun deleteMovie(movieId: String) = movies.removeIf { it.id == movieId }
    fun deleteSerie(serieId: String) = series.removeIf { it.id == serieId }

    fun deleteSeason(idSerie: String, idSeason: String) {
        series.find { it.id == idSerie }
            ?.deleteSeason(idSeason)
            ?: run { throw NotFoundException("Serie", "id", idSerie) }
    }

    fun deleteChapter(idSerie: String, idSeason: String, idChapter: String) {
        series.find { it.id == idSerie }
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

    private fun <T : Id> getById(list: MutableCollection<T>, id: String): T =
        list.find { it.id == id } ?: throw NotFoundException("Unknown", "id", id)

    private fun getContentById(id: String): Content {
        if (id.startsWith("mov")) return getById(movies, id)
        if (id.startsWith("ser")) return getById(series, id)
        throw NotFoundException("Content", "id", id)
    }
}
