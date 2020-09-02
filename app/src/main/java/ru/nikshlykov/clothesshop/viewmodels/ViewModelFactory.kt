package ru.nikshlykov.clothesshop.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import ru.nikshlykov.clothesshop.data.repositories.CategoryRepository
import ru.nikshlykov.clothesshop.data.repositories.ClothesCategoriesRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private var firebaseAuth: FirebaseAuth,
    private var clothesCategoriesRepository: ClothesCategoriesRepository,
    private var categoryRepository: CategoryRepository
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(AuthProfileFlowViewModel::class.java)) {
            return AuthProfileFlowViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(firebaseAuth) as T
        } else if (modelClass.isAssignableFrom(ClothesCategoriesViewModel::class.java)) {
            return ClothesCategoriesViewModel(clothesCategoriesRepository) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(categoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}