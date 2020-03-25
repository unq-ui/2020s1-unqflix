package domain

interface Builder {
    fun any(): Any
}

object UserBuilder : Builder {
    override fun any() = with()
    fun with(
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

object SerieBuilder : Builder {
    override fun any() = with()
    fun with(
        id: String = "ser_1",
        title: String = "ser1",
        description: String = "ser1",
        poster: String = "ser1",
        state: ContentState = Available(),
        categories: MutableList<Category> = mutableListOf(),
        seasons: MutableList<Season> = mutableListOf(),
        relatedContent: MutableList<Content> = mutableListOf()
    ) = Serie(id, title, description, poster, state, categories, seasons, relatedContent)
}

object SeasonBuilder : Builder {
    override fun any() = with()
    fun with(
        id: String = "sea_0",
        title: String = "sea0",
        description: String = "sea0",
        poster: String = "sea0",
        chapters: MutableList<Chapter> = mutableListOf()
    ) = Season(id, title, description, poster, chapters)
}

object ChapterBuilder : Builder {
    override fun any() = with()
    fun with(
        id: String = "cha_0",
        title: String = "chapter",
        description: String = "chapter",
        duration: Int = 60,
        video: String = "video",
        thumbnail: String = "thumbnail"
    ) = Chapter(id, title, description, duration, video, thumbnail)
}

object MovieBuilder : Builder {
    override fun any() = with()
    fun with(
        id: String = "mov_1",
        title: String = "mov1",
        description: String = "mov1",
        poster: String = "mov1",
        state: ContentState = Available(),
        video: String = "video",
        duration: Int = 210,
        actors: MutableList<String> = mutableListOf(),
        directors: MutableList<String> = mutableListOf(),
        categories: MutableList<Category> = mutableListOf(),
        relatedContent: MutableList<Content> = mutableListOf()
    ) = Movie(id, title, description, poster, state, video, duration, actors, directors, categories, relatedContent)
}

