package ru.nikshlykov.clothesshop.di

import dagger.Component
import ru.nikshlykov.clothesshop.di.modules.FirebaseModule
import ru.nikshlykov.clothesshop.di.modules.ViewModelModule
import ru.nikshlykov.clothesshop.ui.activities.AuthActivity

@Component(modules = [FirebaseModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(): AppComponent
    }

    fun inject(activity: AuthActivity)
}