package ru.nikshlykov.clothesshop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.data.models.Product
import ru.nikshlykov.clothesshop.ui.OnItemClickListener

class GoodsRvAdapter : RecyclerView.Adapter<GoodsRvAdapter.ProductViewHolder>() {
    private var onItemClickListener: OnItemClickListener<Product>? = null
    private var productList: ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false) as LinearLayout
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.productNameTextView.text = currentProduct.name
        holder.productPriceTextView.text = currentProduct.price.toString()
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(currentProduct, holder.itemView)
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImageView: ImageView = itemView.findViewById(R.id.product_item___image_view)
        var productNameTextView: TextView =
            itemView.findViewById(R.id.product_item___text_view___name)
        var productPriceTextView: TextView =
            itemView.findViewById(R.id.product_item___text_view___price)
    }

    fun setProductList(categories: ArrayList<Product>) {
        productList = categories
        notifyDataSetChanged()
    }

    fun attachOnItemClickListener(onItemClickListener: OnItemClickListener<Product>) {
        this.onItemClickListener = onItemClickListener
    }

    fun detachOnItemClickListener() {
        this.onItemClickListener = null
    }
}