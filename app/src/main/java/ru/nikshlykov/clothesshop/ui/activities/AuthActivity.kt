package ru.nikshlykov.clothesshop.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.viewmodels.AuthViewModel

class AuthActivity : AppCompatActivity() {

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    private lateinit var logInButton: MaterialButton

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        findViews()

        logInButton.setOnClickListener { view ->
            view.isEnabled = false
            authViewModel.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        authViewModel = ViewModelProvider.NewInstanceFactory().create(AuthViewModel::class.java)
        authViewModel.logInStatus.observe(this, Observer {
            when (it) {
                -1 -> {
                    Toast.makeText(this, "Неправильный пароль или логин", Toast.LENGTH_SHORT)
                        .show()
                    logInButton.isEnabled = true
                }
                1 -> logInButton.setBackgroundColor(resources.getColor(R.color.colorAccent))
                2 -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    logInButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    logInButton.isEnabled = true

                }
            }
        })
    }

    private fun findViews() {
        emailEditText = findViewById(R.id.activity_auth___edit_text___email)
        passwordEditText = findViewById(R.id.activity_auth___edit_text___password)
        logInButton = findViewById(R.id.activity_auth___button___log_in)
    }
}