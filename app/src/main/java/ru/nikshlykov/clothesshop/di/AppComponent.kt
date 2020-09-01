package ru.nikshlykov.clothesshop.di

import dagger.Component
import ru.nikshlykov.clothesshop.di.modules.FirebaseModule
import ru.nikshlykov.clothesshop.di.modules.RepositoriesModule
import ru.nikshlykov.clothesshop.di.modules.ViewModelModule
import ru.nikshlykov.clothesshop.ui.MainActivity
import ru.nikshlykov.clothesshop.ui.fragments.AuthFragment
import ru.nikshlykov.clothesshop.ui.flowfragments.AuthProfileFlowFragment
import ru.nikshlykov.clothesshop.ui.fragments.RegistrationFragment
import ru.nikshlykov.clothesshop.ui.fragments.ClothesCategoriesFragment
import ru.nikshlykov.clothesshop.ui.fragments.ProfileFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModule::class, ViewModelModule::class, RepositoriesModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(activity: MainActivity)

    fun inject(fragment: AuthProfileFlowFragment)

    fun inject(fragment: AuthFragment)
    fun inject(fragment: ClothesCategoriesFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: RegistrationFragment)
}