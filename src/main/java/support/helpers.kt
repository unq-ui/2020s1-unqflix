package support

import domain.Id
import domain.ExistsException
import domain.NotFoundException

fun <T : Id> addToList(item: T, items: MutableList<T>): Boolean {
    val none = items.none { it.idValue() == item.idValue() }
    return if (none) items.add(item) else throw ExistsException(item)
}

fun <T : Id> actionToList(idSearched: String, items: MutableList<T>, name: String, action: (T) -> Boolean): Boolean {
    return items.find { it.id == idSearched }
        ?.let { action(it) }
        ?: run { throw NotFoundException(name, "id", idSearched) }
}
