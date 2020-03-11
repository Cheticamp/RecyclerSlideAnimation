package com.example.recyclerslideanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

internal class MyAdapter(private val dataList: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.ItemHolder>() {
    private var mVisibilityOfDelete: Int = View.GONE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ItemHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        (view as ViewGroup).layoutTransition.setDuration(1000)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int, payload: MutableList<Any>) {
        if (payload.isNotEmpty()) {
            // payload could be batched. If so, we only care about the final state.
            when (payload.last()) {
                UPDATE_DELETE_VISIBILITY -> holder.mDelete.visibility = mVisibilityOfDelete
            }
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        with(holder) {
            mDescription.text = dataList[position]
            mDelete.visibility = mVisibilityOfDelete
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun toggleDeleteVisibility() {
        mVisibilityOfDelete =
                if (mVisibilityOfDelete == View.GONE) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        notifyItemRangeChanged(0, dataList.size, UPDATE_DELETE_VISIBILITY)
    }

    inner class ItemHolder(itemView: View) : ViewHolder(itemView) {
        val mDescription: TextView = itemView.findViewById(R.id.description)
        val mDelete: ImageView = itemView.findViewById(R.id.delete)
    }

    companion object {
        const val UPDATE_DELETE_VISIBILITY = 1
    }


}
