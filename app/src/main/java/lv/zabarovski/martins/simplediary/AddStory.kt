package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_story.*
import lv.zabarovski.martins.simplediary.Dbase.Database
import lv.zabarovski.martins.simplediary.Dbase.StoryDataItem

import lv.zabarovski.martins.simplediary.DiaryList.Companion.REQUEST_CODE
import lv.zabarovski.martins.simplediary.DiaryList.Companion.STORY_ID


class AddStory : AppCompatActivity(){
    private val db get() = Database.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)
        val requestID = intent.getStringExtra(REQUEST_CODE)?.toInt()

        if(requestID == 123) {
            updateStoryButton.setOnClickListener{
                val title = storyTitle.text
                val story = storyContent.text
                val newItem =
                    StoryDataItem(title.toString(), System.currentTimeMillis(), story.toString())
                newItem.uid = db.diaryStoriesDao().insertAllStories(newItem).first()
                val result = Intent().apply {
                putExtra(STORY_ID, newItem.uid)
                }
            setResult(Activity.RESULT_OK,result)
            finish()
            }
        } else {
            updateStoryButton.text = getString(R.string.update_story)
            val storyID = intent.getLongExtra(STORY_ID,0)
            val story = db.diaryStoriesDao().getItemById(storyID)
            storyTitle.setText(story.title)
            storyContent.setText(story.note)
            updateStoryButton.setOnClickListener {
                val newTitle = storyTitle.text
                val newStory = storyContent.text
                db.diaryStoriesDao().update(
                    story.copy(newTitle.toString(),System.currentTimeMillis(),newStory.toString())
                )
                val result = Intent().apply {
                    putExtra(STORY_ID, story.uid)
                }
                setResult(Activity.RESULT_OK,result)
                finish()
            }
        }
    }
}