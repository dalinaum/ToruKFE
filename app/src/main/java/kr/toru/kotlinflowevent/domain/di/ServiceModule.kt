package kr.toru.kotlinflowevent.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.android.scopes.ViewModelScoped
import kr.toru.kotlinflowevent.domain.network.FakeJsonService
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    @ViewModelScoped
    fun provideFakeJsonService(retrofit: Retrofit): FakeJsonService {
        return retrofit.create(FakeJsonService::class.java)
    }
}