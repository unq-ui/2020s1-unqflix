package domain

data class Chapter(
    override val id: String,
    var title: String,
    var description: String,
    var duration: Int,
    var video: String,
    var thumbnail: String
) : Id