package com.example.recyclerslideanimation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private val items = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..100) {
            items.add("Item #$i")
        }
        myAdapter = MyAdapter(items)
        mRecyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.toggleButton -> {
                // Show/hide delete icon.
                myAdapter.toggleDeleteVisibility()
            }
            R.id.insertButton -> {
                // Insert random but visible item.
                val firstVisible = (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val pos = firstVisible + (Math.random() * (mRecyclerView.childCount - 1)).roundToInt()
                items.add(pos, "New item #$pos")
                myAdapter.notifyItemInserted(pos)
            }
        }
    }
}