package lv.zabarovski.martins.simplediary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
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
        val context = holder.itemView.context
        holder.itemView.itemTitleNote.text = item.title
        holder.itemView.itemTextNote.text = item.note
        holder.itemView.itemDateNote.text = item.date.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
    }
}