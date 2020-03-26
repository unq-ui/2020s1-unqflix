package domain

data class Movie(
    override val id: String,
    override var title: String,
    override var description: String,
    override var poster: String,
    override var state: ContentState,
    var video: String,
    var duration: Int,
    var actors: MutableList<String> = mutableListOf(),
    var directors: MutableList<String> = mutableListOf(),
    var categories: MutableList<Category> = mutableListOf(),
    override var relatedContent: MutableList<Content> = mutableListOf()
) : Content, Id {
    override fun idKey() = "title"
    override fun idValue() = title
}
