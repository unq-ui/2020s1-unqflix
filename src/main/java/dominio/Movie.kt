package dominio

data class Movie(
    override val id: String,
    override var title: String,
    override var description: String,
    override var poster: String,
    override var state: ContentState,
    var video: String,
    var duration: Number,
    val actors: MutableCollection<String>,
    val directors: MutableCollection<String>,
    val categories: MutableCollection<Category>,
    override val relatedContent: MutableCollection<Content>
) : Content, Id