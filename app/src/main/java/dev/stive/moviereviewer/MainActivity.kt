package dev.stive.moviereviewer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    private lateinit var btnWhoAmI: Button
    private lateinit var btnAccountent: Button
    private lateinit var btnIronMan: Button
    private lateinit var btnInvitation: Button

    private lateinit var txtWhoAmI: TextView
    private lateinit var txtAccountent: TextView
    private lateinit var txtIronMan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWhoAmI = findViewById(R.id.btnWhoAmI)
        btnIronMan = findViewById(R.id.btnIronMan)
        btnAccountent = findViewById(R.id.btnAccountent)
        btnInvitation = findViewById(R.id.btnInvitation)

        txtWhoAmI = findViewById(R.id.txtWhoAmI)
        txtIronMan = findViewById(R.id.txtIronMan)
        txtAccountent = findViewById(R.id.txtAccountent)


        btnInvitation.setOnClickListener {
            val textInvitation = "Hi, I am using MovieReviewer and you to do this too)"
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle("Invitation Sender")
                .setText(textInvitation).startChooser()
        }

        btnWhoAmI.setOnClickListener {
            txtWhoAmI.setTextColor(ContextCompat.getColor(this, R.color.colorMovieTouched))
            getAnswerFromExplicitIntent(txtWhoAmI.text as String, R.drawable.who_am_i)
        }

        btnAccountent.setOnClickListener {
            txtAccountent.setTextColor(ContextCompat.getColor(this, R.color.colorMovieTouched))
            getAnswerFromExplicitIntent(txtAccountent.text as String, R.drawable.accountent)
        }

        btnIronMan.setOnClickListener {
            txtIronMan.setTextColor(ContextCompat.getColor(this, R.color.colorMovieTouched))
            getAnswerFromExplicitIntent(txtIronMan.text as String, R.drawable.ironman)
        }
        Log.i("MainActivity", "onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_ACCOUNTENT, txtAccountent.currentTextColor)
        outState.putInt(KEY_IRON_MAN, txtIronMan.currentTextColor)
        outState.putInt(KEY_WHO_AM_I, txtWhoAmI.currentTextColor)

        Log.i("MainActivity", "onSave")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        txtAccountent.setTextColor(savedInstanceState.getInt(KEY_ACCOUNTENT))
        txtIronMan.setTextColor(savedInstanceState.getInt(KEY_IRON_MAN))
        txtWhoAmI.setTextColor(savedInstanceState.getInt(KEY_WHO_AM_I))
        Log.i("MainActivity", "onRestoreInstanceState")
    }

    private fun getAnswerFromExplicitIntent(movieName: String, imgRes: Int) {
        // Passing value of "key"
        val intent = Intent(this@MainActivity, ActivityMovie::class.java)
        intent.putExtra(MOVIE_DATA, PassData(movieName, imgRes))
        startActivityForResult(intent, OUR_REQUEST_CODE)
    }

    companion object {
        const val KEY_WHO_AM_I = "who_am_i"
        const val KEY_ACCOUNTENT = "accountent"
        const val KEY_IRON_MAN = "iron_man"
        const val MOVIE_DATA = "MovieDate"
        const val OUR_REQUEST_CODE = 42
        const val DATA_FROM_EXPLICIT_INTENT = "ExplicitIntent"
    }
}
