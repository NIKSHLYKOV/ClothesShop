package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel constructor(private var firebaseAuth: FirebaseAuth): ViewModel() {

    fun signOut(){
        firebaseAuth.signOut()
    }
}