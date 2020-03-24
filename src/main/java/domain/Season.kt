package domain

data class Season(
    override val id: String,
    var title: String,
    var description: String,
    var poster: String,
    var chapters: MutableList<Chapter> = mutableListOf()
) : Id {
    fun addChapter(chapter: Chapter) {
        this.chapters.firstOrNull { it.title === chapter.title }
            ?.let { throw ExistException("Chapter", "title", chapter.title) }
            ?: run { this.chapters.add(chapter) }
    }

    fun deleteChapter(idChapter: String) {
        this.chapters.removeIf { it.id == idChapter}
    }
}
