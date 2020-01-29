package dev.stive.moviereviewer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnWhoAmI: Button
    private lateinit var btnAccountent: Button
    private lateinit var btnIronMan: Button

    private lateinit var txtWhoAmI: TextView
    private lateinit var txtAccountent:  TextView
    private lateinit var txtIronMan:  TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWhoAmI = findViewById(R.id.btnWhoAmI)
        btnIronMan = findViewById(R.id.btnIronMan)
        btnAccountent = findViewById(R.id.btnAccountent)

        txtWhoAmI = findViewById(R.id.txtWhoAmI)
        txtIronMan = findViewById(R.id.txtIronMan)
        txtAccountent = findViewById(R.id.txtAccountent)



        btnWhoAmI.setOnClickListener {
            txtWhoAmI.setTextColor(ContextCompat.getColor(this, R.color.colorMovieTouched))
            // To get current text color of textview
//            txtAccountent.currentTextColor

        }

        btnAccountent.setOnClickListener {
            txtAccountent.setTextColor(ContextCompat.getColor(this, R.color.colorMovieTouched))
        }

        btnIronMan.setOnClickListener {
            txtIronMan.setTextColor(ContextCompat.getColor(this, R.color.colorMovieTouched))
        }
    }
}
