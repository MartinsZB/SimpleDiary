package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_diary_list.*
import lv.zabarovski.martins.simplediary.Dbase.Database
import lv.zabarovski.martins.simplediary.Dbase.StoryDataItem


class DiaryList : Fragment(), AdapterClickListener {
    private val stories = mutableListOf<StoryDataItem>()
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
        adapter = DiaryRecAdapter(this, stories)
        mainDiaryItems.adapter = adapter
        stories.addAll(db.diaryStoriesDao().getAllStories())
        newStoryButton.setOnClickListener { addStory() }
        newImageButton.setOnClickListener { addImage() }
    }

    private fun addStory() {

        val intent = Intent(getActivity(), AddStory::class.java).apply {
            putExtra(REQUEST_CODE, "123")
        }
        startActivityForResult(intent, ADD_REQUEST_CODE)

    }
    private fun addImage() {

        val intent = Intent(getActivity(), AddImage::class.java).apply {
            putExtra(REQUEST_CODE, "126")
        }
        startActivityForResult(intent, ADD_REQUEST_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val storyID = data.getLongExtra(STORY_ID,0)
                val story = db.diaryStoriesDao().getItemById(storyID)
                stories.add(story)
            }
            adapter.notifyDataSetChanged()
        }else if (requestCode == EDIT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val storyID = data.getLongExtra(STORY_ID,0)
                val story = db.diaryStoriesDao().getItemById(storyID)
                val position = stories.indexOfFirst { it.uid == story.uid }
                stories[position] = story
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun itemClicked(story: StoryDataItem) {
//        Toast.makeText(getActivity(),story.note,Toast.LENGTH_LONG).show()
        val intent = Intent(getActivity(), AddStory::class.java).apply {
            putExtra(REQUEST_CODE, "124")
            putExtra(STORY_ID, story.uid)
        }
        startActivityForResult(intent, EDIT_REQUEST_CODE)
    }

    override fun deleteItem(story: StoryDataItem) {
        db.diaryStoriesDao().delete(story)
    }


    companion object {
        const val ADD_REQUEST_CODE = 123
        const val EDIT_REQUEST_CODE = 124
        const val ADD_REQUEST_IMAGE = 126
        const val REQUEST_CODE = "lv.zabarovski.martins.TITLE_REPLY"
        const val STORY_ID = "lv.zabarovski.martins.STORY_ID"
    }
}

interface AdapterClickListener {
    fun itemClicked(story: StoryDataItem)

    fun deleteItem(story: StoryDataItem)

}

