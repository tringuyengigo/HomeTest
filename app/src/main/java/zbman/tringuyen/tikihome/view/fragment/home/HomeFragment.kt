package zbman.tringuyen.tikihome.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import zbman.tringuyen.common.util.Constants
import zbman.tringuyen.tikihome.R
import zbman.tringuyen.tikihome.adapter.`interface`.OnItemClickListener
import zbman.tringuyen.tikihome.adapter.banner.BannerViewAdapter
import zbman.tringuyen.tikihome.adapter.deal.DealViewAdapter
import zbman.tringuyen.tikihome.adapter.link.LinkViewAdapter
import zbman.tringuyen.tikihome.adapter.loading.LoadingViewAdapter
import zbman.tringuyen.tikihome.model.entity.banner.DataBanner
import zbman.tringuyen.tikihome.model.entity.deal.Data
import zbman.tringuyen.tikihome.model.entity.quicklink.DataQuickLink
import zbman.tringuyen.tikihome.viewmodel.home.HomeViewModel

class HomeFragment : Fragment(), LifecycleObserver, OnItemClickListener {
    
    private val mainViewModel: HomeViewModel by viewModel()

    private lateinit var bannerViewAdapter: BannerViewAdapter
    private lateinit var linkAdapter: LinkViewAdapter
    private lateinit var dealAdapter: DealViewAdapter
    private lateinit var loadingAdapter: LoadingViewAdapter
    private val mergedAdapter = MergeAdapter(
        MergeAdapter.Config.Builder().setIsolateViewTypes(true).build()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        handleEvents()
        bindData()
    }

    private fun setupViews() {
        loadingAdapter = LoadingViewAdapter()
        rcv_main.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        rcv_main.itemAnimator = DefaultItemAnimator()
        rcv_main.adapter = mergedAdapter
        addLoading()
    }

    private fun handleEvents() {
        main_swipe_refresh.setOnRefreshListener {
            for (item in mergedAdapter.adapters) {
                mergedAdapter.removeAdapter(item)
            }
            addLoading()
            mainViewModel.fetchParallelData()
        }
    }

    private fun bindData() {
        mainViewModel.mCombine.observe(viewLifecycleOwner, Observer { data ->
            removeLoading()
            if (!data.data?.first!!.data.isNullOrEmpty()) {
                bannerViewAdapter = BannerViewAdapter(data.data?.first!!.data, this)
                mergedAdapter.addAdapter(bannerViewAdapter)
            }
            if (!data.data?.second!!.data.isNullOrEmpty()) {
                linkAdapter = LinkViewAdapter(data.data?.second!!, this)
                mergedAdapter.addAdapter(linkAdapter)
            }
            addLoading()
        })

        mainViewModel.mFlashDeal.observe(viewLifecycleOwner, Observer { data ->
            removeLoading()
            if (!data.data?.data.isNullOrEmpty()) {
                dealAdapter = data.data?.let { DealViewAdapter(it, this) }!!
                mergedAdapter.addAdapter(dealAdapter)
            }
            main_swipe_refresh.isRefreshing = false
        })
    }

    override fun onItemClicked(item: Pair<Any, Int>) {
        item.let { itemClicked ->
            when (itemClicked.second) {
                Constants.ITEM_VIEW_BANNER_TYPE -> {
                    Toast.makeText(
                        activity,
                        "Its toast! Banner: ${(item.first as DataBanner).content}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constants.ITEM_VIEW_LINK_TYPE -> {
                    Toast.makeText(
                        activity,
                        "Its toast! Link: ${(item.first as DataQuickLink).title}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Constants.ITEM_VIEW_DEAL_TYPE -> {
                    Toast.makeText(
                        activity,
                        "Its toast! Deal: ${(item.first as Data).product.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    private fun addLoading() {
        mergedAdapter.addAdapter(loadingAdapter)
    }

    private fun removeLoading() {
        mergedAdapter.removeAdapter(loadingAdapter)
    }
}