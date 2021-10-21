package com.example.appmusic;

import static com.example.appmusic.api.ApiClient.getRetrofit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Adapter.PhotoViewPagerAdapter;
import com.example.appmusic.Adapter.SongAdapter;
import com.example.appmusic.api.Favorite.FavoriteMethod;
import com.example.appmusic.api.Favorite.FavoriteResponse;
import com.example.appmusic.api.Model.Favorite;
import com.example.appmusic.api.Model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePage extends AppCompatActivity {
        private ListView lvFavoriteSong;
        LinearLayout FavoriteContentLayout;
        SongAdapter songAdapter;
        Button btnFavorite;
        Favorite favorite;
        ArrayList<String> mArrayList;
        private ArrayAdapter mAdapter;
        private ViewPager mViewPager;
        private Song song;
       private Timer timer;
        private String[] imageUrls = new String[]{
                "https://dlmod.net/wp-content/uploads/2021/09/acapella-cuoi-thoi-dlmod-1024x576.jpg",
                "http://sohanews.sohacdn.com/thumb_w/660/2014/1-1485911-562645727150932-509505901-o-1395574342813-crop1395720300457p.jpg",
                "https://www.hellolamode.com/wp-content/uploads/2018/07/a-190.jpg",
                "https://duongmonkyhiep.vn/cac-bai-hat-hay-cua-son-tung/imager_1_400_700.jpg"
        };
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
            getSupportActionBar().hide(); // hide the title bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
            setContentView(R.layout.activity_favorite_page);

            songAdapter = new SongAdapter(FavoritePage.this, R.layout.activity_song_item);
            FavoriteContentLayout = findViewById(R.id.FavoriteContent);
            lvFavoriteSong = findViewById(R.id.lvFavoriteSong);
            lvFavoriteSong.setAdapter(songAdapter);
            mViewPager = findViewById(R.id.View_Pager);


            PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(this, imageUrls);
            mViewPager.setAdapter(adapter);

            autoSlideImages();
            //get song list
            getSongInFavorite();
            lvFavoriteSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView SongAdapter, View view, int position, long id) {
                    Intent intent = new Intent(FavoritePage.this, MainActivity.class);
                    intent.putExtra("idFavoriteSong", songAdapter.getItem(position).get_id());
                    startActivity(intent);
                }
            });

            removeSongFromFavorite();
            lvFavoriteSong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> SongAdapter, View view, int position, long id) {
                    final int which_song = position;
                    new AlertDialog.Builder(FavoritePage.this)
                            .setTitle("Are you sure?")
                            .setMessage("Do you want to delete this song")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                   mArrayList.remove(song);
                                   mAdapter.notifyDataSetChanged();

                                }
                            }).setNegativeButton("No", null)
                            .show();
                    return true;
                }
            });
        }

    private void removeSongFromFavorite() {
        FavoriteMethod method = getRetrofit().create(FavoriteMethod.class);
        Call<FavoriteResponse> call = method.removeSongFromFavorite(song.get_id());
        call.enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {

            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

        private void autoSlideImages(){

            // init timer
            if (timer == null){
                timer = new Timer();
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                    int currentItem = mViewPager.getCurrentItem();
                    int totalItem =imageUrls.length-1;
                    if (currentItem <totalItem){
                        currentItem ++;
                        mViewPager.setCurrentItem(currentItem);
                    }else {
                        mViewPager.setCurrentItem(0);
                    }

                    }
                });
                }
            }, 500, 3000);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void getSongInFavorite() {
            FavoriteMethod method = getRetrofit().create(FavoriteMethod.class);
            Call<FavoriteResponse> call = method.getSongInFavorite();
            call.enqueue(new Callback<FavoriteResponse>() {
                @Override
                public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                    if(response.isSuccessful()){
                        favorite = response.body().getData().get("favorite");
                        if(favorite.getSongs().size() > 0)
                            songAdapter.addAll(favorite.getSongs());
                    }
                }

                @Override
                public void onFailure(Call<FavoriteResponse> call, Throwable t) {
                    t.printStackTrace();

                }
            });
        }
        public void combackToHome(View view) {
            Intent intent = new Intent(FavoritePage.this, MainActivity.class);
            startActivity(intent);
        }
}
