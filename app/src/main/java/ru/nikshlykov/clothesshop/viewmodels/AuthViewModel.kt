package ru.nikshlykov.clothesshop.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AuthViewModel constructor(private var firebaseAuth: FirebaseAuth) : ViewModel() {

    var logInStatus: MutableLiveData<Int> = MutableLiveData()

    private var executorService: ExecutorService = Executors.newFixedThreadPool(1)

    init {
        logInStatus.value = 0
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun signIn(email: String, password: String) {
        logInStatus.value = 1
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(executorService, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        logInStatus.postValue(2)
                    } else {
                        Log.w(
                            AuthViewModel::class.java.canonicalName,
                            "createUserWithEmail:failure",
                            task.exception
                        )
                        logInStatus.postValue(-1)
                    }
                })
        } else {
            logInStatus.value = -1
        }
    }
}