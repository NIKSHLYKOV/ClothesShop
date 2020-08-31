package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()
    val email: MutableLiveData<String> = MutableLiveData()
    val phone: MutableLiveData<String> = MutableLiveData()

    init {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        name.value = if (firebaseUser?.displayName != null) firebaseUser.displayName else "Шлыков"
        email.value = firebaseUser?.email
        phone.value = "+7(900)-207-13-56"
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}