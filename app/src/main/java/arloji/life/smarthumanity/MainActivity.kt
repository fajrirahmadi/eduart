package arloji.life.smarthumanity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import arloji.life.smarthumanity.base.view.BaseActivity
import butterknife.BindView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class MainActivity : BaseActivity() {

    @BindView(R.id.bottomBar)
    lateinit var bottomBar: BottomNavigationViewEx
    @BindView(R.id.frameHome)
    lateinit var frameHome: FrameLayout
    @BindView(R.id.frameClass)
    lateinit var frameClass: FrameLayout
    @BindView(R.id.frameInbox)
    lateinit var frameInbox: FrameLayout
    @BindView(R.id.frameProfile)
    lateinit var frameProfile: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureBottomBar()
        showHomeFragment()
    }

    private fun configureBottomBar() {
        bottomBar.enableAnimation(false)
        bottomBar.isItemHorizontalTranslationEnabled = false
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> showHomeFragment()
                R.id.bottom_class -> showClassFragment()
                R.id.bottom_inbox -> showInboxFragment()
                R.id.bottom_profile -> showProfileFragment()
            }
            true
        }
    }

    private fun showHomeFragment() {
        frameHome.visibility = View.VISIBLE
        frameClass.visibility = View.GONE
        frameInbox.visibility = View.GONE
        frameProfile.visibility = View.GONE
    }

    private fun showClassFragment() {
        frameHome.visibility = View.GONE
        frameClass.visibility = View.VISIBLE
        frameInbox.visibility = View.GONE
        frameProfile.visibility = View.GONE
    }

    private fun showInboxFragment() {
        frameHome.visibility = View.GONE
        frameClass.visibility = View.GONE
        frameInbox.visibility = View.VISIBLE
        frameProfile.visibility = View.GONE
    }

    private fun showProfileFragment() {
        frameHome.visibility = View.GONE
        frameClass.visibility = View.GONE
        frameInbox.visibility = View.GONE
        frameProfile.visibility = View.VISIBLE
    }
}
