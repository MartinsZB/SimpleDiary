package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.time.LocalDateTime
import kotlinx.android.synthetic.main.fragment_diary_list.*
import lv.zabarovski.martins.simplediary.Dbase.App
import lv.zabarovski.martins.simplediary.Dbase.Database
import lv.zabarovski.martins.simplediary.Dbase.Database.getInstance
import lv.zabarovski.martins.simplediary.Dbase.StoryDataItem
import java.util.*

val stories = mutableListOf(
    StoryDataItem("One", System.currentTimeMillis(),"This is first note"),
    StoryDataItem("Two", System.currentTimeMillis(),"This is Second note"),
    StoryDataItem("Three", System.currentTimeMillis(),"This is third note")
)

class DiaryList : Fragment(), AdapterClickListener {

    private val db get() = Database.getInstance(requireActivity())
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
        adapter = DiaryRecAdapter(this,stories)
        mainDiaryItems.adapter = adapter
        stories.addAll(db.diaryStoriesDao().getAllStories())
        newStoryButton.setOnClickListener {addStory()}
    }
    private fun addStory() {

        val intent = Intent(getActivity(), AddStory::class.java).apply{
            putExtra(REQUEST_CODE, "123")
        }
        startActivityForResult(intent, ADD_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_REQUEST_CODE || requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            data?.let{
                val title = data.getStringExtra(GET_STORY_TITLE)
                val story = data.getStringExtra(GET_STORY_TEXT)

                stories.add(StoryDataItem(title.toString(), System.currentTimeMillis(),story.toString()))
            }
            adapter.notifyDataSetChanged()
        }
    }



    override fun itemClicked(story: StoryDataItem) {
//        Toast.makeText(getActivity(),story.note,Toast.LENGTH_LONG).show()
        val intent = Intent(getActivity(), AddStory::class.java).apply {
            putExtra(REQUEST_CODE, "124")
            putExtra(SET_STORY_TITLE, story.title)
            putExtra(SET_STORY_TEXT, story.note)
        }

        startActivityForResult(intent, EDIT_REQUEST_CODE)

    }

    companion object {
        const val ADD_REQUEST_CODE = 123
        const val EDIT_REQUEST_CODE = 124
        const val REQUEST_CODE = "lv.zabarovski.martins.TITLE_REPLY"
        const val GET_STORY_TITLE = "lv.zabarovski.martins.TITLE_REPLY"
        const val SET_STORY_TITLE = "lv.zabarovski.martins.TITLE_SEND"
        const val GET_STORY_TEXT = "lv.zabarovski.martins.STORY_REPLY"
        const val SET_STORY_TEXT = "lv.zabarovski.martins.TEXT_SEND"
    }

}
fun deleteItem(position: Int) {
    stories.removeAt(position)
}

interface AdapterClickListener {
    fun itemClicked(story: StoryDataItem)
}

