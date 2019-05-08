package arloji.life.smarthumanity.main

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arloji.life.smarthumanity.R
import arloji.life.smarthumanity.base.view.BaseFragment
import butterknife.BindView

class KelasFragment : BaseFragment() {

    @BindView(R.id.titleToolbar)
    lateinit var titleToolbar: AppCompatTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = getInflate(inflater, R.layout.fragment_class, container)
        titleToolbar.text = "Kelas"
        return view
    }
}