package domain

data class User(
    override var id: String,
    var name: String,
    var creditCard: String,
    var image: String,
    var email: String,
    var password: String,
    val favorites: MutableCollection<Content> = mutableListOf(),
    val lastSeen: MutableCollection<Content> = mutableListOf()
) : Id {
    override fun idKey() = "email"
    override fun idValue() = email

    fun addLastSeen(content: Content) {
        lastSeen.find { it.id == content.id }
            ?: lastSeen.add(content)
    }

    fun addOrDeleteFav(content: Content) {
        favorites.find { it.id == content.id }
            ?.let { favorites.remove(it) }
            ?: run { favorites.add(content) }
    }
}
