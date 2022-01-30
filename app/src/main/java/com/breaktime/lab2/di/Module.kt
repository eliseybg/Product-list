package com.breaktime.lab2.di

import com.breaktime.lab2.currency_parser.CurrencyParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object AnalyticsModule {

    @Provides
    fun provideAnalyticsService() = CurrencyParser()
}