package zbman.tringuyen.tikihome.model.respository.api

import QuickLink
import io.reactivex.Observable
import retrofit2.http.GET
import zbman.tringuyen.tikihome.model.entity.banner.Banner
import zbman.tringuyen.tikihome.model.entity.deal.FlashDeal

interface RestAPI {

    @GET("/v2/home/banners/v2")
    fun getBanner(): Observable<Banner?>?

    @GET("/shopping/v2/widgets/quick_link")
    fun getQuickLink(): Observable<QuickLink?>?

    @GET("/v2/widget/deals/hot")
    fun getFlashDeal(): Observable<FlashDeal?>?

}