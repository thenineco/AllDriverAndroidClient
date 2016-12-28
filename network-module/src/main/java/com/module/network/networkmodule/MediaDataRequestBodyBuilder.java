package com.module.network.networkmodule;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zest .
 */

public class MediaDataRequestBodyBuilder {

    private MediaBodyType mMediaBodyType;

    public static final String HEADER = "multipart/form-data";

    public MultipartBody.Part createMediaBody(File mediaFile) {
        RequestBody requestFileMainImage =
                RequestBody.create(MediaType.parse(HEADER), mediaFile);

        MultipartBody.Part mediaBody = null;
        switch (mMediaBodyType) {
            case VIDEO:
                mediaBody =
                        MultipartBody.Part.createFormData("video", "video.mp4",
                                requestFileMainImage);
                break;
            case PHOTO:
                mediaBody =
                        MultipartBody.Part.createFormData("photo", "image.jpg",
                                requestFileMainImage);
        }

        return mediaBody;
    }

    public static MediaDataRequestBodyBuilder getBuilder() {
        return new MediaDataRequestBodyBuilder();
    }

    public MediaDataRequestBodyBuilder setMediaBodyType(
            MediaBodyType mediaBodyType) {
        mMediaBodyType = mediaBodyType;
        return this;
    }

    public enum MediaBodyType {
        PHOTO, VIDEO
    }
}

