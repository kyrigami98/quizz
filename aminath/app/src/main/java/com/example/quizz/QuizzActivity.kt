package com.example.quizz

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_quizz.*
import java.util.concurrent.TimeUnit


class QuizzActivity : AppCompatActivity() {

    var starttime: Long = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)
        setSupportActionBar(toolbar)

        startTimer(360000)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.ajouterQuestion -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startTimer(noOfMinutes: Int) {
        val countDownTimer = object : CountDownTimer(noOfMinutes.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val hms = String.format(
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(
                        millisUntilFinished
                    ) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(
                        millisUntilFinished
                    ) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                )
                timer.setText(hms)//set text
            }

            override fun onFinish() {
                timer.setText("TIME'S UP!!") //On finish change timer text
            }
        }.start()

    }
}
