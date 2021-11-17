package com.ucs.mangaoff.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasRelationship;

import java.util.List;

public class MangaOffUtils {

    public static String getCoverUrl(List<ResponseMangasRelationship> relationships) {
        for (ResponseMangasRelationship item: relationships) {
            if (item.getType().equals("cover_art")) {
                return item.getAttributes().getFileName();
            }
        }
        return "";
    }

    public static Bitmap addGradient(Bitmap src) {
        int gradient_height = src.getHeight();
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap overlay = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);

        canvas.drawBitmap(src, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,  h - gradient_height, 0, h, 0xFFFFFFFF, 0x00FFFFFF, Shader.TileMode.CLAMP);

        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, h - gradient_height, w, h, paint);

        return overlay;
    }

    public static void setRoundedThumb(Bitmap workingBitmap , int w, int h, ImageView v, Context context) {
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        Shader shader = new BitmapShader(workingBitmap, Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        paint.setAntiAlias(true);
        paint.setShader(shader);
        int radius = 55;
        RectF rec = new RectF(0, 0, w, h);
        c.drawRect(new RectF(radius, 0, w, h), paint);
        c.drawRoundRect(rec, radius, radius, paint);
        v.setBackground(new BitmapDrawable(context.getResources(), bmp));
    }
}
