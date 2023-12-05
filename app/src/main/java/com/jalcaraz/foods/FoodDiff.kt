package com.jalcaraz.foods

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

class FoodDiff @Inject constructor() : DiffUtil.ItemCallback<FoodEntity>() {
    override fun areItemsTheSame(oldItem: FoodEntity, newItem: FoodEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FoodEntity, newItem: FoodEntity) =
        oldItem == newItem

}
