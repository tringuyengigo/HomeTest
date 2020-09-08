package zbman.tringuyen.tikihome.di
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import zbman.tringuyen.common.util.Constants.Companion.API
import zbman.tringuyen.common.util.Constants.Companion.BASE_URL
import zbman.tringuyen.common.util.Constants.Companion.REPOSITORY_IMPL
import zbman.tringuyen.common.util.Constants.Companion.RETROFIT_INSTANCE
import zbman.tringuyen.tikihome.model.respository.MainRepositoryImpl
import zbman.tringuyen.tikihome.model.respository.api.RestAPI
import zbman.tringuyen.tikihome.viewmodel.home.HomeViewModel


val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RestAPI::class.java) }
}

val mRepositoryModules = module {
    single(name = REPOSITORY_IMPL) { MainRepositoryImpl( mRestAPI = get(API)) }
}

val mViewModels = module {
    viewModel {
        HomeViewModel(
            mMainRepositoryImpl = get(REPOSITORY_IMPL)
        )
    }
}






