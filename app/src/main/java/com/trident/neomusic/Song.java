package com.trident.neomusic;

public class Song {

    long id;
    String title;
    String album;

    public Song(long id, String title, String album){
        this.id = id;
        this.title = title;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAlbum() {
        return album;
    }
}
