package com.example.history_of_kazakhstan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.history_of_kazakhstan.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        binding.btnRegister.setOnClickListener {
//            // Handle registration logic here
//        }
    }
}
