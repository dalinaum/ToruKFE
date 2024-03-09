package kr.toru.kotlinflowevent.domain.datasource

import kr.toru.kotlinflowevent.domain.datasource.impl.ApiResponse
import kr.toru.kotlinflowevent.domain.model.PostDTO

interface FakeJsonDataSource {
    suspend fun getPost(): ApiResponse<List<PostDTO>>
}