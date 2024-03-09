package kr.toru.kotlinflowevent.domain.repository.impl

import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository

class FakeJsonRepositoryImpl(private val dataSource: FakeJsonDataSource): FakeJsonRepository {
    override suspend fun getPost(): List<PostDTO> {
        return dataSource.getPost()
    }
}