package kr.toru.kotlinflowevent.domain.datasource.impl

import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.network.ApiResponse
import kr.toru.kotlinflowevent.domain.network.FakeJsonService
import kr.toru.kotlinflowevent.domain.network.HTTPFailedException
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class FakeJsonDataSourceImpl @Inject constructor(
    private val fakeJsonService: FakeJsonService
): FakeJsonDataSource {
    override suspend fun getPost(): ApiResponse<List<PostDTO>> {
        try {
            val postResponse = fakeJsonService.getPost()
            if (postResponse.code() == HTTP_OK) {
                postResponse.body()?.let {
                    return ApiResponse.Success(it)
                }
            }
            return ApiResponse.Failure(exception = HTTPFailedException())
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Failure(exception = e)
        }
    }
}
