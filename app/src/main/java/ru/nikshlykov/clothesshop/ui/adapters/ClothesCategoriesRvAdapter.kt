package ru.nikshlykov.clothesshop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.data.models.ClothesCategory
import ru.nikshlykov.clothesshop.ui.OnItemClickListener

class ClothesCategoriesRvAdapter :
    RecyclerView.Adapter<ClothesCategoriesRvAdapter.ClothesCategoriesViewHolder>(){

    private var onItemClickListener: OnItemClickListener<ClothesCategory>? = null
    private var categoriesList: ArrayList<ClothesCategory> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.clothes_category_item, parent, false) as LinearLayout
        return ClothesCategoriesViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: ClothesCategoriesViewHolder, position: Int) {
        val currentItem = categoriesList[position]
        holder.categoryNameButton.text = currentItem.name
        holder.categoryNameButton.setOnClickListener { view ->
            onItemClickListener?.onItemClick(categoriesList[position], view)
        }
    }

    class ClothesCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryNameButton: MaterialButton =
            itemView.findViewById(R.id.clothes_category_item___button___name)
    }

    fun setCategoriesList(categories: ArrayList<ClothesCategory>) {
        categoriesList = categories
        notifyDataSetChanged()
    }

    fun attachOnItemClickListener(onItemClickListener: OnItemClickListener<ClothesCategory>){
        this.onItemClickListener = onItemClickListener
    }

    fun detachOnItemClickListener(){
        this.onItemClickListener = null
    }
}