package ru.nikshlykov.clothesshop

import android.app.Application
import ru.nikshlykov.clothesshop.di.AppComponent
import ru.nikshlykov.clothesshop.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create()
    }
}