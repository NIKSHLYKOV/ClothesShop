package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private var firebaseAuth: FirebaseAuth) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(AuthProfileFlowViewModel::class.java)) {
            return AuthProfileFlowViewModel(firebaseAuth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}