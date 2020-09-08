package zbman.tringuyen.tikihome.adapter.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_banner.view.*
import timber.log.Timber
import zbman.tringuyen.common.util.Constants.Companion.ITEM_VIEW_BANNER_TYPE
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import zbman.tringuyen.tikihome.model.entity.banner.DataBanner
import kotlin.properties.Delegates

class ViewPagerAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<PagerViewHolder>(), AutoUpdatableAdapter {

    var items: List<DataBanner> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o == n }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_banner, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int): Unit = holder.itemView.run {

        Glide.
            with(this)
            .load(items[position].mobile_url)
            .override(ivImage.width,ivImage.height)
            .centerInside()
            .into(ivImage)

        cardViewBanner.setOnClickListener {
            Timber.d("Click banner pos: $position  content: ${items[position]}" )
            itemClickListener.onItemClicked(Pair(items[position], ITEM_VIEW_BANNER_TYPE))
        }

    }
}

class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)