package com.lit.sharedprefsappthree

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Find Views
        val color = findViewById<EditText>(R.id.color)
        val name  = findViewById<EditText>(R.id.name)

        val reload = findViewById<Button>(R.id.reload)
        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener {
            //Get name and color and Save to Preferences
            val inputcolor = color.text.toString()
            val inputname = name.text.toString()
            //Create a Shared Preferences Object
            val prefs = this.getSharedPreferences("storage", MODE_PRIVATE)
            val editor = prefs.edit()
            //Put name and Color as KEY-VALUE Pairs
            editor.putString("inputcolor", inputcolor)
            editor.putString("inputname", inputname)
            editor.apply()
            //Show a Message to indicate a Save Success
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
        }//End Listener



        //Check If there is color saved  user preference
        val prefs = this.getSharedPreferences("storage", MODE_PRIVATE)
        val inputcolor = prefs.getString("inputcolor", "")
        val inputname = prefs.getString("inputname", "")

        //Find the Text View and Set the Inputname from Preferences
        val welcome = findViewById<TextView>(R.id.welcome)
        welcome.text = "Welcome  $inputname "

        //Check if color is Blue
        if (inputcolor.toString().trim() == "blue"){
            //Find Layout &  Change Layout to Blue
            val layout = findViewById<LinearLayout>(R.id.layout)
            layout.setBackgroundColor(Color.BLUE)
        }

        //Check if color is Green
        if (inputcolor.toString().trim() == "green"){
            //Find Layout & Change Layout to Green
            val layout = findViewById<LinearLayout>(R.id.layout)
            layout.setBackgroundColor(Color.GREEN)
        }
        //Check if color is Red
        if (inputcolor.toString().trim() == "red"){
            //Find Layout & Change Layout to Green
            val layout = findViewById<LinearLayout>(R.id.layout)
            layout.setBackgroundColor(Color.RED)
        }

        //This button is used to reload the App to Detect the Changes saved in Preference.
        //The Aim of preference is to keep the state selected/saved by user even when app is closed and opened! They still Exist!
        reload.setOnClickListener {
            finish()
            startActivity(intent)
        }
    }
}