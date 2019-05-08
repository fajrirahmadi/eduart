package arloji.life.smarthumanity.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arloji.life.smarthumanity.R
import arloji.life.smarthumanity.base.view.BaseFragment
import butterknife.OnClick

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = getInflate(inflater, R.layout.fragment_home, container)
        return view
    }

    @OnClick(R.id.menuWayang)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.menuWayang -> {
                navigateTo(R.id.home_nav_graph_fragment, R.id.actionMove_fromHomeFragment_toDetailKelasActivity)
            }
        }
    }
}