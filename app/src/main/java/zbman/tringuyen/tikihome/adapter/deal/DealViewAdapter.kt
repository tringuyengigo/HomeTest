package zbman.tringuyen.tikihome.adapter.deal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import zbman.tringuyen.tikihome.model.entity.deal.FlashDeal

class DealViewAdapter(private val entry: FlashDeal, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolderDeal>(), AutoUpdatableAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDeal {
        Timber.e("ViewHolderThird -> onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rcv_flash_deal, parent, false)
        return ViewHolderDeal(view)
    }

    override fun onBindViewHolder(holder: ViewHolderDeal, position: Int) {
        Timber.e("ViewHolderThird -> onBindViewHolder")
        holder.rcvFlashDeal.setHasFixedSize(true)
        holder.rcvFlashDeal.layoutManager =
            GridLayoutManager(
                holder.itemView.context,
                1,
                GridLayoutManager.HORIZONTAL,
                false
            )

        holder.rcvFlashDeal.adapter = DealListGridRecyclerAdapter(itemClickListener)
        holder.rcvFlashDeal.itemAnimator = DefaultItemAnimator()
        (holder.rcvFlashDeal.adapter as DealListGridRecyclerAdapter).items = entry.data
        holder.progressBarDeal.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return 1
    }

}

class ViewHolderDeal(view: View) : RecyclerView.ViewHolder(view) {
    var rcvFlashDeal: RecyclerView = itemView.findViewById(
        R.id.rcv_flash_deal
    )
    var progressBarDeal: ProgressBar = itemView.findViewById(
        R.id.progress_bar_deal
    )
    init {
        Timber.e("ViewHolderFlashDeal -> init")
        progressBarDeal.visibility = View.VISIBLE
    }
}