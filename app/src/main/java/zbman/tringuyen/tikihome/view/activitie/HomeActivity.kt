package zbman.tringuyen.tikihome.view.activitie

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.layout_toolbar_custom_home.*
import timber.log.Timber
import zbman.tringuyen.common.base.BaseActivity
import zbman.tringuyen.tikihome.R
import kotlin.math.abs


class HomeActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view as Toolbar }

    override fun getToolbarInstance(): Toolbar? = toolbar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        handleEvents()
    }

    private fun setupViews() {
        setupToolbar()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
    }

    private fun handleEvents() {
        app_bar_toolbar_view.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                abs(verticalOffset) - appBarLayout.totalScrollRange == 0 -> {
                    txt_search.visibility = View.GONE
                }
                verticalOffset == 0 -> {
                    txt_search.visibility = View.VISIBLE
                }
                else -> {
                    txt_search.visibility = View.VISIBLE
                }
            }
        }
        )
    }

    private fun setupToolbar() {
        toolbar.title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


}
