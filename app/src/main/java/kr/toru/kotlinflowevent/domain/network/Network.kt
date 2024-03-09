package kr.toru.kotlinflowevent.domain.network

import kr.toru.kotlinflowevent.domain.model.PostDTO
import retrofit2.Response
import retrofit2.http.GET

interface FakeJsonService {
    @GET("/posts")
    suspend fun getPost(): Response<List<PostDTO>>
}