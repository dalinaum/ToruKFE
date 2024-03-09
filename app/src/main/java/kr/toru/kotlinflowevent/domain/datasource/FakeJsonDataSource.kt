package kr.toru.kotlinflowevent.domain.datasource

import kr.toru.kotlinflowevent.domain.model.PostDTO

interface FakeJsonDataSource {
    suspend fun getPost(): List<PostDTO>
}