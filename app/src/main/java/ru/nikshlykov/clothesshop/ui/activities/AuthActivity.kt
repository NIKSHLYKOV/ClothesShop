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

    private lateinit var signInButton: MaterialButton
    private lateinit var signUpButton: MaterialButton

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        findViews()

        signInButton.setOnClickListener {
            signUpButton.isEnabled = false
            signInButton.isEnabled = false
            authViewModel.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        signUpButton.setOnClickListener {
            signInButton.isEnabled = false
            signUpButton.isEnabled = false
            authViewModel.createUser(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        authViewModel = ViewModelProvider.NewInstanceFactory().create(AuthViewModel::class.java)
        authViewModel.logInStatus.observe(this, Observer {
            when (it) {
                -1 -> {
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT)
                        .show()
                    signInButton.isEnabled = true
                    signUpButton.isEnabled = true
                    signInButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }
                2 -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    signInButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    signInButton.isEnabled = true
                    signUpButton.isEnabled = true

                }
            }
        })
    }

    private fun findViews() {
        emailEditText = findViewById(R.id.activity_auth___edit_text___email)
        passwordEditText = findViewById(R.id.activity_auth___edit_text___password)
        signInButton = findViewById(R.id.activity_auth___button___sign_in)
        signUpButton = findViewById(R.id.activity_auth___button___sign_up)
    }
}