package domain

import support.addToList
import support.actionToList

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

    fun addSeason(season: Season) = addToList(season, seasons)

    fun addChapter(idSeason: String, chapter: Chapter): Boolean {
        return actionToSeason(idSeason) { it.addChapter(chapter) }
    }

    fun deleteSeason(idSeason: String) = seasons.removeIf { it.id == idSeason }

    fun deleteChapter(idSeason: String, idChapter: String): Boolean {
        return actionToSeason(idSeason) { it.deleteChapter(idChapter) }
    }

    private fun actionToSeason(idSeason: String, action: (Season) -> Boolean): Boolean {
        return actionToList(idSeason, seasons, "Season", action)
    }
}
