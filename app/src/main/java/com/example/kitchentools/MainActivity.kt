package com.example.kitchentools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchentools.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.convertCupsButton.setOnClickListener { cupsToGrams() }

        val ingredientsList = mutableListOf<Data> ()
        val adapter = IngredientAdapter(ingredientsList)
        binding.viewIngredients.adapter = adapter
        binding.viewIngredients.layoutManager = LinearLayoutManager(this)

        binding.ingredientAddBtn.setOnClickListener() {
            val text = binding.ingredientAdd.text.toString()
            val text2 = binding.amountAdd.text.toString()
            if (text.isNotEmpty() && text2.isNotEmpty()) {
                var amount = "%s %s"
                if (binding.gramsOuncesSwitch.isChecked) amount = amount.format(text2, "grams")
                else amount = amount.format(text2, "ounces")

                val ingredient = Data(text, amount)
                ingredientsList.add(ingredient)
                adapter.notifyItemInserted(ingredientsList.size - 1)
            }

        }
    }

    private fun cupsToGrams() {
        val cupsText = binding.cupsNumberInput.text.toString()
        val cups = cupsText.toDoubleOrNull()
        if (cups == 0.0 || cups == null) {
            displayResult(0.0, "")
            return
        }

        if (binding.gramsOuncesSwitch.isChecked) {
            var result = String.format("%.1f", cups * 125.0).toDouble()
            displayResult(result, "grams")
        } else {
            var result = String.format("%.1f", cups * 4.4).toDouble()
            displayResult(result, "ounces")
        }
    }

    private fun displayResult(value: Double, type: String) {
        binding.flourResult.text = getString(R.string.flour_result_text, value.toString(), type)
    }
}