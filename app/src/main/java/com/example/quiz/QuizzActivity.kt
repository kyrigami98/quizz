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
    //ABOGOURIN AMINATH VERLAINE
    
    /**********Les variables globales du quiz********************************************************************************/

    var starttime: Long = 0
    var i = 0
    var j = 0
    var Reponse = ""
    var score = 0

    //ceci est le tableau contenant les questions et les reponses possibles
    val questions = arrayOf(
        arrayOf("Qui es tu?", "Ton age?", "Ton sexe?", "Ton nom?", "Ton adresse?"),
        arrayOf("Aminath/bob/cool","5/15/40", "F/M/FM", "Bof/baf/bif", "Neant/4 rue bebe/4 avenue")
    )

    //ceci est le tableau des vraies reponses
    val reponses = arrayOf("Aminath","15", "F", "bif", "4 rue bebe")


    /***********************************************************************************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz)

        suivant(); //ici j'appelle une fois la fonction suivant pour initialiser le quizz

        startTimer(360000) //ici je lance ma fonction pour deconter le temps de 6 min

        /********Ceci est l'ecoute des boutons de reponses radios de la page***********************************************************************/

        radioButton.setOnClickListener(View.OnClickListener {
            if (radioButton.isChecked) { //si se boutton de reponse est cliquer
                Reponse = radioButton.getText() as String//je change la variable Reponse de l'utilisateur avec la valeur du boutton
                }
        })

        radioButton2.setOnClickListener(View.OnClickListener {
            if (radioButton2.isChecked) {//si se boutton de reponse est cliquer
                Reponse = radioButton2.getText() as String//je change la variable Reponse de l'utilisateur avec la valeur du boutton
               }
        })

        radioButton3.setOnClickListener(View.OnClickListener {
            if (radioButton3.isChecked) {//si se boutton de reponse est cliquer
                Reponse = radioButton3.getText() as String//je change la variable Reponse de l'utilisateur avec la valeur du boutton
              }
        })

        /*******ici on ecoute le boutton sivant pour lorsqu'il passe a la question suivante***********************************************************************************/

        button2.setOnClickListener {

            if( Reponse.equals(reponses[i]) ){ //ici je verfie si la reponse donné par l'utilisateur est egal a la reponse de mon tableau de vraies reponses
                score++ //j'incremente le score d'un point
            }

            textView5.setText("Score: "+score) //ici j'update le score dans la vue

            if (i<=3){ // si on est pas sur la dernier question
                i++ //j'augmente i pour passé a la question suivante
                suivant() // j"appele ma fonction suivant pour qu'il change le contenu de ma vue avec la question suivante
            }else{ //sinon si on est sur la dernier question
             fin()// j'appelle la fonction fin pour aller vers la page de score automatiquement
            }

        }
        /***********************************************************************************************************************************/


    }

    /********* ma fonction suivant  change le contenu de ma vue avec la question suivante****************************************************************/

    private fun suivant() {

            var nbquiz = i+1  //ceci est juste la valeur de la pagination es question EX: Question 4/5, Question 2/5
            textView.setText("Questions " + nbquiz + " /5") //ici j'affiche ma pagination dans une textview

            textView3.setText(questions[j][i]) //ici je selection la question suivante dans le tableau des questions

            var result = questions[j + 1][i]  //on recupere les reponses possibles a la question selectionnez dans le tableau questions
            var separated = result.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() //ici on decoupe les reponses en fonction du /

            radioButton.setText(separated[0]) //ici on affiche la premiere reponse possible dans la vue sur mon boutton radio
            radioButton2.setText(separated[1])//ici on affiche la deuxieme reponse possible dans la vue sur mon boutton radio2
            radioButton3.setText(separated[2])//ici on affiche la troisieme reponse possible dans la vue sur mon boutton radio3


        //ici on vide les variable pour ne pas voir des residues dans le tableau au prochain passe dans la fonction
            separated = emptyArray()
            result = ""

    }


    private fun fin() { //cette fonction envoie vers la page de score
        val intent = Intent(this@QuizzActivity, ScoreActivity::class.java) //je cree un intent pour allez vers la page de de score
        intent.putExtra("score",score.toString()) //j'envoi le score sur la page score
        startActivity(intent)// je lance l'activité score
    }



    /************Ceci est le timer de mon application**************************************************************/
    //cette fontion prend en paramentre le temps en milliseconde et la tranforme en muniteur

    private fun startTimer(noOfMinutes: Int) {
        val countDownTimer = object : CountDownTimer(noOfMinutes.toLong(), 1000) { //ceci est une objet timer de Kotlin
            override fun onTick(millisUntilFinished: Long) {

                val hms = String.format(
                    "%02d:%02d", //ceci est le format sous laquelle on veux le temps
                    TimeUnit.MILLISECONDS.toMinutes(
                        millisUntilFinished
                    ) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(
                        millisUntilFinished
                    ) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                )
                timer.setText(hms)//on affiche le timer dans notre vue sur l'objet timer
            }

            override fun onFinish() {//si le temps se termine
                timer.setText("TIME'S UP!!") //j'affiche se message
                fin() //et j'appelle la fonction fin pour aller vers la page de score automatiquement
            }
        }.start()

    }
    /***********************************************************************************************************************************/

}
