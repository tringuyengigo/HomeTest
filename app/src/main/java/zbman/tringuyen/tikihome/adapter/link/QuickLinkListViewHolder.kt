package zbman.tringuyen.tikihome.adapter.link

import zbman.tringuyen.tikihome.model.entity.quicklink.DataQuickLink
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_quick_link.view.*
import timber.log.Timber
import zbman.tringuyen.common.util.Constants
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import kotlin.properties.Delegates

class QuickLinkListViewHolder(itemView: View, private val itemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

    fun bindView(mData: DataQuickLink) {
        Timber.e("QuickLinkListViewHolder mData: $mData ")
        itemView.tvLink.text = mData.title
        Glide.
            with(itemView)
            .load(mData.image_url)
            .override(itemView.ivImageLink.width,itemView.ivImageLink.height)
            .centerInside()
            .into(itemView.ivImageLink)
        itemView.ivImageLink.setOnClickListener {
            itemClickListener.onItemClicked(Pair(mData, Constants.ITEM_VIEW_LINK_TYPE))
        }
    }
}

class QuickLinkListGridRecyclerAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    var items: List<DataQuickLink> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuickLinkListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_quick_link, parent, false), itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val linkViewHolder = viewHolder as QuickLinkListViewHolder
        linkViewHolder.bindView(items[position])
    }

}