package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import java.time.LocalDateTime
import androidx.fragment.app.*
import kotlinx.android.synthetic.main.fragment_diary_list.*
import lv.zabarovski.martins.simplediary.DiaryList.Companion.EDIT_REQUEST_CODE

val stories = mutableListOf(
    StoryDataItem("One", LocalDateTime.now(),"This is first note"),
    StoryDataItem("Two", LocalDateTime.now(),"This is Second note"),
    StoryDataItem("Three", LocalDateTime.now(),"This is third note")
)

class DiaryList : Fragment() {


    private lateinit var adapter: DiaryRecAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diary_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiaryRecAdapter(stories)
        mainDiaryItems.adapter = adapter
        newStoryButton.setOnClickListener {addStory()}
    }
    private fun addStory() {
        val intent = Intent(getActivity(), AddStory::class.java).apply{}
        startActivityForResult(intent, REPLAY_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REPLAY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            data?.let{
                val title = data.getStringExtra(GET_STORY_TITLE)
                val story = data.getStringExtra(GET_STORY_TEXT)
                stories.add(StoryDataItem(title.toString(), LocalDateTime.now(),story.toString()))
            }
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val REPLAY_REQUEST_CODE = 123
        const val EDIT_REQUEST_CODE = 124
        const val GET_STORY_TITLE = "lv.zabarovski.martins.TITLE_REPLY"
        const val GET_STORY_TEXT = "lv.zabarovski.martins.STORY_REPLY"
        const val SEND_STORY_TITLE = "lv.zabarovski.martins.TITLE_SEND"
        const val SEND_STORY_TEXT = "lv.zabarovski.martins.STORY_SEND"
    }

}
fun deleteItem(position: Int) {
    stories.removeAt(position)
}

//fun editStory(title: String, text: String){
//    val intent = Intent(DiaryList(),AddStory::class.java).apply { }
//    startActivityForResult(intent,EDIT_REQUEST_CODE)
//}