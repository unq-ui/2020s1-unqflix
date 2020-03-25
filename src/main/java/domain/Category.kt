package domain

data class Category(override val id: String, val name: String) : Id {
    override fun equalsId(other: Any?): Boolean = other is Category && other.name == name
    override fun existsException() = ExistsException("Category", "name", name)
}
