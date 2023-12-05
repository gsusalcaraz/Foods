package com.jalcaraz.foods

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModel {
    @Singleton
    @Binds
    abstract fun bindDataSource(imp: FoodDataSourceRoom): FoodDataSource
}

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Provides
    fun provideDao(database: FoodDatabase): FoodDao = database.foodDao()

    @Singleton //Para que el comienzo de la base de datos tenga una sola instancia
    @Provides
    fun provideDatabaseRoom(@ApplicationContext app: Context): FoodDatabase = Room.databaseBuilder(
        app, FoodDatabase::class.java, name = "FoodDatabase"
    ).build()
}

@InstallIn(SingletonComponent::class)
@Module
object AdapterModule {
    @Provides
    fun provideLayoutManager(@ApplicationContext context: Context): LayoutManager =
        LinearLayoutManager(context)
//    fun provideLayoutManager(@ApplicationContext context: Context): LayoutManager =
//        GridLayoutManager(context, 2)
}