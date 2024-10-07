package com.mehmetbaloglu.jettrivia.di

import com.mehmetbaloglu.jettrivia.data.repository.QuestionsRepository
import com.mehmetbaloglu.jettrivia.retrofit.QuestionsApi
import com.mehmetbaloglu.jettrivia.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionsApi() : QuestionsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionsApi::class.java)

    @Singleton
    @Provides
    fun provideQuestionsRepository(api: QuestionsApi): QuestionsRepository = QuestionsRepository(api)

}