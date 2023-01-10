package com.example.spacexlaunches.room

import android.app.Application
import androidx.room.*

@Entity(tableName = "RocketEntity")
data class RocketEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,

    var name: String,
    var success_rate_pct: Float,
    var cost_per_launch: Int)

@Entity(tableName = "LaunchEntity",
    foreignKeys = [ForeignKey(entity = LaunchEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("rocketId"),
    onDelete = ForeignKey.CASCADE)]
)
data class LaunchEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,

    var dateUtc: String,
    var failures_reason: String,
    var details: String,
    var success: Boolean,
    var flightNumber: Int,
    var links_patch_large: String,
    var rocketId: Long)

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

@Dao
interface RocketDAO {
    @Query("SELECT * FROM RocketEntity")
    fun getAll(): MutableList<RocketEntity>
    @Insert
    fun add(launchEntity: RocketEntity)
    @Update
    fun update(launchEntity: RocketEntity)
    @Delete
    fun delete(launchEntity: RocketEntity)
}

@Database(entities = [LaunchEntity::class], version = 1)
abstract class LaunchDatabase: RoomDatabase() {
    abstract fun launchDao(): LaunchDAO
    abstract fun rocketDao(): RocketDAO
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
