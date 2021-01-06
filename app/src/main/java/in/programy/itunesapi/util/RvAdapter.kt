package `in`.programy.itunesapi.util

import `in`.programy.itunesapi.R
import `in`.programy.itunesapi.model.room.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_rv.view.*

class RvAdapter() : RecyclerView.Adapter<RvAdapter.ViewHolder>(){
    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item)

    private val differCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false))
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currItem = differ.currentList[position]

        holder.itemView.apply {
            try {
                Glide.with(this)
                        .load(currItem.artworkUrl100)
                        .placeholder(R.drawable.download)
                        .into(ivTrack)
                tvArtist.text = currItem.artistName
                tvSecondLine.text = currItem.collectionName ?: currItem.trackName
            }
            catch (t: Throwable){
                t.printStackTrace()
            }
        }
    }
}