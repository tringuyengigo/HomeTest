package zbman.tringuyen

import android.app.Application
import androidx.multidex.BuildConfig
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import zbman.tringuyen.tikihome.di.mNetworkModules
import zbman.tringuyen.tikihome.di.mRepositoryModules
import zbman.tringuyen.tikihome.di.mViewModels

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {

        }
        Timber.plant(Timber.DebugTree())
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
            listOf( mNetworkModules,
                mRepositoryModules,
                mViewModels)
        )

    }
}