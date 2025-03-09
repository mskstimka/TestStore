package com.test.teststore.app.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.teststore.app.utils.setImageByURL
import com.test.teststore.databinding.ItemProductBinding
import com.test.teststore.domain.models.Product

class ProductsAdapter(
    val navigate: (id: Int) -> Unit,
) :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(
        ContainerPosterDiffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(currentList[position])


    override fun getItemCount(): Int = currentList.size

    inner class ProductViewHolder(
        private val binding:
        ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Product) = with(binding) {

            tvTitle.text = model.title
            tvDescription.text = model.description
            tvPrice.text = "Price: ${model.price}"

            ivAvatar.setImageByURL(model.image.toString())

            root.setOnClickListener{
                navigate(model.id)
            }
        }
    }


    object ContainerPosterDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }
}
