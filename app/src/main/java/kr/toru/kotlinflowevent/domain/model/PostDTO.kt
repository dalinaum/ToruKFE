package kr.toru.kotlinflowevent.domain.model

data class PostDTO(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)