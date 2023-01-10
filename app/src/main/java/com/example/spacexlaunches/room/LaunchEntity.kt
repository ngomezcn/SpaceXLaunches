package com.example.spacexlaunches.room

import android.app.Application
import androidx.room.*

@Entity(tableName = "LaunchEntity")
data class LaunchEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String,
    var phoneNumber: String)

@Dao
interface LaunchDAO {
    @Query("SELECT * FROM LaunchEntity")
    fun getAll(): MutableList<LaunchEntity>
    @Insert
    fun add(launchEntity: LaunchEntity)
    @Update
    fun update(launchEntity: LaunchEntity)
    @Delete
    fun delete(launchEntity: LaunchEntity)
}

@Database(entities = [LaunchEntity::class], version = 1)
abstract class LaunchDatabase: RoomDatabase() {
    abstract fun launchDao(): LaunchDAO
}

class LaunchApplication: Application() {
    companion object {
        lateinit var database: LaunchDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            LaunchDatabase::class.java,
            "LaunchDatabase").build()
    }
}
