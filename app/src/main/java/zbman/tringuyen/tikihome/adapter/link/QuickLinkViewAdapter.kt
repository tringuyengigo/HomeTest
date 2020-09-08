package zbman.tringuyen.tikihome.adapter.link

import QuickLink
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.layout_rcv_quick_link.view.*
import timber.log.Timber
import zbman.tringuyen.common.util.Constants
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import zbman.tringuyen.tikihome.adapter.banner.PagerViewHolder
import zbman.tringuyen.tikihome.model.entity.quicklink.DataQuickLink


class LinkViewAdapter(private val entry: QuickLink, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolderLink>(), AutoUpdatableAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLink {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rcv_quick_link, parent, false)
        return ViewHolderLink(view)
    }

    override fun onBindViewHolder(holder: ViewHolderLink, position: Int): Unit = holder.itemView.run {

        rcv_quick_link.setHasFixedSize(true)
        rcv_quick_link.layoutManager =
            GridLayoutManager(
                holder.itemView.context,
                2,
                GridLayoutManager.HORIZONTAL,
                false
            )
        rcv_quick_link.adapter = QuickLinkListGridRecyclerAdapter(itemClickListener)
        (rcv_quick_link.adapter as QuickLinkListGridRecyclerAdapter).items = entry.data[0]
            .zip(entry.data[1]){ rowOne, rowTwo ->
                listOf(rowOne,rowTwo)
            }
            .flatten()

    }

    override fun getItemCount(): Int {
        return 1
    }

}

class ViewHolderLink(view: View) : RecyclerView.ViewHolder(view)