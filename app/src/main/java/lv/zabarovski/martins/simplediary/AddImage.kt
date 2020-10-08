package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_add_picture.*
import lv.zabarovski.martins.simplediary.Dbase.Database
import lv.zabarovski.martins.simplediary.Dbase.ImageDataItem
import lv.zabarovski.martins.simplediary.DiaryList.Companion.REQUEST_CODE

class AddImage : AppCompatActivity() {
    private val db get() = Database.getInstance(this)
    val myImageURi = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_picture)

        imagePlaceholder.setOnClickListener { openGalleryForImage() }
        updateImageButton.setOnClickListener {
            val title = imageTitle.text
            val note = imageNoteContent.text
            val newItem = ImageDataItem(title.toString(),System.currentTimeMillis(), note.toString(),myImageURi)
            newItem.uid = db.diaryStoriesDao().insertAllImages(newItem).first()
            val result = Intent().apply {
                putExtra(DiaryList.STORY_ID, newItem.uid)
            }
            setResult(Activity.RESULT_OK,result)
            finish()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST){
            val imageURI = data?.data
            val  myImageUri = imageURI.toString()
            imagePlaceholder.setImageURI(Uri.parse(myImageUri)) // handle chosen image
        }
    }

    companion object {
        const val IMAGE_REQUEST = 125
    }
}