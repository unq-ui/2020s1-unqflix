package domain

interface Id {
    val id: String
    fun idKey(): String
    fun idValue(): Any
}
