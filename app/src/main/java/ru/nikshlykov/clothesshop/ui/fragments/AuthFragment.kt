package ru.nikshlykov.clothesshop.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.nikshlykov.clothesshop.App
import ru.nikshlykov.clothesshop.R
import ru.nikshlykov.clothesshop.ui.activities.MainActivity
import ru.nikshlykov.clothesshop.viewmodels.AuthViewModel
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory
import javax.inject.Inject

class AuthFragment : Fragment() {

    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    private lateinit var signInButton: MaterialButton
    private lateinit var signUpButton: MaterialButton

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var authViewModel: AuthViewModel

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = viewModelFactory.create(AuthViewModel::class.java)

        authViewModel.logInStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                -1 -> {
                    Toast.makeText(activity, "Ошибка", Toast.LENGTH_SHORT)
                        .show()
                    signInButton.isEnabled = true
                    signUpButton.isEnabled = true
                    signInButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                }
                2 -> {
                    startActivity(Intent(activity, MainActivity::class.java))
                    signInButton.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    signInButton.isEnabled = true
                    signUpButton.isEnabled = true
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews(view)

        signInButton.setOnClickListener {
            signUpButton.isEnabled = false
            signInButton.isEnabled = false
            authViewModel.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        signUpButton.setOnClickListener {
            signInButton.isEnabled = false
            signUpButton.isEnabled = false
            authViewModel.createUser(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! < 6) {
                    passwordEditText.error = "Пароль должен состоять минимум из 6 символов"
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        authViewModel.checkUser()
    }

    private fun findViews(view: View) {
        emailEditText = view.findViewById(R.id.activity_auth___edit_text___email)
        passwordEditText = view.findViewById(R.id.activity_auth___edit_text___password)
        signInButton = view.findViewById(R.id.activity_auth___button___sign_in)
        signUpButton = view.findViewById(R.id.activity_auth___button___sign_up)
    }
}