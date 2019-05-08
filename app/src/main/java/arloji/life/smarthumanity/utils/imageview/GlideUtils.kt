package arloji.life.smarthumanity.utils.imageview

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlideUtils {
    companion object {
        fun setImageCircle(context: Context, path: String?, imageView: ImageView, progress: View) {
            GlideApp.with(context)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(GlideRequestListener(progress))
                .transform(CircleTransform())
                .into(imageView)
        }

        fun setImageCircle(context: Context?, path: Int?, imageView: ImageView, progress: View) {
            GlideApp.with(context!!)
                .load(path)
                .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(GlideRequestListener(progress))
                .transform(CircleTransform())
                .into(imageView)
        }

        fun setImageCircle(context: Context?, path: Int?, imageView: ImageView) {
            GlideApp.with(context!!)
                .load(path)
                .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(CircleTransform())
                .into(imageView)
        }
    }
}