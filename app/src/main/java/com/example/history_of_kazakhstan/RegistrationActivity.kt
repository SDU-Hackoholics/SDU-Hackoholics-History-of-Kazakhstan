package com.example.history_of_kazakhstan

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.history_of_kazakhstan.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    private var lastTextEditTime = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val delay = 1000L // 1 second delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        val editTextUsername = findViewById<EditText>(R.id.username)

        editTextUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lastTextEditTime = System.currentTimeMillis()
                handler.removeCallbacks(checkTextChangedRunnable)
                handler.postDelayed(checkTextChangedRunnable, delay)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.buttonRegistration.setOnClickListener{



        }
    }


    private val checkTextChangedRunnable = Runnable {
        if (System.currentTimeMillis() > lastTextEditTime + delay - 500) {
            // Text has not changed for more than 1 second
            runOnUiThread {
                // TODO Get username list from users_table in host='34.32.36.44', user='sdu-hackoholics', password='18o-m01-a2h-8aZ', database='history-of-kazakhstan'
            }
        }
    }

}