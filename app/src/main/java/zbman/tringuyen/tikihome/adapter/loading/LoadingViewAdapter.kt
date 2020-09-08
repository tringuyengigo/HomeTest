package zbman.tringuyen.tikihome.adapter.loading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter

class LoadingViewAdapter : RecyclerView.Adapter<ViewHolderLoading>(), AutoUpdatableAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderLoading {

        Timber.e("ViewHolderThird -> onCreateViewHolder")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_loading, parent, false)
        return ViewHolderLoading(view)
    }

    override fun onBindViewHolder(holder: ViewHolderLoading, position: Int) {
        Timber.e("ViewHolderThird -> onBindViewHolder")
    }

    override fun getItemCount(): Int {
        return 1
    }

}

class ViewHolderLoading(view: View) : RecyclerView.ViewHolder(view) {

    private var progressBarBottom: ProgressBar = itemView.findViewById(
        R.id.progress_bar_bottom
    )
    init {
        Timber.e("ViewHolderFlashDeal -> init")
        progressBarBottom.visibility = View.VISIBLE
    }
}