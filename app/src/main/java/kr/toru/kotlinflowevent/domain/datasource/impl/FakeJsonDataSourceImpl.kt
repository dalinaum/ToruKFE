package kr.toru.kotlinflowevent.domain.datasource.impl

import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.network.FakeJsonService
import javax.inject.Inject

class FakeJsonDataSourceImpl @Inject constructor(
    private val fakeJsonService: FakeJsonService
): FakeJsonDataSource {
    override suspend fun getPost(): List<PostDTO> {
        return fakeJsonService.getPost()
    }
}