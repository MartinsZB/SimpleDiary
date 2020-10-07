package lv.zabarovski.martins.simplediary.Dbase

import android.app.Application
import androidx.room.Room

class App : Application() {

    val db by lazy {
        Room.databaseBuilder(this, DiaryDatabase::class.java, "diary-db")
            .allowMainThreadQueries()
            .build()
    }
}