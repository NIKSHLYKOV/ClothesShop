package ru.nikshlykov.clothesshop.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.nikshlykov.clothesshop.viewmodels.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}