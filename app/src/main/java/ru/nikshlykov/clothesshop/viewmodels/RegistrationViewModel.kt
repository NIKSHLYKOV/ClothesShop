package ru.nikshlykov.clothesshop.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RegistrationViewModel constructor(private var firebaseAuth: FirebaseAuth) : ViewModel() {

    var registrationStatus: MutableLiveData<Int> = MutableLiveData()

    private var executor: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        registrationStatus.value = 0
    }

    fun register(email: String, password: String, name: String, photoUri: String) {
        // TODO Написать обработку внезапных ошибок.
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(executor, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userProfileChangeRequest = UserProfileChangeRequest.Builder().apply {
                        displayName = name
                        this.photoUri = Uri.parse(photoUri)
                    }.build()
                    firebaseAuth.currentUser?.updateProfile(userProfileChangeRequest)
                        ?.addOnCompleteListener(executor, OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                registrationStatus.postValue(1)
                            }
                        })
                }
            })
    }
}