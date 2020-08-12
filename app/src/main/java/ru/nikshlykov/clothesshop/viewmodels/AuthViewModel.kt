package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AuthViewModel : ViewModel() {

    var logInStatus: MutableLiveData<Int> = MutableLiveData()

    private var executorService: ExecutorService = Executors.newFixedThreadPool(1)

    init {
        logInStatus.value = 0
    }

    fun signIn(email: String, password: String) {
        if (email == "nikita" && password == "123") {
            logInStatus.value = 1
            delayLogIn()
        } else {
            logInStatus.value = -1
        }
    }

    private fun delayLogIn(){
        executorService.execute {
            Thread.sleep(3000)
            logInStatus.postValue(2)
        }
    }
}