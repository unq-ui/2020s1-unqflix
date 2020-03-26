package domain

class ExistsException(item: Id) : Exception(
    "Error: Exist another ${item::class.java.simpleName as String} with ${item.idKey()} = ${item.idValue()}"
)

open class NotFoundException(
    classError: String,
    val prop: String,
    val value: String
) : Exception("""Error: Not found $classError with $prop = $value""")

class SerieNotFoundException(idSerie: String) : NotFoundException("Serie", "id", idSerie)
