package kr.toru.kotlinflowevent.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.network.ApiResponse
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository
import javax.inject.Inject

class FakeJsonRepositoryImpl @Inject constructor(
    private val dataSource: FakeJsonDataSource
): FakeJsonRepository {
    override suspend fun getPost(): Flow<List<PostDTO>> {
        return flow {
            when (val result = dataSource.getPost()) {
                is ApiResponse.Success -> {
                    emit(result.data)
                }

                else -> {
                    emit(emptyList())
                }
            }
        }
    }
}