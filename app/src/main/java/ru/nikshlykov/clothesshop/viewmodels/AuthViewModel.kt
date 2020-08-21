package ru.nikshlykov.clothesshop.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AuthViewModel constructor(private var firebaseAuth: FirebaseAuth): ViewModel() {

    var logInStatus: MutableLiveData<Int> = MutableLiveData()

    private var listener: OnCompleteListener<AuthResult>

    private var executorService: ExecutorService = Executors.newFixedThreadPool(1)

    init {
        logInStatus.value = 0
        firebaseAuth = FirebaseAuth.getInstance()

        listener = OnCompleteListener { task ->
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
        }
    }

    fun checkUser(){
        val user:FirebaseUser? = firebaseAuth.currentUser
        if (user != null){
            logInStatus.value = 2
        }
    }

    fun createUser(email: String, password: String) {
        logInStatus.value = 1
        if (email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(executorService, listener)
        } else {
            logInStatus.value = -1
        }
    }

    fun signIn(email: String, password: String) {
        logInStatus.value = 1
        if (email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(executorService, listener)
        } else {
            logInStatus.value = -1
        }
    }
}