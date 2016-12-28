package com.module.network.networkmodule;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by zest
 */
public class RequestBodyWithProgress extends RequestBody {

    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private File mFile;
    private String mPath;
    private String contentType;
    private MediaType mediaType;
    private UploadCallbacks listener;
    private UploadCallbacks mListener;
    private byte[] byteArray;

    public RequestBodyWithProgress(byte[] byteArray, MediaType mediaType,
            final UploadCallbacks listener) {
        this.byteArray = byteArray;
        this.mediaType = mediaType;
        this.listener = listener;
    }

    public RequestBodyWithProgress(final File file, MediaType mediaType,
            final UploadCallbacks listener) {
        mFile = file;
        this.mediaType = mediaType;
        mListener = listener;
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }

    @Override
    public MediaType contentType() {
        // i want to upload only images
        return mediaType;
    }


    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        InputStream in;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long fileLength;
        if (mFile != null) {
            fileLength = mFile.length();
            in = new FileInputStream(mFile);
        } else {
            fileLength = byteArray.length;
            in = new ByteArrayInputStream(byteArray);
        }
        long uploaded = 0;
        try {
            int read;
            while ((read = in.read(buffer)) != -1) {

                // update progress on UI thread
                updateProgress(uploaded, fileLength);

                uploaded += read;
                sink.write(buffer, 0, read);
            }
        } catch (Exception e) {
            mListener.onError();
            e.printStackTrace();
        } finally {
            mListener.onFinish();
            in.close();
        }
    }

    private void updateProgress(long uploaded, long fileLength) {
        int uploadPercents = (int) (100 * uploaded / fileLength);
        System.out.println(uploadPercents);
//        WaweApp.daggerComponent().mediaUploadBehaviorSubject().setSubjectData(uploadPercents);
    }

    public interface UploadCallbacks {

        void onError();

        void onFinish();
    }
}