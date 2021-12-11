package com.example.sfriesen.vaxx_checker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import java.io.IOException
import java.io.OutputStreamWriter
import com.google.gson.Gson

class IDActivity : AppCompatActivity() {
    lateinit var people:ArrayList<Person>
    lateinit var person:Person
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idchecker)
        people = ArrayList<Person>()
        textView = findViewById(R.id.textViewIDContent)
        val status:String = "Status: ... "
        textView.setText(status);
    }
    fun onReadImage(view: View) {
        // setup the textView to display text found


        // setup the bitmap image
        var myBitmap: Bitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.johndoe
        );
        val image = InputImage.fromBitmap(myBitmap, 0)
        val recognizer = TextRecognition.getClient()
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->

                val converttext: String = visionText.text.toString();

                //delimiters not working properly, will probably need a REGEX exp to extract just the name
                val delimitedtext: Array<String> = converttext.split("\n").toTypedArray()
                person = Person(
                    delimitedtext[5],
                    delimitedtext[4],
                    delimitedtext[6],
                    delimitedtext[7],
                    delimitedtext[18]
                )
                textView.setText("Status: Read " +person.firstname + " " + person.lastname)
                people.add(person)
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                textView.setText("Status: Unable to read, try again")
            }
    }
    fun onSaveClick(view:View)    {
        if(people.size != 0)
        {
            //write People Arraylist to file
            writeToFile(this)

            //start intent
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
        else
            textView.setText("Status: Unable to view, read again")
    }

    fun writeToFile(context: Context)
    {
        try {
            val ofile = openFileOutput("people.json", MODE_PRIVATE)
            val osw = OutputStreamWriter(ofile)
            osw.write(Gson().toJson(people))
            osw.flush()
            osw.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

}