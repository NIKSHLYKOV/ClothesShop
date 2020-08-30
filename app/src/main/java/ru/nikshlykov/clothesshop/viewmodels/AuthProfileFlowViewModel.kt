package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthProfileFlowViewModel constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    fun isUserAuthenticated(): Boolean{
        return firebaseAuth.currentUser != null
    }
}