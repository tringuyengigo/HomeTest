package zbman.tringuyen.tikihome.model.respository

import QuickLink
import io.reactivex.Observable
import zbman.tringuyen.tikihome.model.entity.banner.Banner
import zbman.tringuyen.tikihome.model.entity.deal.FlashDeal
import zbman.tringuyen.tikihome.model.respository.api.RestAPI


class MainRepositoryImpl constructor(private val mRestAPI: RestAPI) : MainRepository {


    override fun getBanner(): Observable<Banner?>? {
        return mRestAPI.getBanner()
    }

    override fun getQuickLink(): Observable<QuickLink?>? {
        return  mRestAPI.getQuickLink()
    }

    override fun getFlashDeal(): Observable<FlashDeal?>? {
        return  mRestAPI.getFlashDeal()
    }

}