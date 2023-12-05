package com.jalcaraz.foods

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodDataSourceRoom @Inject constructor(private val dao: FoodDao) : FoodDataSource {

    override suspend fun getAllFood(callback: (List<FoodEntity>) -> Unit) {
        callback(dao.getAllFood())
    }

    override suspend fun addFood(foodEntity: FoodEntity, callback: (Long) -> Unit) {
        val result = dao.addFood(foodEntity)
        callback(result)
    }
}