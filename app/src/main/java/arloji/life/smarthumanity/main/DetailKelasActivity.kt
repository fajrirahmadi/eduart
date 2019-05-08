package arloji.life.smarthumanity.main

import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import arloji.life.smarthumanity.R
import arloji.life.smarthumanity.adapter.KelasAdapter
import arloji.life.smarthumanity.base.view.BaseActivity
import butterknife.BindView
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter

class DetailKelasActivity : BaseActivity() {

    @BindView(R.id.listKelasRecycleView)
    lateinit var listKelasRecyclerView: RecyclerView
    @BindView(R.id.titleToolbar)
    lateinit var titleToolbar: AppCompatTextView

    private val listKelasAdapter = FastItemAdapter<KelasAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kelas)
        titleToolbar.text = "Detail Kelas"
        configureItemAdapter(listKelasAdapter, listKelasRecyclerView)
        initValue()
    }

    private fun initValue() {
        listKelasAdapter.clear()
        listKelasAdapter.add(
            KelasAdapter(
                R.drawable.icon_wayang_potehi,
                "Wayang Potehi",
                3,
                6,
                100000
            )
        )
        listKelasAdapter.add(
            KelasAdapter(
                R.drawable.icon_wayang_golek,
                "Wayang Golek",
                1,
                4,
                500000
            )
        )
        listKelasAdapter.add(
            KelasAdapter(
                R.drawable.ic_wayang,
                "Wayang Jawa",
                4,
                12,
                1500000
            )
        )
    }
}