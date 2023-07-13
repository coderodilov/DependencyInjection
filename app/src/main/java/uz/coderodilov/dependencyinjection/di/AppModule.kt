package uz.coderodilov.dependencyinjection.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.coderodilov.dependencyinjection.api.ApiConfig
import uz.coderodilov.dependencyinjection.api.ApiService
import uz.coderodilov.dependencyinjection.database.AppDatabase
import uz.coderodilov.dependencyinjection.database.PostDao
import uz.coderodilov.dependencyinjection.utility.NetworkHelper
import javax.inject.Singleton

/* 
* Created by Coder Odilov on 13/07/2023
*/

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAoiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,
            "app_database").build()
    }

    @Singleton
    @Provides
    fun providePostDao(appDatabase: AppDatabase) : PostDao{
        return appDatabase.postDao()
    }

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context) : NetworkHelper{
        return NetworkHelper(context)
    }

}
