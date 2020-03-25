package domain

interface Id {
    val id: String
    fun equalsId(other: Any?): Boolean = other is Id && other.id == id
    fun existsException() = ExistsException("Id", "id", id)
}
