package domain

class UNQFlix(
    val movies: MutableList<Movie> = mutableListOf(),
    val series: MutableList<Serie> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val users: MutableList<User> = mutableListOf(),
    val banners: MutableList<Content> = mutableListOf()
) {
    fun addUser(user: User) = addOrFail(user, users)
    fun addMovie(movie: Movie) = addOrFail(movie, movies)
    fun addSerie(serie: Serie) = addOrFail(serie, series)
    fun addCategory(category: Category) = addOrFail(category, categories)

    fun addSeason(idSerie: String, season: Season): Boolean {
        return addToSerie(idSerie) { it.addSeason(season) }
    }

    fun addChapter(idSerie: String, idSeason: String, chapter: Chapter): Boolean {
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

    private fun <T : Id> addOrFail(item: T, items: MutableList<T>): Boolean? {
        return if (items.none { it.equalsId(item) }) {
            items.add(item)
        } else throw item.existsException()
    }

    private fun addToSerie(idSerie: String, addBlock: (serie: Serie) -> Boolean): Boolean {
        val serie = series.find { it.id == idSerie }
        return if (serie != null) addBlock(serie) else throw NotFoundException("Serie", "id", idSerie)
    }

    private fun <T : Id> getById(list: MutableCollection<T>, id: String): T =
        list.find { it.id == id } ?: throw NotFoundException("Unknown", "id", id)

    private fun getContentById(id: String): Content {
        if (id.startsWith("mov")) return getById(movies, id)
        if (id.startsWith("ser")) return getById(series, id)
        throw NotFoundException("Content", "id", id)
    }
}
