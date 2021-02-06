package com.livin.androidtest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.livin.androidtest.R
import com.livin.androidtest.data.db.entities.Products
import com.livin.androidtest.data.remote.models.Banner
import com.livin.androidtest.data.remote.models.Carousel
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_BANNER
import com.livin.androidtest.data.remote.models.ItemOrder.Companion.ITEM_CAROUSEL
import com.livin.androidtest.data.remote.models.Product
import com.livin.androidtest.databinding.ItemBannerBinding
import com.livin.androidtest.databinding.ItemCarouselBinding
import com.livin.androidtest.databinding.ItemProductGridBinding
import com.livin.androidtest.local.HomeDisplayItem


class HomeItemsAdapter(private val clickListener: (HomeDisplayItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val homeItems = mutableListOf<HomeDisplayItem>()

    fun setItems(items: MutableList<HomeDisplayItem>) {
        this.homeItems.clear()
        this.homeItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return homeItems[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_CAROUSEL -> {
                val binding: ItemCarouselBinding = ItemCarouselBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CarouselViewModel(binding, clickListener)
            }
            ITEM_BANNER -> {
                val binding: ItemBannerBinding = ItemBannerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BannerViewModel(binding, clickListener)
            }
            else -> {
                val binding: ItemProductGridBinding = ItemProductGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ProductViewModel(binding, clickListener)
            }
        }
    }

    override fun getItemCount(): Int = homeItems.size

    override fun onBindViewHolder(model: RecyclerView.ViewHolder, position: Int) {
        when (model) {
            is ProductViewModel -> {
                homeItems[position].products?.let { model.bind(it) }
            }
            is BannerViewModel -> {
                homeItems[position].banner?.let { model.bind(it) }
            }
            is CarouselViewModel -> {
                homeItems[position].carousels?.let { model.bind(it) }
            }
        }
    }
}

class ProductViewModel(
    private val itemBinding: ItemProductGridBinding,
    val clickListener: (HomeDisplayItem) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(items: MutableList<Product>) {
        val productsAdapter = ProductsAdapter {
        }
        itemBinding.productsList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productsAdapter
        }
        productsAdapter.setItems(items)
    }
}

class BannerViewModel(
    private val itemBinding: ItemBannerBinding,
    val clickListener: (HomeDisplayItem) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var banner: Banner

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Banner) {
        this.banner = item
        Glide.with(itemBinding.root)
            .load(banner.image)
            .into(itemBinding.bannerImage)
    }

    override fun onClick(v: View?) {
    }
}


class CarouselViewModel(
    private val itemBinding: ItemCarouselBinding,
    val clickListener: (HomeDisplayItem) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var products: Products

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(items: MutableList<Carousel>) {
        itemBinding.carouselView.apply {
            size = items.size
            resource = R.layout.image_carousel_item
            autoPlay = true
            indicatorAnimationType = IndicatorAnimationType.THIN_WORM
            carouselOffset = OffsetType.CENTER
            setCarouselViewListener { view, position ->
                // Example here is setting up a full image carousel
                val imageView = view.findViewById<ImageView>(R.id.carouselImage)
                Glide.with(itemBinding.root)
                    .load(items[position].image)
                    .into(imageView)
            }
            show()
        }
    }

    override fun onClick(v: View?) {

    }
}
