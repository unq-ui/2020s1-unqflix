package domain

data class Season(
    override val id: String,
    var title: String,
    var description: String,
    var poster: String,
    var chapters: MutableList<Chapter> = mutableListOf()
) : Id {

    fun addChapter(chapter: Chapter): Boolean {
        return chapters.firstOrNull { it.title === chapter.title }
            ?.let { throw ExistException("Chapter", "title", chapter.title) }
            ?: run { chapters.add(chapter) }
    }

    fun deleteChapter(idChapter: String) = chapters.removeIf { it.id == idChapter }
}
