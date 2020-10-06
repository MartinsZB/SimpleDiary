package lv.zabarovski.martins.simplediary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.time.LocalDateTime
import androidx.fragment.app.*
import kotlinx.android.synthetic.main.fragment_diary_list.*


class DiaryList : Fragment() {

    private val stories = mutableListOf(
        StoryDataItem("One", LocalDateTime.now(),"This is first note"),
        StoryDataItem("Two", LocalDateTime.now(),"This is Second note"),
        StoryDataItem("Three", LocalDateTime.now(),"This is third note")
    )

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
    }
}