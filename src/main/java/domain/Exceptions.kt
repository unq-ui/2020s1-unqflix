package domain

class ExistsException(item: Id) : Exception(
    "Error: Exist another ${item::class.java.simpleName as String} with ${item.idKey()} = ${item.idValue()}"
)

class NotFoundException(
    classError: String,
    val prop: String,
    val value: String
) : Exception("""Error: Not found $classError with $prop = $value""")
