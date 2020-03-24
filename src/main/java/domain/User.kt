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
    fun addLastSeen(content: Content) {
        this.lastSeen.find { it.id == content.id }
            ?: this.lastSeen.add(content)
    }

    fun addOrDeleteFav(content: Content) {
        this.favorites.find { it.id == content.id }
            ?.let { this.favorites.remove(it) }
            ?: run { this.favorites.add(content) }
    }
}
