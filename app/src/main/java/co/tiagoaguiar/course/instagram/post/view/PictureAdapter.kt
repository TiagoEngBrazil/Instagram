package co.tiagoaguiar.course.instagram.post.view

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import java.lang.Long.getLong
import java.lang.reflect.Array.getLong

class PictureAdapter(private val onClick: (Uri) -> Unit) :
    RecyclerView.Adapter<PictureAdapter.PostViewHolder>() {

    var items: List<Uri> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(image: Uri) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val bitmap =
                    itemView.context.contentResolver.loadThumbnail(image, Size(200, 200), null)
                itemView.findViewById<ImageView>(R.id.item_profile_image_grid)
                    .setImageBitmap(bitmap)
            } else {
                itemView.findViewById<ImageView>(R.id.item_profile_image_grid).setImageURI(image)
//                MediaStore.Images.Thumbnails.getThumbnail(itemView.context.contentResolver, imageId, MediaStore.Images.Thumbnails.MINI_KIND, null)
            }
            itemView.setOnClickListener {
                onClick.invoke(image)
            }
        }

    }
}