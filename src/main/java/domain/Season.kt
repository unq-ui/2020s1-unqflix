package domain

import support.addToList

data class Season(
    override val id: String,
    var title: String,
    var description: String,
    var poster: String,
    var chapters: MutableList<Chapter> = mutableListOf()
) : Id {

    override fun idKey() = "title"
    override fun idValue() = title

    fun addChapter(chapter: Chapter) = addToList(chapter, chapters)

    fun deleteChapter(idChapter: String) = chapters.removeIf { it.id == idChapter }
}
