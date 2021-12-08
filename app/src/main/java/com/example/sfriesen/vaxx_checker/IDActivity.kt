package com.example.sfriesen.vaxx_checker

import androidx.appcompat.app.AppCompatActivity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class IDActivity : AppCompatActivity() {
    lateinit var people:ArrayList<Person>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idchecker)
        people = ArrayList<Person>()
    }
    fun onReadImage(view: View) {
        // setup the textView to display text found
        var textView: TextView = findViewById(R.id.textViewIDContent)

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
                val person = Person(
                    delimitedtext[5],
                    delimitedtext[4],
                    delimitedtext[6],
                    delimitedtext[7],
                    delimitedtext[12]
                )
                people.add(person)
                textView.setText(people[0].firstname + " " + people[0].lastname)
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                textView.setText("Task Failed")
            }
    }
}