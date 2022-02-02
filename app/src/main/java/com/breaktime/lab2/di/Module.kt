package com.breaktime.lab2.di

import android.content.Context
import com.breaktime.lab2.currency_parser.CurrencyParser
import com.breaktime.lab2.repository.Repository
import com.breaktime.lab2.util.Preferences
import com.breaktime.lab2.util.ResourcesProvider
import com.breaktime.lab2.view.explore.ExploreViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Singleton

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
    @Singleton
    fun provideResourcesProvider(@ApplicationContext context: Context) = ResourcesProvider(context)

    @Provides
    @Singleton
    fun provideRepository() = Repository()

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context) = Preferences(context)

    @Provides
    @Singleton
    fun providesRealmDatabase(
        @ApplicationContext context: Context
    ): Realm {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration
            .Builder()
            .name("favorite products")
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        return Realm.getDefaultInstance()
    }
}

//@Module
//@InstallIn(ViewModelComponent::class)
//object ViewModelModule {
//    @Provides
//    fun providesExploreViewModel(repository: Repository) = ExploreViewModel(repository)
//}