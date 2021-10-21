package com.example.appmusic.Adapter;


import static com.example.appmusic.api.ApiClient.getRetrofit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appmusic.R;
import com.example.appmusic.api.Favorite.FavoriteCheckerResponse;
import com.example.appmusic.api.Favorite.FavoriteMethod;
import com.example.appmusic.api.Favorite.FavoriteResponse;
import com.example.appmusic.api.Model.Song;
import com.squareup.picasso.Picasso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongAdapter extends ArrayAdapter<Song> {
    Activity context;
    int resource;

    public SongAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View customView = layoutInflater.inflate(this.resource, null);
        TextView txtPostName = customView.findViewById(R.id.txtSongName);
        TextView txtAuthorName = customView.findViewById(R.id.txtAuthor);
        ImageView imgSong = customView.findViewById(R.id.imgSong);
        Song song = getItem(position);
        txtPostName.setText(song.getName());
        if(song.getImageUrl() != null && !song.getImageUrl().isEmpty()){
            Picasso.get().load(song.getImageUrl()).error(R.drawable.ic_baseline_not_started_24).resize(100,100).into(imgSong);
        }
        else if(song.getSinger().getImageUrl() !=null && !song.getSinger().getImageUrl().isEmpty()){
            Picasso.get().load(song.getSinger().getImageUrl()).error(R.drawable.ic_baseline_not_started_24).resize(100,100).into(imgSong);
        }
        else {
            imgSong.setImageResource(R.drawable.ic_baseline_not_started_24);
        }
        txtAuthorName.setText(song.getSinger().getName());

        Button btnRemove = customView.findViewById(R.id.btnRemove);
        Button btnLove = customView.findViewById(R.id.btnLove);
        FavoriteMethod method = getRetrofit().create(FavoriteMethod.class);
        Call<FavoriteCheckerResponse> call = method.checkIfExist(song.get_id());
        call.enqueue(new Callback<FavoriteCheckerResponse>() {
            @Override
            public void onResponse(Call<FavoriteCheckerResponse> call, Response<FavoriteCheckerResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getData().get("message")==true){
                        btnLove.setVisibility(View.VISIBLE);
                        btnRemove.setVisibility(View.INVISIBLE);
                    }else{
                        btnLove.setVisibility(View.INVISIBLE);
                        btnRemove.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<FavoriteCheckerResponse> call, Throwable t) {

            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call api add to favorite. waiting for token to complete
                FavoriteMethod method = getRetrofit().create(FavoriteMethod.class);
                Call<FavoriteResponse> caller = method.addSongToFavorite(song.get_id());
                caller.enqueue(new Callback<FavoriteResponse>() {
                    @Override
                    public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                        if(response.isSuccessful()){
                            btnLove.setVisibility(View.VISIBLE);
                            btnRemove.setVisibility(View.INVISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriteResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call api add to favorite. waiting for token to complete
                FavoriteMethod method = getRetrofit().create(FavoriteMethod.class);
                Call<FavoriteResponse> call = method.removeSongFromFavorite(song.get_id());
                call.enqueue(new Callback<FavoriteResponse>() {
                    @Override
                    public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                        if(response.isSuccessful()){
                            btnLove.setVisibility(View.INVISIBLE);
                            btnRemove.setVisibility(View.VISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<FavoriteResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });
        return customView;
    }
}
