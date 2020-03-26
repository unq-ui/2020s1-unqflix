package domain

data class Category(override val id: String, val name: String) : Id {
    override fun idKey() = "name"
    override fun idValue() = name
}
