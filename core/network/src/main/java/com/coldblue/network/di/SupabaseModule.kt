package com.coldblue.network.di

import com.coldblue.network.BuildConfig
import com.coldblue.network.SupabaseDataSource
import com.coldblue.network.SupabaseDataSourceImpl
import com.coldblue.network.datasource.MandaDetailDataSource
import com.coldblue.network.datasource.MandaKeyDataSource
import com.coldblue.network.datasource.MandaTodoDataSource
import com.coldblue.network.datasource.NoticeDataSource
import com.coldblue.network.datasource.SurveyDataSource
import com.coldblue.network.datasource.TodoDataSource
import com.coldblue.network.datasource.UpdateNoteDataSource
import com.coldblue.network.datasourceImpl.MandaDetailDataSourceImpl
import com.coldblue.network.datasourceImpl.MandaKeyDataSourceImpl
import com.coldblue.network.datasourceImpl.MandaTodoDataSourceImpl
import com.coldblue.network.datasourceImpl.NoticeDataSourceImpl
import com.coldblue.network.datasourceImpl.SurveyDataSourceImpl
import com.coldblue.network.datasourceImpl.TodoDataSourceImpl
import com.coldblue.network.datasourceImpl.UpdateNoteNoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.SupaUrl,
        supabaseKey = BuildConfig.SupaKey
    ) {
        install(Auth)
        install(ComposeAuth) { googleNativeLogin(serverClientId = BuildConfig.ClientId) }
        install(Postgrest)
    }

    @Singleton
    @Provides
    fun provideComposeAuth(client: SupabaseClient): ComposeAuth = client.composeAuth

    @Singleton
    @Provides
    fun provideSupaRepository(client: SupabaseClient): SupabaseDataSource =
        SupabaseDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideTodoDataSource(client: SupabaseClient): TodoDataSource =
        TodoDataSourceImpl(client)


    @Singleton
    @Provides
    fun provideMandaKeyDataSource(client: SupabaseClient): MandaKeyDataSource =
        MandaKeyDataSourceImpl(client)


    @Singleton
    @Provides
    fun provideMandaDetailDataSource(client: SupabaseClient): MandaDetailDataSource =
        MandaDetailDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideUpdateNoteDataSource(client: SupabaseClient): UpdateNoteDataSource =
        UpdateNoteNoteDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideNoticeDataSource(client: SupabaseClient): NoticeDataSource =
        NoticeDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideMandaTodoDataSource(client: SupabaseClient): MandaTodoDataSource =
        MandaTodoDataSourceImpl(client)


    @Singleton
    @Provides
    fun provideSurveyDataSource(client: SupabaseClient): SurveyDataSource =
        SurveyDataSourceImpl(client)

}
