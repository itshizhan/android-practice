package com.itshizhan.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener downloadListener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.downloadListener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream  = null;
        RandomAccessFile savedFiled  = null;
        File file = null;

        // 已经下载文件的长度
        try {
            long downloadedLength = 0;
            String downloadUrl = params[0];

            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .getPath();
            file = new File(directory  +  fileName);
            if(file.exists()){
                downloadedLength = file.length();
            }

            long contentLength = getContentLength(downloadUrl);
            if(contentLength == 0){
                return TYPE_FAILED;
            }else if(contentLength == downloadedLength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE","bytes="+downloadedLength+"-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if(response!=null){
                inputStream = response.body().byteStream();
                savedFiled = new RandomAccessFile(file,"rw");
                savedFiled.seek(downloadedLength);
                byte [] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = inputStream.read(b))!=-1){
                    if(isCanceled){
                        return  TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else{
                        total+=len;
                        savedFiled.write(b,0,len);
                        int progress = (int) (((total+downloadedLength)*100)/contentLength);
                        publishProgress(progress);


                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(savedFiled!=null){
                try {
                    savedFiled.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(isCanceled&&file!=null){
                file.delete();
            }

        }



        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        if(progress>lastProgress){
            downloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        super.onPostExecute(status);
        switch (status){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                downloadListener.onPaused();
                break;
            case TYPE_CANCELED:
                downloadListener.onCanceled();
                break;
            default:
                break;

        }
    }

    public void pausedDownload(){
        isPaused = true;
    }
    public void canceledDownlaod(){
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        Response response = client.newCall(request).execute();
        if(response!=null&&response.isSuccessful()){
            if (response.body() != null) {
                assert response.body() != null;
                return response.body().contentLength();
            }else{
                return 0;
            }
        }
        return  0;
    }


}
