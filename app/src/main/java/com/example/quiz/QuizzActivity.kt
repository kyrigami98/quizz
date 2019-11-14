package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_quizz.*
import java.util.concurrent.TimeUnit
import android.widget.CheckBox
import android.widget.Toast


class QuizzActivity : AppCompatActivity() {

    /***********************************************************************************************************************************/

    var starttime: Long = 0
    var i = 0
    var j = 0
    var Reponse = ""
    var score = 0

    val questions = arrayOf(
        arrayOf("Qui es tu?", "Ton age?", "Ton sexe?", "Ton nom?", "Ton adresse?"),
        arrayOf("Aminath/bob/cool","5/15/40", "F/M/FM", "Bof/baf/bif", "Neant/4 rue bebe/4 avenue")
    )

    val reponses = arrayOf("Aminath","15", "F", "bif", "4 rue bebe")


    /***********************************************************************************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        suivant();

        startTimer(360000)

        radioButton.setOnClickListener(View.OnClickListener {
            if (radioButton.isChecked) {
                Reponse = radioButton.getText().toString()
            }
        })

        radioButton2.setOnClickListener(View.OnClickListener {
            if (radioButton2.isChecked) {
                Reponse = radioButton2.getText().toString()
            }
        })

        radioButton3.setOnClickListener(View.OnClickListener {
            if (radioButton3.isChecked) {
                Reponse = radioButton3.getText().toString()
            }
        })


        button2.setOnClickListener {
            suivant();
        }


    }

    /***********************************************************************************************************************************/

    private fun suivant() {
        var nbquiz = i+1
        textView.setText("Questions "+nbquiz+" /5")
        textView3.setText(questions[j][i])

        var result = questions[j+1][i]  //on recupere les reponses dans le tableau questions
        var separated = result.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()


        radioButton.setText(separated[0])
        radioButton2.setText(separated[1])
        radioButton3.setText(separated[2])

        if( Reponse.equals(reponses[i]) ){
            score++
        }
        textView5.setText("Score: "+score)

        separated = emptyArray()
        result = ""
        Reponse = ""
        i++
    }
    /***********************************************************************************************************************************/

    private fun answer() {



    }


    /***********************************************************************************************************************************/
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
    /***********************************************************************************************************************************/

}
