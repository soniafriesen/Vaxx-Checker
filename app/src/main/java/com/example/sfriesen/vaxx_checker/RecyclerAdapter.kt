package com.example.sfriesen.vaxx_checker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
class MyRecyclerAdapter(private val myDataset: ArrayList<Person>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    // LW Renamed textview to view
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyRecyclerAdapter.MyViewHolder {
        // create a new view
        // LW changed textview to view and set the layout to the xml created
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout, parent, false) as View
        // set the view's size, margins, paddings and layout parameters
        val lp = view.layoutParams
        lp.height = parent.measuredHeight/8
        view.layoutParams = lp
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // LW set the item to bind through view.textview
        holder.view.findViewById<TextView>(R.id.textViewfname).text = myDataset[position].firstname
        holder.view.findViewById<TextView>(R.id.textViewlname).text = myDataset[position].lastname
        holder.view.findViewById<TextView>(R.id.textViewStreet).text = myDataset[position].streetName
        holder.view.findViewById<TextView>(R.id.textViewLocation).text = myDataset[position].location
        holder.view.findViewById<TextView>(R.id.textViewdob).text = myDataset[position].dob
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size


}