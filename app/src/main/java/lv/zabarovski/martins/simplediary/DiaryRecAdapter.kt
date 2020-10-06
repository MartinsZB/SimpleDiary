package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.getSystemService
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_story.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import java.security.AccessController.getContext
import java.time.format.DateTimeFormatter

class DiaryRecAdapter (private val stories: MutableList<StoryDataItem>) :
    RecyclerView.Adapter<DiaryRecAdapter.DiaryViewHolder>(){


    class DiaryViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return DiaryViewHolder(view)
    }

    override fun getItemCount() = stories.size

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = stories[position]
        holder.itemView.itemTitleNote.text = item.title
        holder.itemView.itemTextNote.text = item.note
        holder.itemView.itemDateNote.text = item.date.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
        holder.itemView.setOnClickListener{
           editStory(item.title,item.note)

        }
        holder.itemView.itemDeleteNote.setOnClickListener {
            deleteItem(position)
            notifyDataSetChanged()
        }


    }
//    private fun editStory(context: Activity, title: String, story: String) {
//
//        val editItemIntent = Intent(Activity, AddStory::class.java).apply{}
//        startActivityForResult(editItemIntent, REPLAY_REQUEST_CODE)
//
//    }
//    companion object {
//        const val REPLAY_REQUEST_CODE = 124
//        const val SEND_STORY_TITLE = "lv.zabarovski.martins.TITLE_SEND"
//        const val GET_STORY_TITLE = "lv.zabarovski.martins.TITLE_REPLY"
//        const val SEND_STORY_TEXT = "lv.zabarovski.martins.STORY_SEND"
//        const val GET_STORY_TEXT = "lv.zabarovski.martins.STORY_REPLY"
//    }
}