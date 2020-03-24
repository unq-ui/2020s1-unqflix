package domain

interface Builder {
    fun any(): Any
}

object UserBuilder : Builder {
    override fun any() = getWith()
    fun getWith(
        id: String = "1",
        name: String = "user",
        creditCard: String = "1234123412341234",
        image: String = "image",
        email:String = "user@gmail.com",
        password:String = "user",
        favorites: MutableCollection<Content> = mutableListOf(),
        lastSeen: MutableCollection<Content> = mutableListOf()
    ) = User(id, name, creditCard, image, email, password, favorites, lastSeen)
}
