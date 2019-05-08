package arloji.life.smarthumanity.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arloji.life.smarthumanity.R
import arloji.life.smarthumanity.base.view.BaseFragment
import butterknife.OnClick

class LoginFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = getInflate(inflater, R.layout.fragment_login, container)
        return view
    }

    @OnClick(R.id.loginButton)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.loginButton -> {
                navigateToAndFinishCurrent(
                    R.id.authenticationNavGraph,
                    R.id.action_move_fromLoginFragment_toMainActivity
                )
            }
        }
    }
}