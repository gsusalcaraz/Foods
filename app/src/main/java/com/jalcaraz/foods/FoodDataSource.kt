package com.jalcaraz.foods

interface FoodDataSource {
    suspend fun getAllFood(callback: (List<FoodEntity>) -> Unit)
    suspend fun addFood(foodEntity: FoodEntity, callback: (Long) -> Unit)
}