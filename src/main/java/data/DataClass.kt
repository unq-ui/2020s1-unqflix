package data

data class CategoryData(
    var id: Int,
    var name: String
)

data class MovieData(
    val title: String,
    val description: String,
    val poster: String,
    val video: String,
    val duration: Int,
    val actores: List<String>,
    val directors: List<String>,
    val categories: List<String>
)

data class EpisodeData(
    val title: String,
    val description: String,
    val duration: Int,
    val video: String,
    val thumbnail: String
)

data class SeasonData(
    val title: String,
    val description: String,
    val poster: String,
    val chapters: List<EpisodeData>
)

data class SerieData(
    val title: String,
    val description: String,
    val poster: String,
    val categories: List<String>,
    val seasons: List<SeasonData>
)
