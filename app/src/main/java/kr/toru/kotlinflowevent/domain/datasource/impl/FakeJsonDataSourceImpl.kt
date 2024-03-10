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
            // 코드가 잘못되거나 바디가 잘못될 수 있으면 그 메시지를 예외에 넘겨줄 수 있으면 좋을 듯.
            // 지금으로서는 디버깅에 크게 도움이 안될 것 같음.
            return ApiResponse.Failure(exception = HTTPFailedException())
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Failure(exception = e)
        }
    }
}
