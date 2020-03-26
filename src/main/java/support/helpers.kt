package support

import domain.Id
import domain.ExistsException

fun <T : Id> addToList(item: T, items: MutableList<T>, compare: (T) -> Boolean): Boolean? {
    return if (items.none(compare)) items.add(item) else throw ExistsException(item)
}
