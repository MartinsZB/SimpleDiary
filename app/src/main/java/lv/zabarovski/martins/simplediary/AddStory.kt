package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_story.*
import lv.zabarovski.martins.simplediary.DiaryList.Companion.GET_STORY_TEXT
import lv.zabarovski.martins.simplediary.DiaryList.Companion.GET_STORY_TITLE
import lv.zabarovski.martins.simplediary.DiaryList.Companion.REPLAY_REQUEST_CODE
import lv.zabarovski.martins.simplediary.DiaryList.Companion.SEND_STORY_TEXT
import lv.zabarovski.martins.simplediary.DiaryList.Companion.SEND_STORY_TITLE


class AddStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)
        if(REPLAY_REQUEST_CODE == 123) {
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
            //updateStoryButton.text = "+id/strings/update_story"
            storyTitle.setText(SEND_STORY_TITLE.toString())
            storyContent.setText(SEND_STORY_TEXT.toString())
            updateStoryButton.setOnClickListener {

            }
        }
    }
}