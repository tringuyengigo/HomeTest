package zbman.tringuyen.tikihome.viewmodel.home

import QuickLink
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import zbman.tringuyen.common.base.BaseViewModel
import zbman.tringuyen.common.util.Data
import zbman.tringuyen.common.util.Status
import zbman.tringuyen.tikihome.model.entity.banner.Banner
import zbman.tringuyen.tikihome.model.entity.deal.FlashDeal
import zbman.tringuyen.tikihome.model.respository.MainRepositoryImpl

class HomeViewModel constructor(private val mMainRepositoryImpl: MainRepositoryImpl) : BaseViewModel() {

    var mFlashDeal = MutableLiveData<Data<FlashDeal>>()
    var mCombine = MutableLiveData<Data<Pair<Banner?, QuickLink?>>>()

    init {
        fetchParallelData()
    }

    fun fetchParallelData() {
        val mZipper = BiFunction< Banner?, QuickLink?, Pair<Banner?, QuickLink?>> { first, second -> Pair(first, second) }

        val mBannerObservable = mMainRepositoryImpl.getBanner()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorReturn {
                val bn = Banner(emptyList())
                bn
            }

        val mQuickLinkObservable = mMainRepositoryImpl.getQuickLink()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorReturn {
                val ql = QuickLink(emptyList())
                ql
            }

        val mFlashDealObservable = mMainRepositoryImpl.getFlashDeal()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorReturn {
                val flDeal = FlashDeal(emptyList(), emptyList(), "")
                flDeal
            }

        val mCombineObservable = Observable.zip( mBannerObservable , mQuickLinkObservable, mZipper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { dataCombine ->
                    Timber.e("[fetchParallelData] dataCombine Banner + QuickLink->  ${Gson().toJson(dataCombine)}")
                    mCombine.value = Data(responseType = Status.SUCCESSFUL, data = dataCombine)
                    mFlashDealObservable
                }
                ?.subscribe(
                        {  dataDeal ->
                            Timber.e("[fetchParallelData] Deal ->  ${Gson().toJson(dataDeal)}")
                            mFlashDeal.value = Data(responseType = Status.SUCCESSFUL, data = dataDeal)
                        },
                        { error ->
                            Timber.e("[fetchParallelData] Exception Stream: $error")
                        }
                )

        if (mCombineObservable != null) {
            addDisposable(mCombineObservable)
        }
    }

}