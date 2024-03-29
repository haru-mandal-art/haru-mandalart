package com.coldblue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coldblue.database.convert.LocalDateConvert
import com.coldblue.database.convert.LocalDateTimeConverter
import com.coldblue.database.convert.LocalTimeConverter
import com.coldblue.database.dao.AlarmDao
import com.coldblue.database.dao.CurrentGroupDao
import com.coldblue.database.dao.AppDao
import com.coldblue.database.dao.MandaDetailDao
import com.coldblue.database.dao.MandaKeyDao
import com.coldblue.database.dao.TodoDao
import com.coldblue.database.dao.TodoGroupDao
import com.coldblue.database.entity.AlarmEntity
import com.coldblue.database.entity.CurrentGroupEntity
import com.coldblue.database.entity.MandaDetailEntity
import com.coldblue.database.entity.MandaKeyEntity
import com.coldblue.database.entity.TodoEntity
import com.coldblue.database.entity.TodoGroupEntity

@Database(
    entities = [
        AlarmEntity::class,
        CurrentGroupEntity::class,
        MandaKeyEntity::class,
        MandaDetailEntity::class,
        TodoEntity::class,
        TodoGroupEntity::class,
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(LocalDateConvert::class, LocalDateTimeConverter::class, LocalTimeConverter::class,)
abstract class AppDataBase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
    abstract fun currentGroupDao(): CurrentGroupDao
    abstract fun mandaKeyDao(): MandaKeyDao
    abstract fun mandaDetailDao(): MandaDetailDao
    abstract fun todoDao(): TodoDao
    abstract fun todoGroupDao(): TodoGroupDao
    abstract fun haruMandalrtDao(): AppDao

}
