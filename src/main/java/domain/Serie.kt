package domain

data class Serie(
    override val id: String,
    override var title: String,
    override var description: String,
    override var poster: String,
    override var state: ContentState,
    var categories: MutableList<Category> = mutableListOf(),
    var seasons: MutableList<Season> = mutableListOf(),
    override var relatedContent: MutableList<Content> = mutableListOf()
) : Content, Id {

    fun addSeason(season: Season) {
        seasons.firstOrNull { it.title === season.title }
            ?.let { throw ExistException("Season", "title", season.title) }
            ?: run { seasons.add(season) }
    }

    fun addChapter(idSeason: String, chapter: Chapter) {
        seasons.find { it.id == idSeason }
            ?.addChapter(chapter)
            ?: run { throw NotFoundException("Season", "id", idSeason) }
    }

    fun deleteSeason(idSeason: String) {
        seasons.removeIf { it.id == idSeason }
    }

    fun deleteChapter(idSeason: String, idChapter: String) {
        seasons.find { it.id == idSeason }
            ?.deleteChapter(idChapter)
            ?: run { throw NotFoundException("Season", "id", idSeason) }
    }
}
