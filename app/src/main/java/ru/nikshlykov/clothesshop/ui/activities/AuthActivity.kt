package ru.nikshlykov.clothesshop.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import ru.nikshlykov.clothesshop.R

class AuthActivity : AppCompatActivity() {

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        findViews()
    }

    private fun findViews(){
        emailEditText = findViewById(R.id.activity_auth___edit_text___email)
        passwordEditText = findViewById(R.id.activity_auth___edit_text___password)
    }
}