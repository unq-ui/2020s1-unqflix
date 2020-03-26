package domain

import support.addToList

data class Season(
    override val id: String,
    var title: String,
    var description: String,
    var poster: String,
    var chapters: MutableList<Chapter> = mutableListOf()
) : Id {

    fun addChapter(chapter: Chapter): Boolean {
        return addToList(chapter, chapters) { it.title == chapter.title }
            ?: throw ChapterExistsException(chapter)
    }

    fun deleteChapter(idChapter: String) = chapters.removeIf { it.id == idChapter }
}
