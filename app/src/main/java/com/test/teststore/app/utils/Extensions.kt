package com.test.teststore.app.utils

import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.squareup.picasso.Picasso
import com.test.teststore.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.subscribeToFlow(
    lifecycleOwner: LifecycleOwner,
    active: (item: T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@subscribeToFlow.collect {
                active(it)
            }
        }
    }
}

fun ImageView.setImageByURL(url: String) {
    Picasso.get().load(url)
        .placeholder(R.color.white)
        .error(R.color.white).into(this)
}