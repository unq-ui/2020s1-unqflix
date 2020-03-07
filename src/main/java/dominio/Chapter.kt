package dominio

data class Chapter(
    override val id: String,
    var title: String,
    var description: String,
    var duration: Number,
    var video: String,
    var thumbnail: String
) : Id