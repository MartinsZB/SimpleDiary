package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_story.*
import lv.zabarovski.martins.simplediary.DiaryList.Companion.GET_STORY_TEXT
import lv.zabarovski.martins.simplediary.DiaryList.Companion.GET_STORY_TITLE
import lv.zabarovski.martins.simplediary.DiaryList.Companion.ADD_REQUEST_CODE
import lv.zabarovski.martins.simplediary.DiaryList.Companion.REQUEST_CODE
import lv.zabarovski.martins.simplediary.DiaryList.Companion.SET_STORY_TEXT
import lv.zabarovski.martins.simplediary.DiaryList.Companion.SET_STORY_TITLE


class AddStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)
        val requestID = intent.getStringExtra(REQUEST_CODE)?.toInt()

        if(requestID == 123) {
            updateStoryButton.setOnClickListener{
                val title = storyTitle.text
                val story = storyContent.text
                val result = Intent().apply {
                putExtra(GET_STORY_TITLE, title.toString())
                putExtra(GET_STORY_TEXT, story.toString())
                }
            setResult(Activity.RESULT_OK,result)
            finish()
            }
        } else {
            updateStoryButton.text = getString(R.string.update_story)
            val title = intent.getStringExtra(SET_STORY_TITLE)
            val story = intent.getStringExtra(SET_STORY_TEXT)
            storyTitle.setText(title)
            storyContent.setText(story)
            updateStoryButton.setOnClickListener {
                val newTitle = storyTitle.text
                val newStory = storyContent.text
                val result = Intent().apply {
                    putExtra(GET_STORY_TITLE, newTitle.toString())
                    putExtra(GET_STORY_TEXT, newStory.toString())
                }
                setResult(Activity.RESULT_OK,result)
                finish()
            }
        }
    }
}