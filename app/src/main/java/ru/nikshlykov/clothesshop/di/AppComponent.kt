package ru.nikshlykov.clothesshop.di

import dagger.Component
import ru.nikshlykov.clothesshop.di.modules.FirebaseModule
import ru.nikshlykov.clothesshop.di.modules.ViewModelModule
import ru.nikshlykov.clothesshop.ui.activities.AuthActivity
import ru.nikshlykov.clothesshop.ui.activities.MainActivity
import ru.nikshlykov.clothesshop.ui.fragments.ProfileFragment

@Component(modules = [FirebaseModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)

    fun inject(fragment: ProfileFragment)
}