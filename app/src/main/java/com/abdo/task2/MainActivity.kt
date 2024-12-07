package com.abdo.task2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var signInLayout: LinearLayout
    private lateinit var movieListLayout: LinearLayout
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var tvWelcome: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnMore: Button

    private val movieList = mutableListOf<Movie>()
    private lateinit var adapter: MovieAdapter

    private val movieImages = listOf(
        R.drawable.movie1, R.drawable.movie2, R.drawable.movie3,
        R.drawable.movie4, R.drawable.movie5, R.drawable.movie6,
        R.drawable.movie7, R.drawable.movie8, R.drawable.movie9,
        R.drawable.movie10, R.drawable.movie11, R.drawable.movie12,
        R.drawable.movie13, R.drawable.movie14, R.drawable.movie15
    )

    private val movieTitles = listOf(
        "Iron Man (2008)", "Iron Man 2 (2010)", "Iron Man 3 (2013)",
        "Spider-Man (2002)", "Spider-Man 2 (2004)", "Spider-Man 3 (2007)",
        "The Dark Knight (2008)", "The Dark Knight Rises (2012)", "Batman Begins (2005)",
        "Avengers: Endgame (2019)", "Avengers: Infinity War (2018)", "Avengers: Age of Ultron (2015)",
        "Captain America: The First Avenger (2011)", "Captain America: The Winter Soldier (2014)", "Captain America: Civil War (2016)"
    )

    private val movieDescriptions = listOf(
        "Tony Stark builds a high-tech suit to fight evil after being captured by terrorists.",
        "Tony Stark faces a new enemy, Whiplash, and struggles with his inner demons.",
        "Tony Stark battles a powerful terrorist and confronts his past mistakes.",
        "Peter Parker becomes Spider-Man and fights the Green Goblin to protect New York City.",
        "Peter Parker battles Doctor Octopus while trying to maintain his personal life.",
        "Spider-Man faces new enemies and struggles with his dark side after bonding with an alien symbiote.",
        "Batman battles the Joker in an intense showdown for Gotham's soul.",
        "Bruce Wayne returns as Batman to stop Bane from destroying Gotham City.",
        "The origin story of how Bruce Wayne became the iconic Dark Knight of Gotham.",
        "The Avengers unite to reverse Thanos' snap and restore balance to the universe.",
        "The Avengers face their toughest battle against Thanos to save the universe.",
        "The Avengers assemble to stop Ultron from wiping out humanity.",
        "Steve Rogers transforms into Captain America to fight Hydra during World War II.",
        "Steve Rogers uncovers a conspiracy while battling the Winter Soldier in Washington, D.C.",
        "The Avengers are divided as Captain America and Iron Man clash over government control."
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        signInLayout = findViewById(R.id.signInLayout)
        movieListLayout = findViewById(R.id.movieListLayout)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        tvWelcome = findViewById(R.id.tvWelcome)
        recyclerView = findViewById(R.id.recyclerView)
        btnMore = findViewById(R.id.btnMore)


        adapter = MovieAdapter(movieList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        btnSignIn.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                etUsername.error = "Please enter a username"
                etPassword.error = "Please enter a password"
            } else if (password.length < 8 || !password.any { it.isDigit() } || !password.any { it.isLetter() }) {
                etPassword.error = "Password must be at least 8 characters with letters and numbers"
            } else {
                signInLayout.visibility = LinearLayout.GONE
                movieListLayout.visibility = LinearLayout.VISIBLE
                tvWelcome.text = "Welcome: $username"


                loadMovies(5)
            }
        }


        btnMore.setOnClickListener {
            if (movieList.size < 15) {
                loadMovies(5)
            }
            if (movieList.size >= 15) {
                btnMore.isEnabled = false
            }
        }
    }

    private fun loadMovies(count: Int) {
        val newMovies = (movieList.size until (movieList.size + count)).map {
            Movie(
                movieTitles[it],
                movieDescriptions[it],
                movieImages[it]
            )
        }
        movieList.addAll(0, newMovies)
        adapter.notifyDataSetChanged()
    }
}
