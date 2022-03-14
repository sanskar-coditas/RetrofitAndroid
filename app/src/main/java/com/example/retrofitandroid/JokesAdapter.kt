package com.example.retrofitandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JokesAdapter(private val jokeList:ArrayList<MyJokesData>): RecyclerView.Adapter<JokesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val singleJoke: TextView = itemView.findViewById(R.id.txtForJoke)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(
            R.layout.single_joke_item,
            parent, false
        )

        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jokeList[position]
        holder.singleJoke.text = currentItem.value
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }


}