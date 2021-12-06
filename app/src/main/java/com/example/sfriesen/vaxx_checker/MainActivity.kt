package com.example.sfriesen.vaxx_checker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun onStartClick(view: View) {
        when (view.id) {
            R.id.main_start -> {
                val intent = Intent(this, CheckerActivity::class.java)
                startActivity(intent)
            }
        }
        finish()
    }

}