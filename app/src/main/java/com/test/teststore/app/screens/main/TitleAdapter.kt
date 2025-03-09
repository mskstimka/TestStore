package com.test.teststore.app.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.teststore.databinding.ItemTitleBinding

class TitleAdapter(
    val navigate: () -> Unit,
) :
    ListAdapter<TitleModel, TitleAdapter.ScreenshotsViewHolder>(
        ScreenshotsDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotsViewHolder {
        val binding =
            ItemTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ScreenshotsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) =
        holder.bind(currentList[position])

    override fun getItemCount(): Int = currentList.size

    inner class ScreenshotsViewHolder(
        private val binding: ItemTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(model: TitleModel) = with(binding) {

           btLocalProducts.setOnClickListener{
               navigate()
           }
        }


    }

    object ScreenshotsDiffCallback : DiffUtil.ItemCallback<TitleModel>() {
        override fun areItemsTheSame(
            oldItem: TitleModel,
            newItem: TitleModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: TitleModel,
            newItem: TitleModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}