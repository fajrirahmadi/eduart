package arloji.life.smarthumanity.base.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import arloji.life.smarthumanity.R
import arloji.life.smarthumanity.base.contract.BaseContract
import butterknife.ButterKnife
import butterknife.Unbinder
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import java.util.*

abstract class BaseFragment : Fragment(), BaseContract.View {
    lateinit var progressDialog: ProgressDialog
    lateinit var unbinder: Unbinder;

    fun showProgressDialog(title: String, message: String) {
        progressDialog = indeterminateProgressDialog(title = title, message = message)
    }

    fun showProgressDialog(message: String) {
        progressDialog = indeterminateProgressDialog(message = message)
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    override fun showProgressDialog() {
        showProgressDialog("Nyantai Bentar . . .")
    }

    override fun dismissProgressDialog() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

    override fun showError(message: String) {
        alert {
            this.message = message
        }.show()
    }

    override fun showInfo(msg: String) {
        alert {
            this.message = msg
        }.show()
    }

    fun showActivityAndFinishCurent(intent: Intent) {
        showActivity(intent)
        activity?.finish()
    }

    fun showActivity(intent: Intent) {
        startActivity(intent)
    }

    fun getIntent(context: Context, cls: Class<*>): Intent {
        return Intent(context, cls)
    }

    fun getInflate(layoutInflater: LayoutInflater, layoutResID: kotlin.Int, container: ViewGroup?): View {
        val inflate = layoutInflater.inflate(layoutResID, container, false)
        unbinder = ButterKnife.bind(this, inflate)
        return inflate
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }

    fun navigateTo(navigationContainerId: Int, actionId: Int, bundle: Bundle?) {
        activity?.let {
            Navigation.findNavController(it, navigationContainerId)
                .navigate(actionId, bundle)
        }
    }

    fun navigateTo(navigationContainerId: Int, actionId: Int) {
        navigateTo(navigationContainerId, actionId, null)
    }

    fun navigateToAndFinishCurrent(navigationContainerId: Int, actionId: Int) {
        navigateTo(navigationContainerId, actionId)
        activity?.finish()
    }

    fun configureItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    protected fun configureToolbarNoTitle(toolbar: Toolbar) {
        toolbar.title = ""
        val activities = (activity as AppCompatActivity)
        activities.setSupportActionBar(toolbar)
        Objects.requireNonNull(activities.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        activities.supportActionBar?.setDisplayShowTitleEnabled(true)
        activities.supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            resources.getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    protected fun configureEmptyToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        val activities = (activity as AppCompatActivity)
        activities.setSupportActionBar(toolbar)
        Objects.requireNonNull(activities.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        activities.supportActionBar?.setDisplayShowTitleEnabled(true)
        activities.supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            resources.getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }
}