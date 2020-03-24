package domain

class UNQFlix(
    val movies: MutableList<Movie> = mutableListOf(),
    val series: MutableList<Serie> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val users: MutableList<User> = mutableListOf(),
    val banners: MutableList<Content> = mutableListOf()
) {

    var nextUserId = 0
    var nextMovieId = 0
    var nextSerieId = 0
    var nextSeasonId = 0
    var nextChapterId = 0

    fun getNewUserId(): String {
        val id = nextUserId++
        return """u_$id"""
    }

    fun getNewMovieId(): String {
        val id = nextMovieId++
        return """mov_$id"""
    }

    fun getNewSerieId(): String {
        val id = nextSerieId++
        return """ser_$id"""
    }

    fun getNewSeasonId(): String {
        val id = nextSeasonId++
        return """sea_$id"""
    }

    fun getNewChapterId(): String {
        val id = nextChapterId++
        return """cha_$id"""
    }

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

    fun addMovie(movie : Movie) {
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
            ?.let { it.addSeason(season) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun addChapter(idSerie: String, idSeason: String, chapter: Chapter) {
        series.find { it.id == idSerie }
            ?.let { it.addChapter(idSeason, chapter) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun addBanner(banner: Content) {
        if(banners.size == 5) {
            banners.remove(banners.first())
        }
        banners.add(banner)
    }

    fun deleteMovie(movieId: String) {
        movies.removeIf { it.id == movieId}
    }

    fun deleteSerie(serieId: String) {
        series.removeIf { it.id == serieId}
    }

    fun deleteSeason(idSerie: String, idSeason: String){
        series.find { it.id == idSerie }
            ?.let { it.deleteSeason(idSeason) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun deleteChapter(idSerie: String, idSeason: String, idChapter: String) {
        series.find { it.id == idSerie }
            ?.let { it.deleteChapter(idSeason, idChapter) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun deleteBanner(banner: Content) {
        banners.remove(banner)
    }

    fun searchMovies(text: String): List<Movie> {
        return movies.filter { it.title.contains(text, true) }
    }

    fun searchSeries(text: String): List<Serie> {
        return series.filter { it.title.contains(text, true) }
    }

    fun addLastSeen(idUser: String, idContent: String) {
        val user = getById(users, idUser);
        val content = getContentById(idContent)
        user.addLastSeen(content);
    }

    fun addOrDeleteFav(idUser: String, idContent: String) {
        val user = getById<User>(users, idUser);
        val content = getContentById(idContent)
        user.addOrDeleteFav(content);
    }

    fun <T : Id> getById(list: MutableCollection<T>, id: String): T {
        return list.find { it.id == id } ?: throw NotFoundException("Unknow", "id", id)
    }

    fun getContentById(id: String): Content {
        if(id.startsWith("mov")) return getById(movies, id)
        if(id.startsWith("ser")) return getById(series, id)
        throw NotFoundException("Content", "id", id)
    }

}
