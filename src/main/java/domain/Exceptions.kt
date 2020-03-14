package domain

class ExistException(val classError: String, val prop: String, val value: String) : Exception("""Error: Exist another $classError with $prop = $value""")

class NotFoundException(val classError: String, val prop: String, val value: String) : Exception("""Error: Not found $classError with $prop = $value""")