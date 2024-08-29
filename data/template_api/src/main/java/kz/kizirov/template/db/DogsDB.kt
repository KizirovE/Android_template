package kz.kizirov.template.db.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dogs::class], version = 1)
abstract class DogsDB : RoomDatabase() {
    abstract fun dogsDao(): DogsDao
}