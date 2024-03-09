package kr.toru.kotlinflowevent.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.toru.kotlinflowevent.domain.datasource.FakeJsonDataSource
import kr.toru.kotlinflowevent.domain.datasource.impl.FakeJsonDataSourceImpl
import kr.toru.kotlinflowevent.domain.network.FakeJsonService
import kr.toru.kotlinflowevent.domain.repository.FakeJsonRepository
import kr.toru.kotlinflowevent.domain.repository.impl.FakeJsonRepositoryImpl
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
internal object ServiceModule {

    @Provides
    @ViewModelScoped
    fun provideFakeJsonService(retrofit: Retrofit): FakeJsonService {
        return retrofit.create(FakeJsonService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideFakeJsonRepository(fakeJsonDataSource: FakeJsonDataSource): FakeJsonRepository {
        return FakeJsonRepositoryImpl(fakeJsonDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideFakeJsonDataSource(fakeJsonService: FakeJsonService): FakeJsonDataSource {
        return FakeJsonDataSourceImpl(fakeJsonService)
    }
}