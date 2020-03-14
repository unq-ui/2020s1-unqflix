package dominio

data class Serie(
    override val id: String,
    override var title: String,
    override var description: String,
    override var poster: String,
    override var state: ContentState,
    var categories: MutableList<Category>,
    var seasons: MutableList<Season>,
    override var relatedContent: MutableList<Content>
) : Content, Id  {
    fun addSeason(season: Season) {
        this.seasons.firstOrNull { it.title === season.title }
            ?.let { throw ExistException("Season", "title", season.title) }
            ?: run { this.seasons.add(season) }
    }

    fun addChapter(idSeason: String, chapter: Chapter) {
        this.seasons.find { it.id == idSeason }
            ?.let { it.addChapter(chapter) }
            ?: run { throw NotFoundException("Season", "id", idSeason)}
    }

    fun deleteSeason(idSeason: String) {
        this.seasons.removeIf { it.id == idSeason}
    }

    fun deleteChapter(idSeason: String, idChapter: String) {
        this.seasons.find { it.id == idSeason }
            ?.let { it.deleteChapter(idChapter) }
            ?: run { throw NotFoundException("Season", "id", idSeason)}
    }
}