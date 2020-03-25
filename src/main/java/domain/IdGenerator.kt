package domain

class IdGenerator {
    var currentUserId = 0
        private set
    var currentMovieId = 0
        private set
    var currentSerieId = 0
        private set
    var currentSeasonId = 0
        private set
    var currentChapterId = 0
        private set

    fun nextUserId(): String = "u_${++currentUserId}"
    fun nextMovieId(): String = "mov_${++currentMovieId}"
    fun nextSerieId(): String = "ser_${++currentSerieId}"
    fun nextSeasonId(): String = "sea_${++currentSeasonId}"
    fun nextChapterId(): String = "cha_${++currentChapterId}"
}
