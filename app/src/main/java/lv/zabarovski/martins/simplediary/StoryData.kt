package lv.zabarovski.martins.simplediary

import java.time.LocalDateTime

data class StoryDataItem(
    val title: String,
    val date: LocalDateTime,
    val note: String
)