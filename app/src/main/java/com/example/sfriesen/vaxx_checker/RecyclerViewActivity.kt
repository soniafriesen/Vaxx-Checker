package com.example.sfriesen.vaxx_checker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStreamReader

class RecyclerViewActivity : AppCompatActivity() {
    companion object {
        var myDataset = ArrayList<Person>()
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val jsonFileString = getJSONData(this,"people.json")
        //TypeToken to get the type of the object
        val listPokemonType = object: TypeToken<ArrayList<Person>>() {}.type
        myDataset = Gson().fromJson(jsonFileString, listPokemonType)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyRecyclerAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    fun onButtonReturn(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    //Extra functions
    //Method: getJSONData()
    fun getJSONData(context:Context, filename:String):String? {
        val jsonString:String
        try {
            // Use bufferedReader
            // Closable.use will automatically close the input at the end of execution
            // it.reader.readText()  is automatically
            var inputStream = openFileInput(filename)
            val isr = InputStreamReader(inputStream)
            jsonString = isr.buffered().use {
                it.readText() }
        } catch(ioException:IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}