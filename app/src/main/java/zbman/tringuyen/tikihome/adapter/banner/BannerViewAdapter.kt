package zbman.tringuyen.tikihome.adapter.banner

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_viewpaper_banner.view.*
import timber.log.Timber
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.AutoUpdatableAdapter
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import zbman.tringuyen.tikihome.model.entity.banner.DataBanner
import java.util.*

class BannerViewAdapter(private val entries: List<DataBanner>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolderBanner>(), AutoUpdatableAdapter {

    private var currentPage = 0
    private val DELAY: Long = 500
    private val PERIOD: Long = 3000
    lateinit var timer: Timer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBanner {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_viewpaper_banner, parent, false)
        return ViewHolderBanner(view)
    }

    override fun onBindViewHolder(holder: ViewHolderBanner, position: Int) {
        timer = Timer()
        val handler = Handler()

        val update = Runnable {
            holder.itemView.vp_banner.setCurrentItem(currentPage, true)
            if(currentPage == entries.size) {
                currentPage = 0
                holder.itemView.vp_banner.setCurrentItem(0, false)
            }
            currentPage++
        }

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, (DELAY), (PERIOD))

        holder.itemView.vp_banner.adapter = ViewPagerAdapter(itemClickListener)
        (holder.itemView.vp_banner.adapter as ViewPagerAdapter).items = entries
    }

    override fun getItemCount(): Int {
        return 1
    }

}

class ViewHolderBanner(view: View) : RecyclerView.ViewHolder(view)
