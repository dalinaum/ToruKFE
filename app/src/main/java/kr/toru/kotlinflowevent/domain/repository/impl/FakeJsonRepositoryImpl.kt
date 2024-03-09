package kr.toru.kotlinflowevent.domain.repository.impl

import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository
import javax.inject.Inject

class FakeJsonRepositoryImpl @Inject constructor(
    private val dataSource: FakeJsonDataSource
): FakeJsonRepository {
    override suspend fun getPost(): List<PostDTO> {
        val result = dataSource.getPost()
        result.isSuccess.let {
            return result.getOrNull() ?: emptyList()
        }
    }
}