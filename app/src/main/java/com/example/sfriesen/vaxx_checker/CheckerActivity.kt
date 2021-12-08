package com.example.sfriesen.vaxx_checker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition

class CheckerActivity : AppCompatActivity() {
    lateinit var people:ArrayList<Person>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checker)
        people = ArrayList<Person>()
        // set the bitmap and textView
        var myBitmap: Bitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.smartqr
        );
        var textView: TextView = findViewById(R.id.textViewContent);

        // put the bitmap into the InputImage
        val image = InputImage.fromBitmap(myBitmap, 0)
        // Set up the barcode scanner
        val scanner = BarcodeScanning.getClient()

        // processing the image
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                textView.setText("QR Scan Sucessfull")
                // Task completed successfully
            }
            .addOnFailureListener {
                // Task failed with an exception
                textView.setText("Failed to read the barcode")
            }
    }



    fun onReadImage(view: View) {
        // setup the textView to display text found
        var textView: TextView = findViewById(R.id.textViewIDContent)

        // setup the bitmap image
        var myBitmap: Bitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.johndoe);
        val image = InputImage.fromBitmap(myBitmap, 0)

        val recognizer = TextRecognition.getClient()

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->

                val converttext: String = visionText.text.toString();

                //delimiters not working properly, will probably need a REGEX exp to extract just the name
                val delimitedtext: Array<String> =converttext.split("\n").toTypedArray()
                val person = Person(delimitedtext[5],delimitedtext[4],delimitedtext[6],delimitedtext[7],delimitedtext[12])
                people.add(person)
                textView.setText(people[0].firstname)
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                textView.setText("Task Failed")
            }
    }
}