package com.jalcaraz.foods

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM FoodEntity")
    suspend fun getAllFood(): List<FoodEntity>

    @Insert
    suspend fun addFood(foodEntity: FoodEntity): Long
}