package lv.zabarovski.martins.simplediary

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_add_picture.*
import lv.zabarovski.martins.simplediary.DiaryList.Companion.REQUEST_CODE

class AddImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_picture)

        imagePlaceholder.setOnClickListener { openGalleryForImage() }
        updateImageButton.setOnClickListener { finish() }
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
            val dbURI = imageURI.toString()
            imagePlaceholder.setImageURI(Uri.parse(dbURI)) // handle chosen image
        }
    }

    companion object {
        const val IMAGE_REQUEST = 125
    }
}