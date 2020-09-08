package zbman.tringuyen.tikihome.model.respository

import QuickLink
import io.reactivex.Observable
import zbman.tringuyen.tikihome.model.entity.banner.Banner
import zbman.tringuyen.tikihome.model.entity.deal.FlashDeal

interface MainRepository {

    fun getBanner(): Observable<Banner?>?

    fun getQuickLink(): Observable<QuickLink?>?

    fun getFlashDeal(): Observable<FlashDeal?>?


}