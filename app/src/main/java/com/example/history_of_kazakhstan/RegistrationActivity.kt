package com.example.history_of_kazakhstan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.history_of_kazakhstan.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    private var lastTextEditTime = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val delay = 1000L // 1 second delay

    private var is_username_exists = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lastTextEditTime = System.currentTimeMillis()
                handler.removeCallbacks(checkTextChangedRunnable)
                handler.postDelayed(checkTextChangedRunnable, delay)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.buttonRegistration.setOnClickListener{

            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != confirmPassword) {
                Toast.makeText(applicationContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (is_username_exists) {
                Toast.makeText(applicationContext, "Username already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                Toast.makeText(applicationContext, "Welcome, $username!", Toast.LENGTH_SHORT).show()

                DatabaseHandler().addUser(applicationContext, username, password)

                DatabaseHandler().addAccountCreated(applicationContext, username)
                DatabaseHandler().updateLastEnter(applicationContext, username)

                DatabaseHandler().updateCurrentUser(applicationContext, username)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }


    private val checkTextChangedRunnable = Runnable {
        if (System.currentTimeMillis() > lastTextEditTime + delay - 500) {
            // Text has not changed for more than 1 second
            runOnUiThread {
                // TODO Get username list from users_table in host='34.32.36.44', user='sdu-hackoholics', password='18o-m01-a2h-8aZ', database='history-of-kazakhstan'
                val username = binding.username.text.toString()

                if (DatabaseHandler().checkUsernameExists(applicationContext, username)) {
                    is_username_exists = true
//                    Toast.makeText(applicationContext, "Username exists", Toast.LENGTH_SHORT).show()
                } else {
                    is_username_exists = false
//                    Toast.makeText(applicationContext, "Username does not exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}