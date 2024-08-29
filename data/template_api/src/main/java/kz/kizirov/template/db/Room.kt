package kz.kizirov.template.db.entity

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module


val dbDogs = module {
    single {
        val context: Context by inject<Context>()
        Room.databaseBuilder(
            context,
            DogsDB::class.java, "database-dogs"
        ).build()
    }
}
