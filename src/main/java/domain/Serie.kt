package domain

import support.*

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

    fun deleteSeason(idSeason: String) = seasons.removeIf { it.id == idSeason }

    fun addChapter(idSeason: String, chapter: Chapter) = getSeason(idSeason).addChapter(chapter)

    fun deleteChapter(idSeason: String, idChapter: String) = getSeason(idSeason).deleteChapter(idChapter)

    private fun getSeason(idSeason: String): Season = itemFromList(idSeason, seasons, "Season")
}
