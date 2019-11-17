package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    //ABOGOURIN AMINATH VERLAINE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val intent = intent //je recupere l'intent de la page
        val score = intent.getStringExtra("score") //je recupere la voaleur du score envoyer depuis la page de quizz dans mon intent
        textView4.setText(score+"/5") //j'affiche le score finale

        restart.setOnClickListener { //s'il clique sur le boutton recommencer le quizz
            val intent = Intent(this, QuizzActivity::class.java) //je cree un intent pour allez vers la page de quiz
            startActivity(intent)// je lance l'activité
        }

        acceuil.setOnClickListener { //s'il clique sur le boutton accueil
            val intent = Intent(this, MainActivity::class.java) //je cree un intent pour allez vers la page de principale
            startActivity(intent)// je lance l'activité
        }


    }

}