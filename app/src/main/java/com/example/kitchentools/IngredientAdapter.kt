package com.example.kitchentools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchentools.databinding.ItemActivityBinding

class IngredientAdapter(
    var ingredients: MutableList<Data>
) : RecyclerView.Adapter<IngredientAdapter.IngredientsViewHolder>() {

    inner class IngredientsViewHolder(val binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemActivityBinding.inflate(layoutInflater, parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.binding.apply {
            itemTitle.text = ingredients[position].title
            itemAmount.text = ingredients[position].amount
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}