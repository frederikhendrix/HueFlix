package com.example.hueflix

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CustomAdapter(private val mList: List<EmotionData>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>()  {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.textViewEmotion.text = ItemsViewModel.emotion
        val stringPercentage  =  ItemsViewModel.percentage.toString() + "%"
        holder.textViewPercentage.text = stringPercentage

        val cardView = holder.itemView.findViewById<CardView>(R.id.cardView)
        val color = Color.parseColor(ItemsViewModel.hexadecimal)
        cardView.setCardBackgroundColor(color)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewEmotion: TextView = itemView.findViewById(R.id.textEmotion)
        val textViewPercentage: TextView = itemView.findViewById(R.id.textPercentage)
    }
}