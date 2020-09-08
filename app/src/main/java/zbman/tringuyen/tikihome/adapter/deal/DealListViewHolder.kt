package zbman.tringuyen.tikihome.adapter.deal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_deal.view.*
import timber.log.Timber
import zbman.tringuyen.common.util.Constants
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import zbman.tringuyen.tikihome.model.entity.deal.Data
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.properties.Delegates


class DealListViewHolder(itemView: View, private val itemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

    fun bindView(mData: Data) {
        val formatter: NumberFormat = DecimalFormat("#,### đ")
        val percentDiscount = 100*mData.product.discount / mData.product.list_price
        val percentRemain = 100*mData.progress.qty_ordered / mData.progress.qty

        itemView.tvPercent.text = StringBuilder("-$percentDiscount%")
        itemView.tvPrice.text = formatter.format(mData.product.price)
        itemView.progress_bar_deal.progress = percentRemain.toFloat()

        Glide.with(itemView)
            .load(mData.product.thumbnail_url)
            .override(itemView.ivImageDeal.width,itemView.ivImageDeal.height)
            .centerInside()
            .placeholder(R.drawable.ic_broken_image)
            .into(itemView.ivImageDeal)

        itemView.setOnClickListener {
            itemClickListener.onItemClicked(Pair(mData, Constants.ITEM_VIEW_DEAL_TYPE))
        }
    }

}

class DealListGridRecyclerAdapter( private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    var items: List<Data> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DealListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_deal, parent, false), itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val dealViewHolder = viewHolder as DealListViewHolder
        dealViewHolder.bindView(items[position])
    }

}