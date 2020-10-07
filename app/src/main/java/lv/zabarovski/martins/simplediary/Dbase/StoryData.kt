package lv.zabarovski.martins.simplediary.Dbase

import androidx.room.*
import java.time.LocalDateTime

@Entity(tableName = "diary_stories")
data class StoryDataItem(
    val title: String,
    val date: Long,
    val note: String,
    @PrimaryKey(autoGenerate = true) var uid: Long = 0
)

@Dao
interface DiaryStoriesDao {
    @Query("SELECT * FROM diary_stories")
    fun getAllStories(): List<StoryDataItem>

    @Query("SELECT * FROM diary_stories WHERE uid = :itemId")
    fun getItemById(itemId: Long): StoryDataItem

    @Insert
    fun insertAllStories(vararg items: StoryDataItem): List<Long>

    @Update
    fun update(item: StoryDataItem)


    @Delete
    fun delete(item: StoryDataItem)
}