package arloji.life.smarthumanity.utils.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

public class CircleTransform extends BitmapTransformation {

  @Override
  protected Bitmap transform(@NotNull BitmapPool pool, @NotNull Bitmap toTransform, int outWidth, int outHeight) {
    return circleCrop(pool, toTransform);
  }

  private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
    if (source == null) return null;

    int size = Math.min(source.getWidth(), source.getHeight());
    int x = (source.getWidth() - size) / 2;
    int y = (source.getHeight() - size) / 2;
    float r = size / 2f;

    // TODO this could be acquired from the pool too
    Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
    Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);

    Paint paint = new Paint();
    paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
    paint.setAntiAlias(true);

    Canvas canvas = new Canvas(result);
    canvas.drawCircle(r, r, r, paint);
    return result;
  }

  @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

  }
}