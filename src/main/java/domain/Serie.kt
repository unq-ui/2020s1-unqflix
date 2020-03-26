package domain

import support.addToList

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

    override fun idKey() = "title"
    override fun idValue() = title

    fun addSeason(season: Season) = addToList(season, seasons) { it.title == season.title }

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
}
