package co.tiagoaguiar.course.instagram.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import co.tiagoaguiar.course.instagram.profile.view.PostAdapter

class searchAdapter: RecyclerView.Adapter<searchAdapter.searchViewHolder> () {

    var items: List<UserAuth> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchViewHolder {
        return searchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: searchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }

    class searchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserAuth) {
            itemView.findViewById<ImageView>(R.id.search_img_user).setImageURI(user.photoUri)
            itemView.findViewById<TextView>(R.id.search_txt_username).text = user.name
        }
    }
}