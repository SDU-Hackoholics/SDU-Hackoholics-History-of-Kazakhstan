package com.example.history_of_kazakhstan

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.appcompat.app.AppCompatActivity
import com.example.history_of_kazakhstan.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        DatabaseHandler().checkAndCreateJsonFile(applicationContext)

        val current_user = DatabaseHandler().getKeyValue(applicationContext, "current_user").toString()

        if (current_user != "") {
            DatabaseHandler().updateLastEnter(applicationContext, current_user)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonHaveAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCreateAccount.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
