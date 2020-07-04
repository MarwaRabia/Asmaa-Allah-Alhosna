package com.example.asmaaallahalhosna.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.asmaaallahalhosna.R;
import com.example.asmaaallahalhosna.pojo.AudioModel;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {
    Toolbar mMainAppToolbar;
    RecyclerView mRecyclerView;
    AudioViewModel mAudioViewModel;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainAppToolbar=findViewById(R.id.main_page_toolbar);
        mRecyclerView=findViewById(R.id.mRecycler);
        setSupportActionBar(mMainAppToolbar);
        getSupportActionBar().setTitle("أسماء الله الحسنىٰ");
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        final AudioAdapter mAudioAdapter=new AudioAdapter(this);
        mAudioViewModel=ViewModelProviders.of(this).get(AudioViewModel.class);
        mAudioViewModel.mArrayListMutableLiveData.observe(this, new Observer<ArrayList<AudioModel>>() {
            @Override
            public void onChanged(ArrayList<AudioModel> audioModels) {
                mAudioAdapter.setListmodel(audioModels);
            }
        });
        mRecyclerView.setAdapter(mAudioAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAudioViewModel.getAudioName();



    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.mShare){
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = getResources().getString(R.string.share_body)+"";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "برنامج اسماء الله الحسنى بدون انترنت");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        return super.onOptionsItemSelected(item);
    }
}