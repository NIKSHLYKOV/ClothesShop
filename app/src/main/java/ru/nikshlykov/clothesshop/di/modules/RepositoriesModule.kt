package ru.nikshlykov.clothesshop.di.modules

import dagger.Module
import dagger.Provides
import ru.nikshlykov.clothesshop.data.repositories.CategoryRepository
import ru.nikshlykov.clothesshop.data.repositories.ClothesCategoriesRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun providesClothesCategoriesRepository(): ClothesCategoriesRepository {
        return ClothesCategoriesRepository()
    }

    @Provides
    @Singleton
    fun providesCategoryRepository(): CategoryRepository {
        return CategoryRepository()
    }
}