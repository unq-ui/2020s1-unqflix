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

    fun addSeason(season: Season): Boolean {
        return seasons.firstOrNull { it.title === season.title }
            ?.let { throw ExistsException("Season", "title", season.title) }
            ?: run { seasons.add(season) }
    }

    fun addChapter(idSeason: String, chapter: Chapter): Boolean {
        return seasons.find { it.id == idSeason }
            ?.addChapter(chapter)
            ?: run { throw NotFoundException("Season", "id", idSeason) }
    }

    fun deleteSeason(idSeason: String) = seasons.removeIf { it.id == idSeason }

    fun deleteChapter(idSeason: String, idChapter: String): Boolean {
        return seasons.find { it.id == idSeason }
            ?.deleteChapter(idChapter)
            ?: run { throw NotFoundException("Season", "id", idSeason) }
    }

    override fun equalsId(other: Any?): Boolean = other is Serie && other.title == title
    override fun existsException() = ExistsException("Serie", "title", title)
}
