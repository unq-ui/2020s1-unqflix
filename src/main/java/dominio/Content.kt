package dominio

interface Content : Id {
    var description: String
    var poster: String
    var title: String
    var state: ContentState
    val relatedContent: MutableCollection<Content>
}