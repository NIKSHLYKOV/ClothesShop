package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import ru.nikshlykov.clothesshop.data.repositories.ClothesCategoriesRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private var firebaseAuth: FirebaseAuth,
    private var clothesCategoriesRepository: ClothesCategoriesRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(ClothesCategoriesViewModel::class.java)) {
            return ClothesCategoriesViewModel(clothesCategoriesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}