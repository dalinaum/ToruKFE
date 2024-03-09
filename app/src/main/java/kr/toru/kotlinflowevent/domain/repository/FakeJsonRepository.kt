package kr.toru.kotlinflowevent.domain.repository

import kr.toru.kotlinflowevent.domain.model.PostDTO

interface FakeJsonRepository {
    suspend fun getPost(): List<PostDTO>
}