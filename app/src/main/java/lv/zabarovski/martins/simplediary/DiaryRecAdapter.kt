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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_story.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import java.security.AccessController.getContext
import java.time.format.DateTimeFormatter

class DiaryRecAdapter (
    private val listener: AdapterClickListener,
    private val stories: MutableList<StoryDataItem>) :
    RecyclerView.Adapter<DiaryRecAdapter.DiaryViewHolder>(){

    class DiaryViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return DiaryViewHolder(view)
    }

    override fun getItemCount() = stories.size

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = stories[position]
        val activity = holder.itemView.context as Activity
        holder.itemView.itemTitleNote.text = item.title
        holder.itemView.itemTextNote.text = item.note
        holder.itemView.itemDateNote.text = item.date.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()

        holder.itemView.setOnClickListener{
            listener.itemClicked(stories[position])
            deleteItem(position)
        }
        holder.itemView.itemDeleteNote.setOnClickListener {
            deleteItem(position)
            notifyDataSetChanged()
        }


    }
}