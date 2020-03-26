package support

fun <T> addToList(item: T, items: MutableList<T>, compare: (T) -> Boolean): Boolean? {
    return if (items.none(compare)) items.add(item) else null
}
