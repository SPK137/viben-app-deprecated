package com.example.novelreader

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class AuthorListAdapter(private val myDataSet : Array<String>) : RecyclerView.Adapter<AuthorListAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val materialCardView: MaterialCardView) : RecyclerView.ViewHolder(materialCardView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        // create a new view
        val materialCardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_author, parent, false) as MaterialCardView
        // set the view's size, margins, paddings and layout parameters

        return MyViewHolder(materialCardView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.materialCardView.findViewById<TextView>(R.id.text_author_name).text = myDataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataSet.size
}