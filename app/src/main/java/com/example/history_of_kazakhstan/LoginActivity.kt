package com.example.history_of_kazakhstan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history_of_kazakhstan.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        binding.btnLogin.setOnClickListener {
//            // Handle login logic here
//        }
    }
}
