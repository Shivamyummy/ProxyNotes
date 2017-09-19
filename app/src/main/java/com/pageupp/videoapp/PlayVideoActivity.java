package com.pageupp.videoapp;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideoActivity extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog pd;
    MediaController controller;
    int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
     //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);


        setContentView(R.layout.activity_play_video);

        videoView=(VideoView)findViewById(R.id.videoView);

        if (controller == null) {

            controller = new MediaController(PlayVideoActivity.this);

        }



        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            final int position = (int) bundle.get("position");
            pd = new ProgressDialog(this);
            pd.setTitle(MainActivity.videotitle[position]);
            pd.setMessage("Please wait...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();

            try{

                controller.setAnchorView(videoView);
                Uri vid = Uri.parse(MainActivity.urllist[position]);
                videoView.setMediaController(controller);
                videoView.setVideoURI(vid);
            }catch(Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            videoView.requestFocus();
            videoView.setKeepScreenOn(true);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {

                    pd.dismiss();
                    videoView.seekTo(position);
                    if(position == 0){
                        pd.dismiss();
                        videoView.start();
                    }
                    else
                        {
                            videoView.pause();
                        }


                }
            });

        }
        else{
            //
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position",videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position=savedInstanceState.getInt("Position");
        videoView.seekTo(position);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}

