package com.example.mzikalar;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton ibPlay, ibPause, ibNext, ibList;
    MediaPlayer mediaPlayer;
    ArrayList<String> musicList;
    TextView mTitle;
    int currentMusic = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibPlay = findViewById(R.id.ibPlay);
        ibPause = findViewById(R.id.ibPause);
        ibNext = findViewById(R.id.ibNext);
        ibList = findViewById(R.id.ibMusicList);
        mTitle = findViewById(R.id.tvMusicTitle);
        musicList = new ArrayList<>();
        ListRaw();
        ibPlay.setOnClickListener(this);
        ibPause.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibList.setOnClickListener(this);
        mTitle.setText(musicList.get(0));
        mediaPlayer = MediaPlayer.create(this, R.raw.mustafacecelibedel);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibPlay: {
                mediaPlayer.start();
                break;
            }
            case R.id.ibNext: {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                if (currentMusic < musicList.size() - 1) {
                    currentMusic += 1;
                } else {
                    currentMusic = 0;
                }
                String musicUri = "android.resource://" + getPackageName() + "/raw/" + musicList.get(currentMusic);
                mediaPlayer = MediaPlayer.create(this, Uri.parse(musicUri));
                mediaPlayer.start();
                mTitle.setText(musicList.get(currentMusic));
                Log.d("butonKesfet", "onClick: Next ");
                break;
            }
            case R.id.ibPause: {
                mediaPlayer.pause();
                break;
            }
            case R.id.ibMusicList: {

                Log.d("butonKesfet", "onClick: MusicList ");
                break;
            }
            default: {
                Log.d("butonKesfet", "onClick: Unkown ");
                break;
            }
        }

    }
    public void ListRaw() {

        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            musicList.add(fields[i].getName());
        }
//
    }
}