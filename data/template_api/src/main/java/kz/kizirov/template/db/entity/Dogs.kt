package kz.kizirov.template.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Dogs(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "message") val message: String?,
    @ColumnInfo(name = "status") val status: String?
)