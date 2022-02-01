package com.breaktime.lab2.di

import android.content.Context
import com.breaktime.lab2.currency_parser.CurrencyParser
import com.breaktime.lab2.util.Preferences
import com.breaktime.lab2.util.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideCurrencyParser() = CurrencyParser()
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResourcesProvider(@ApplicationContext context: Context) = ResourcesProvider(context)

    @Provides
    fun providePreferences(@ApplicationContext context: Context) = Preferences(context)
}