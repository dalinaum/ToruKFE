package kr.toru.kotlinflowevent.domain.datasource.impl

import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.network.FakeJsonService
import kr.toru.kotlinflowevent.domain.network.Network

class FakeJsonDataSourceImpl: FakeJsonDataSource {
    override suspend fun getPost(): List<PostDTO> {
        return Network.initRetrofit().create(FakeJsonService::class.java)
            .getPost()
    }
}