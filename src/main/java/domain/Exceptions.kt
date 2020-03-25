package domain

class ExistsException(
    classError: String,
    val prop: String,
    val value: String
) : Exception("""Error: Exist another $classError with $prop = $value""")

class NotFoundException(
    classError: String,
    val prop: String,
    val value: String
) : Exception("""Error: Not found $classError with $prop = $value""")
