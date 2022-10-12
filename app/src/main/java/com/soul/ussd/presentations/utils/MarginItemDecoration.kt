package com.soul.ussd.presentations.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
//            if (parent.getChildAdapterPosition(view) == 0 ||parent.getChildAdapterPosition(view) == 1) {
//                top = spaceHeight
//            }
//            bottom = spaceHeight
//            if (parent.getChildAdapterPosition(view)%2==0) {
//                left = spaceHeight
//                right = spaceHeight/2
//            }else{
//                left = spaceHeight/2
//                right = spaceHeight
//            }

//            if(parent.getChildAdapterPosition(view) == state.itemCount - 1){
//                bottom = spaceHeight
//            }
        }
    }
}