package kr.toru.kotlinflowevent.domain.datasource.impl

import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.model.PostDTO
import kr.toru.kotlinflowevent.domain.network.FakeJsonService
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class FakeJsonDataSourceImpl @Inject constructor(
    private val fakeJsonService: FakeJsonService
): FakeJsonDataSource {
    override suspend fun getPost(): Result<List<PostDTO>> {
        try {
            val postResponse = fakeJsonService.getPost()
            if (postResponse.code() == HTTP_OK) {
                postResponse.body()?.let {
                    return Result.success(it)
                }
            }
            return Result.failure(exception = Exception("Failed to get post"))
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure(exception = e)
        }
    }
}
