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
        val id = this.nextUserId++
        return """u_$id"""
    }

    fun getNewMovieId(): String {
        val id = this.nextMovieId++
        return """mov_$id"""
    }

    fun getNewSerieId(): String {
        val id = this.nextSerieId++
        return """ser_$id"""
    }

    fun getNewSeasonId(): String {
        val id = this.nextSeasonId++
        return """sea_$id"""
    }

    fun getNewChapterId(): String {
        val id = this.nextChapterId++
        return """cha_$id"""
    }

    fun addUser(user: User) {
        this.users.firstOrNull { it.email === user.email }
            ?.let { throw ExistException("User", "email", user.email) }
            ?: run { this.users.add(user) }
    }

    fun addCategory(category: Category) {
        this.categories.firstOrNull { it.name === category.name }
            ?.let { throw ExistException("Category", "name", category.name) }
            ?: run { this.categories.add(category) }
    }

    fun addMovie(movie : Movie) {
        this.movies.firstOrNull { it.title === movie.title }
            ?.let { throw ExistException("Movie", "title", movie.title) }
            ?: run { this.movies.add(movie) }
    }

    fun addSerie(serie: Serie) {
        this.series.firstOrNull { it.title === serie.title }
            ?.let { throw ExistException("Serie", "title", serie.title) }
            ?: run { this.series.add(serie) }
    }

    fun addSeason(idSerie: String, season: Season) {
        this.series.find { it.id == idSerie }
            ?.let { it.addSeason(season) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun addChapter(idSerie: String, idSeason: String, chapter: Chapter) {
        this.series.find { it.id == idSerie }
            ?.let { it.addChapter(idSeason, chapter) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun addBanner(banner: Content) {
        if(this.banners.size == 5) {
            this.banners.remove(this.banners.first())
        }
        this.banners.add(banner)
    }

    fun deleteMovie(movieId: String) {
        this.movies.removeIf { it.id == movieId}
    }

    fun deleteSerie(serieId: String) {
        this.series.removeIf { it.id == serieId}
    }

    fun deleteSeason(idSerie: String, idSeason: String){
        this.series.find { it.id == idSerie }
            ?.let { it.deleteSeason(idSeason) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun deleteChapter(idSerie: String, idSeason: String, idChapter: String) {
        this.series.find { it.id == idSerie }
            ?.let { it.deleteChapter(idSeason, idChapter) }
            ?: run { throw NotFoundException("Serie", "id", idSerie)}
    }

    fun deleteBanner(banner: Content) {
        this.banners.remove(banner)
    }

    fun searchMovies(text: String): List<Movie> {
        return this.movies.filter { it.title.contains(text, true) }
    }

    fun searchSeries(text: String): List<Serie> {
        return this.series.filter { it.title.contains(text, true) }
    }

    fun addLastSeen(idUser: String, idContent: String) {
        val user = this.getById(users, idUser);
        val content = this.getContentById(idContent)
        user.addLastSeen(content);
    }

    fun addOrDeleteFav(idUser: String, idContent: String) {
        val user = this.getById<User>(this.users, idUser);
        val content = this.getContentById(idContent)
        user.addOrDeleteFav(content);
    }

    fun <T : Id> getById(list: MutableCollection<T>, id: String): T {
        return list.find { it.id == id } ?: throw NotFoundException("Unknow", "id", id)
    }

    fun getContentById(id: String): Content {
        if(id.startsWith("mov")) return this.getById(this.movies, id)
        if(id.startsWith("ser")) return this.getById(this.series, id)
        throw NotFoundException("Content", "id", id)
    }

}
