package com.example.sfriesen.vaxx_checker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val myDataset: ArrayList<Person>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    // LW change from TextView to view
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)


     var person: ArrayList<Person> = ArrayList()


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerAdapter.MyViewHolder {
        // LW create a new view, use the layout,   change TextView to View
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout, parent, false) as View
        // LW set the view's size, margins, paddings and layout parameters
        val lp = view.getLayoutParams()
        lp.height = parent.measuredHeight/6
        view.setLayoutParams(lp)
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        var fnl = myDataset[position].firstname.toString() + " " + myDataset[position].lastname.toString()

        // LW use the view and drill down to the textview in the layout
        holder.view.findViewById<TextView>(R.id.textViewRecyclerItem).text = fnl

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}