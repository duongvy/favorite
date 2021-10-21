package com.example.appmusic;

import static com.example.appmusic.api.ApiClient.getRetrofit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.appmusic.Adapter.SongAdapter;
import com.example.appmusic.api.Favorite.FavoriteMethod;
import com.example.appmusic.api.Favorite.FavoriteResponse;
import com.example.appmusic.api.Model.Favorite;

import java.util.ArrayList;

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
        ArrayAdapter<String> mAdapter;
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
            //remove song from favorite list
            lvFavoriteSong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    new AlertDialog.Builder(FavoritePage.this)
                            .setTitle("Do you want to remove" + mArrayList.get(i) + "from list")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mArrayList.remove(i);
                                    mAdapter.notifyDataSetChanged();

                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                    return false;
                }
            });
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
