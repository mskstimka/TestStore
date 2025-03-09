package com.test.teststore.app.screens.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CenteredItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.left = space / 2
        outRect.right = space / 2
        outRect.bottom = space
    }
}