package com.example.asmaaallahalhosna.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmaaallahalhosna.R;
import com.example.asmaaallahalhosna.pojo.AudioModel;


import java.util.ArrayList;

import static android.content.Context.*;
import static androidx.core.content.ContextCompat.getSystemService;

class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewModelAudio> implements View.OnClickListener {
    Button play, forward, back;
    SeekBar mSeekBar;
    private  Context mContext;
    TextView mTextView1,mTextView2,mspeed;
    @NonNull
    private ArrayList<AudioModel> listmodel = new ArrayList<>();
    private MediaPlayer mMediaPlayer;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;


    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    // initialize View out RecyclerView
    public AudioAdapter(Context context) {
        play = ((Activity) context).findViewById(R.id.playbtn);
        forward = ((Activity) context).findViewById(R.id.forbtn);
        back = ((Activity) context).findViewById(R.id.backbtn);
        mSeekBar = ((Activity) context).findViewById(R.id.seekbar);
        mTextView1= ((Activity) context).findViewById(R.id.total);
        mTextView2= ((Activity) context).findViewById(R.id.coun);
        mspeed= ((Activity) context).findViewById(R.id.speed);
        mContext=context;
    }

    public void setListmodel(@NonNull ArrayList<AudioModel> listmodel) {
        this.listmodel = listmodel;
        notifyDataSetChanged();
    }

    @Override
    public ViewModelAudio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewModelAudio(LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewModelAudio holder, final int position) {
        holder.mTextView.setText(listmodel.get(position).getName());
        play.setOnClickListener(this);
        forward.setOnClickListener(this);
        back.setOnClickListener(this);
        mspeed.setOnClickListener(this);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            final AudioModel mAudioModel = listmodel.get(position);
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(holder.itemView.getContext(), mAudioModel.getAudio());
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mSeekBar.setMax(mMediaPlayer.getDuration());
                        mMediaPlayer.start();
                        mTextView1.setText(createTimeLabel(mMediaPlayer.getDuration()));

                        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                        changeSeekBar();
                    }


                });
                mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mMediaPlayer.seekTo(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return listmodel.size();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playbtn:
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    play.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);

                } else {
                    try {
                        mMediaPlayer.start();
                        play.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
                        changeSeekBar();
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }
                break;
            case R.id.forbtn:
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() - 5000);

                break;
            case R.id.backbtn:
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() + 5000);
                break;
            case R.id.speed:
               changeSpeed();


        }

    }

    private void changeSeekBar() {
        try {
            mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            mTextView2.setText(createTimeLabel(mMediaPlayer.getCurrentPosition()));

            if (mMediaPlayer.isPlaying() && mMediaPlayer != null) {
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        changeSeekBar();
                    }
                };
                mHandler.postDelayed(mRunnable, 1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Clean up the media player by releasing its resources.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            play.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
            mspeed.setTextColor(Color.GRAY);

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }


    public class ViewModelAudio extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewModelAudio(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.name);


        }
    }
    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;
        return timeLabel;

    }
    //to chang the speed of audio
    @RequiresApi(api = Build.VERSION_CODES.M)
    private  void changeSpeed(){
        float speed = 1.5f;
        if ( mMediaPlayer!=null &&mMediaPlayer.isPlaying()){
        float s=mMediaPlayer.getPlaybackParams().getSpeed();
        if(s==1f){
            mMediaPlayer.setPlaybackParams(mMediaPlayer.getPlaybackParams().setSpeed(speed));
            mspeed.setTextColor(Color.WHITE);

        }
        else {
            mMediaPlayer.setPlaybackParams(mMediaPlayer.getPlaybackParams().setSpeed(1));
            mspeed.setTextColor(Color.GRAY);



        }}

    }
}
