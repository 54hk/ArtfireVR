package com.artfire.ninedraw.artfire_code.utils;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.base.Globals;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class GlideUtil {
    /**
     * 加载本地图片
     *
     * @param url
     * @param imageView
     */
    public static void loadLocalImage(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        Glide.with(Globals.context).load(url).into(imageView);
    }

    /**
     * 加载本地的GIF图片
     *
     * @param url
     * @param imageView
     */
    public static void loadLocalGif(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        Glide.with(Globals.context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }


    /**
     * 加载本地的GIF图片
     *
     * @param id
     * @param imageView
     */
    public static void loadLocalGif2(int id, ImageView imageView) {
        Glide.with(Globals.context).load(id).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    /**
     * 加载本地资源文件夹图片
     *
     * @param url
     * @param imageView
     */
    public static void loadLocalMipmap(int url, ImageView imageView) {
        Glide.with(Globals.context).load(url).into(imageView);
    }


    /**
     * 加载本地图片 不带缓存
     *
     * @param url
     * @param imageView
     */
    public static void loadLocalImageNoChache(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        Glide.with(Globals.context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imageView);
    }


    public static void loadLocalImage2(Integer resourceId, ImageView imageView) {
        Glide.with(Globals.context).load(resourceId).into(imageView);
    }

    /**
     * 加载gif图片，没有默认图
     *
     * @param url
     * @param imageView
     */
    public static void loadGifNoDefult(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).asGif().diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载图片，没有默认图
     *
     * @param url
     * @param imageView
     */
    public static void loadImage(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }


    /**
     * 画廊专用
     *
     * @param url
     * @param imageView
     */
    public static void loadGalleryImage(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).placeholder(R.mipmap.defult).error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
//        Glide.with(Globals.context).using(new pro).load(glideUrl).placeholder(R.mipmap.default_image).error(R.mipmap.default_image).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /***
     * 加载首页banner图
     * @param url
     * @param imageView
     */
    public static void loadBanner(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载首页精品图
     *
     * @param imageView
     */
    public static void loadBoutiqueCourse(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }
    public static void loadBoutiqueCourse1(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载首页系列图
     *
     * @param url
     * @param imageView
     */
    public static void loadSeriesCourse(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).placeholder(R.mipmap.defult).error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载音频课列表图
     *
     * @param imageView
     */
    public static void loadVoiceCourse(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).placeholder(R.mipmap.defult).error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载gif
     *
     * @param imageView
     */
    public static void loadGif(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).asGif().error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }




    /**
     * 加载4:5图片
     *
     * @param url
     * @param imageView
     */
    public static void loadFourToFiveImage(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载首页活动图
     *
     * @param url
     * @param imageView
     */
    public static void loadAty(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).placeholder(R.mipmap.defult).error(R.mipmap.defult).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    /**
     * 加载头像
     *
     * @param url
     * @param imageView
     */
    public static void loadPortraits(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).placeholder(R.mipmap.head_default).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param url
     * @param imageView
     */
    public static void loadRoundPortraits(String url, final ImageView imageView) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).asBitmap().centerCrop().placeholder(R.mipmap.head_default).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(Globals.context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    /**
     * 加载圆形头像,自定义角度
     *
     * @param url
     * @param imageView
     */
    public static void loadRoundPortraits(String url, final ImageView imageView, final float angle) {
        if (TextUtils.isEmpty(url)) {
            url = "null";
        }
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(Globals.OSS_HEAD_KEY, Globals.OSS_HEAD_VALUE).build());
        Glide.with(Globals.context).load(glideUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(Globals.context.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(angle);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }


}
