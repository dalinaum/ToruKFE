package kr.toru.kotlinflowevent.domain.datasource

import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.network.ApiResponse

interface FakeJsonDataSource {
    suspend fun getPost(): ApiResponse<List<PostDTO>>
}