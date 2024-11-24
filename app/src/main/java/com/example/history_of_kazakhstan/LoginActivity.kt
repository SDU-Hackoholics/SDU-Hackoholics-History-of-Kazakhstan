package com.example.history_of_kazakhstan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.history_of_kazakhstan.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()



        binding.buttonLogin.setOnClickListener {

            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (DatabaseHandler().checkLogin(applicationContext, username, password)) {
                Toast.makeText(applicationContext, "Welcome back, $username!", Toast.LENGTH_SHORT).show()

                DatabaseHandler().updateLastEnter(applicationContext, username)

                DatabaseHandler().updateCurrentUser(applicationContext, username)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}
