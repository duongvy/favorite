package com.example.appmusic;

import static com.example.appmusic.api.ApiClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appmusic.Adapter.SongAdapter;
import com.example.appmusic.api.Album.AlbumMethods;
import com.example.appmusic.api.Album.AlbumResponse;
import com.example.appmusic.api.Model.Album;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumPage extends AppCompatActivity {

    ListView lvSongInAlbum;
    SongAdapter songAdapter;
    TextView txtAlbumName;
    ImageView imgAlbum;
    Button btnBack;
    Button btnPlay;
    LinearLayout albumContentLayout;
    private Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_album_page);

        txtAlbumName = findViewById(R.id.txtAlbumName);
        imgAlbum = findViewById(R.id.imgAlbumThump);
        btnBack = findViewById(R.id.btnBack);
        btnPlay = findViewById(R.id.btnPlay);
        albumContentLayout = findViewById(R.id.albumContent);
        songAdapter = new SongAdapter(AlbumPage.this, R.layout.activity_song_item);
        lvSongInAlbum = findViewById(R.id.lvAlbumSong);
        lvSongInAlbum.setAdapter(songAdapter);

        Intent intent = getIntent();
        String albumId = intent.getStringExtra("albumId");
        getSongsInAlbum(albumId == null ? "6156f56ee3fd24179c29a354" : albumId);
        lvSongInAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(AlbumPage.this, PlayMusicActivity.class);//sửa lại thành page play song khi có
                Intent intent = new Intent(AlbumPage.this, Home.class);
                intent.putExtra("songId", songAdapter.getItem(position).get_id());
                startActivity(intent);
            }
        });


    }

    private void getSongsInAlbum(String albumId) {
        AlbumMethods method = getRetrofit().create(AlbumMethods.class);
        Call<AlbumResponse> call = method.getById(albumId);
        call.enqueue(new Callback<AlbumResponse>()  {
            @Override
            public void onResponse(Call<AlbumResponse> call, Response<AlbumResponse> response) {
                if(response.isSuccessful()){
                    album = response.body().getData().get("albums");
                    songAdapter.addAll(album.getSongs());
                    songAdapter.add(album.getSongs().get(0));
                    txtAlbumName.setText(album.getName());
                    if(!album.getImageUrl().isEmpty()){
                        Picasso.get().load(album.getImageUrl()).error(R.drawable.ic_baseline_not_started_24).resize(1000, 1000).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                imgAlbum.setImageBitmap(bitmap);
                                BitmapDrawable bmDraw = new BitmapDrawable(getBaseContext().getResources(), bitmap);
                                bmDraw.setAlpha(80);
                                albumContentLayout.setBackground(bmDraw);


                            }

                            @Override
                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });

                    }
                    else {
                        imgAlbum.setImageResource(R.drawable.ic_baseline_not_started_24);
                        albumContentLayout.setBackgroundResource(R.drawable.default_background);
                    }

                }
            }

            @Override
            public void onFailure(Call<AlbumResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void playAlbum(View view) {
        //Intent intent = new Intent(AlbumPage.this, PlayAlbumActivity.class);
        Intent intent = new Intent(AlbumPage.this, Home.class);
        intent.putExtra("idAlbum", album.get_id());
        startActivity(intent);
    }

    public void combackToHome(View view) {
        Intent intent = new Intent(AlbumPage.this, MainActivity.class);//sửa lại thành home khi có
        startActivity(intent);
    }
}