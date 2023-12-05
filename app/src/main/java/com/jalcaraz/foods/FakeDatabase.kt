package com.jalcaraz.foods

import javax.inject.Inject

class FakeDatabase @Inject constructor() {
    fun getAllTypes(): List<FoodType> = listOf(
        FoodType(1,"Comida rápida"),
        FoodType(2,"Postre"),
        FoodType(3,"Asiática"),
        FoodType(4,"Argentina"),
        FoodType(5,"Española"),
        FoodType(6,"Mexicana"),
    )
}