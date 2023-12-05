package com.jalcaraz.foods

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodApp : Application() {
//    companion object {
//        lateinit var database: FoodDatabase
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        database = Room.databaseBuilder(
//            this, FoodDatabase::class.java, name = "FoodDatabase"
//        ).build()
//    }
}