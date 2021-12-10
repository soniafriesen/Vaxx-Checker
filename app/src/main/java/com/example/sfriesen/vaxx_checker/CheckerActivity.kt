package com.example.sfriesen.vaxx_checker

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class CheckerActivity : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checker)
        textView = findViewById(R.id.textViewContent)
        val status:String = "Status: ... "
        textView.setText(status)
    }

    fun onQRScanner(view: View) {
        // set the bitmap and textView
        var myBitmap: Bitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.smartqr
        )
        // put the bitmap into the InputImage
        val image = InputImage.fromBitmap(myBitmap, 0)
        // Set up the barcode scanner
        val scanner = BarcodeScanning.getClient()

        // processing the image
        val result = scanner.process(image)
            .addOnSuccessListener { barcodes ->
                textView.setText("Status: QR Scan Sucessfull")
                Toast.makeText(this,"Valid QR Code", Toast.LENGTH_LONG)
                val intent = Intent(this, IDActivity::class.java)
                startActivity(intent)
                // Task completed successfully
            }
            .addOnFailureListener {
                // Task failed with an exception
                textView.setText("Status: Failed to read QR")
                Toast.makeText(this,"Invalid QR Code", Toast.LENGTH_LONG)
                val intent = Intent(this, IDActivity::class.java)
                startActivity(intent)

            }

    }
}