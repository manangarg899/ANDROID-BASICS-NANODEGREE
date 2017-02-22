package com.example.manan.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * These all onClick Listeners helps the user to go to new activity or intent when user clicks on any button in main activity..
         */

        Button allSongs = (Button) findViewById(R.id.all_songs);

        allSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, AllSongsActivity.class);
                startActivity(I);
            }
        });

        Button album = (Button) findViewById(R.id.album);

        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(I);
            }
        });

        Button artist = (Button) findViewById(R.id.artist);

        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, ArtistActivity.class);
                startActivity(I);
            }
        });

        Button folder = (Button) findViewById(R.id.folder);

        folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, FolderActivity.class);
                startActivity(I);
            }
        });

        Button playlist = (Button) findViewById(R.id.playlist);

        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, Playlist.class);
                startActivity(I);
            }
        });

        Button recents = (Button) findViewById(R.id.recently_added);

        recents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, RecentlyAddedActivity.class);
                startActivity(I);
            }
        });

        Button playing_now = (Button) findViewById(R.id.now_playing);

        playing_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, NowplayingActivity.class);
                startActivity(I);
            }
        });
    }
}
