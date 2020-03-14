package dominio

data class Movie(
    override val id: String,
    override var title: String,
    override var description: String,
    override var poster: String,
    override var state: ContentState,
    var video: String,
    var duration: Int,
    var actors: MutableList<String>,
    var directors: MutableList<String>,
    var categories: MutableList<Category>,
    override var relatedContent: MutableList<Content>
) : Content, Id