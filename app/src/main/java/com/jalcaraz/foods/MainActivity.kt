package com.jalcaraz.foods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jalcaraz.foods.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var fakeDatabase: FakeDatabase
    @Inject
    lateinit var dataSource: FoodDataSource
    @Inject
    lateinit var adapter: FoodAdapter
    @Inject
    lateinit var llm: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupAutoComplete()
        setupButtons()
    }

    private fun setupRecyclerView() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = llm //LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupAutoComplete() {
        var types = fakeDatabase.getAllTypes().map { it.name }
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, types)
        (binding.tilType.editText as? AutoCompleteTextView)?.setAdapter(typeAdapter)
    }

    private fun setupButtons() {
        with(binding) {
            btnSave.setOnClickListener {
                val type = fakeDatabase.getAllTypes().first { it.name == acType.text.toString() }.id
                val food = FoodEntity(
                    price = etPrice.text.toString().toDouble(),
                    name = etName.text.toString(),
                    type = type
                )
                saveFood(food)
            }
            btnClear.setOnClickListener { clearForm() }
        }
    }

    private fun saveFood(food: FoodEntity) {
        lifecycleScope.launch {
            dataSource.addFood(food) { id ->
                if (id < 1) {
                    Snackbar.make(binding.root, R.string.food_save_error, Snackbar.LENGTH_LONG)
                        .show()
                } else {
                    Log.i("GSUS", "Savefood: Success!!!")
                    clearForm()
                    getFoods()
                }
            }
        }
    }

    private fun clearForm() {
        with(binding) {
            etPrice.setText("")
            etName.setText("")
            //acType.setText("")
        }
    }

    override fun onStart() {
        super.onStart()
        getFoods()
    }

    private fun getFoods() {
        lifecycleScope.launch {
            dataSource.getAllFood { foods ->
                adapter.submitList(foods)
            }
        }
    }
}