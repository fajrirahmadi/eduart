package arloji.life.smarthumanity.base.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import arloji.life.smarthumanity.base.contract.BaseContract
import arloji.life.smarthumanity.R
import butterknife.ButterKnife
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.yesButton
import java.util.*

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    lateinit var progressDialog: ProgressDialog

    override fun setContentView(layout: Int) {
        super.setContentView(layout)
        ButterKnife.bind(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun showProgressDialog(title: String, message: String) {
        progressDialog = indeterminateProgressDialog(title = title, message = message)
    }

    fun showProgressDialog(message: String) {
        progressDialog = indeterminateProgressDialog(message = message)
    }

    override fun showProgressDialog() {
        showProgressDialog("Nyantai Bentar . . .")
    }

    override fun dismissProgressDialog() {
        progressDialog.dismiss()
    }

    fun showDialogInfoAndFinish(msg: String) {
        alert {
            isCancelable = false
            message = msg
            yesButton { onBackPressed() }
        }.show()
    }

    fun showDialogError(msg: String) {
        if (progressDialog.isShowing)
            dismissProgressDialog()
        alert {
            message = msg
        }.show()
    }

    override fun showError(message: String) {
        showDialogError(message)
    }

    override fun showInfo(msg: String) {
        if (progressDialog.isShowing)
            dismissProgressDialog()
        alert {
            message = msg
        }.show()
    }

    fun showActivityAndFinishCurent(intent: Intent) {
        showActivity(intent)
        finish()
    }

    fun showActivity(intent: Intent) {
        startActivity(intent)
    }

    fun getIntent(context: Context, cls: Class<*>): Intent {
        return Intent(context, cls)
    }

    fun configureItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    protected fun configureToolbarWithHomeAndTitle(toolbar: Toolbar, title: String?) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    protected fun configureToolbarEmpty() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        this.setSupportActionBar(toolbar)
        Objects.requireNonNull(this.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(true)
        this.supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }
}