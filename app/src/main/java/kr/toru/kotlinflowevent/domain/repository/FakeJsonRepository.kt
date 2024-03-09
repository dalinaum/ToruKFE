package kr.toru.kotlinflowevent.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.toru.kotlinflowevent.domain.model.PostDTO

interface FakeJsonRepository {
    suspend fun getPost(): Flow<List<PostDTO>>
}