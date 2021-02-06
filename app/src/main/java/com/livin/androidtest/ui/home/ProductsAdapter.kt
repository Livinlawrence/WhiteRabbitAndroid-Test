package com.livin.androidtest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.livin.androidtest.data.remote.models.Product
import com.livin.androidtest.databinding.ItemProductBinding


class ProductsAdapter(private val clickListener: (Product) -> Unit) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    private val items = mutableListOf<Product>()

    fun setItems(items: MutableList<Product>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemProductBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])
}

class CharacterViewHolder(
    private val itemBinding: ItemProductBinding,
    private val clickListener: (Product) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var product: Product

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Product) {
        this.product = item
        itemBinding.productName.text = item.name
        itemBinding.discountInfo.visibility = View.GONE
        item.discount?.let {
            itemBinding.discountInfo.visibility = View.VISIBLE
            itemBinding.discountInfo.text = "Min $it Off"
        }
        Glide.with(itemBinding.root)
            .load(item.thumb)
            .into(itemBinding.productImage)
    }

    override fun onClick(v: View?) {
        clickListener(product)
    }
}

