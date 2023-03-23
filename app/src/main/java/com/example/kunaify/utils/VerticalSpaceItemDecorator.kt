package com.example.kunaify.utils

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecorator(private val spaceHeight: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                setEmpty()
            } else {
                bottom = spaceHeight
            }
        }
    }
}
