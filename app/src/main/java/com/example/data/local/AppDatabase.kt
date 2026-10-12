package com.example.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.data.model.ModeProgress
import com.example.data.model.PlayerProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player_profile WHERE id = 1 LIMIT 1")
    fun getProfileFlow(): Flow<PlayerProfile?>

    @Query("SELECT * FROM player_profile WHERE id = 1 LIMIT 1")
    suspend fun getProfile(): PlayerProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: PlayerProfile)

    @Update
    suspend fun updateProfile(profile: PlayerProfile)

    @Query("UPDATE player_profile SET xp = xp + :gainedXp, totalWordsTyped = totalWordsTyped + :wordsTyped WHERE id = 1")
    suspend fun addXpAndWords(gainedXp: Int, wordsTyped: Int)
}

@Dao
interface ProgressDao {
    @Query("SELECT * FROM mode_progress")
    fun getAllProgress(): Flow<List<ModeProgress>>

    @Query("SELECT * FROM mode_progress WHERE modeKey = :key LIMIT 1")
    suspend fun getProgress(key: String): ModeProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgress(progress: ModeProgress)
}

@Database(
    entities = [PlayerProfile::class, ModeProgress::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun progressDao(): ProgressDao
}
