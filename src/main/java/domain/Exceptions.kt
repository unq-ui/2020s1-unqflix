package domain

open class ExistsException(
    classError: String,
    val prop: String,
    val value: String
) : Exception("""Error: Exist another $classError with $prop = $value""")

open class NotFoundException(
    classError: String,
    val prop: String,
    val value: String
) : Exception("""Error: Not found $classError with $prop = $value""")

class UserExistsException(user: User) : ExistsException("User", "email", user.email)
class MovieExistsException(movie: Movie) : ExistsException("Movie", "title", movie.title)
class SerieExistsException(serie: Serie) : ExistsException("Serie", "title", serie.title)
class SeasonExistsException(season: Season) : ExistsException("Season", "title", season.title)
class ChapterExistsException(chapter: Chapter) : ExistsException("Chapter", "title", chapter.title)
class CategoryExistsException(category: Category) : ExistsException("Category", "name", category.name)

class SerieNotFoundException(idSerie: String) : NotFoundException("Serie", "id", idSerie)
