package com.junbaor.kuworadio.model;

/**
 * Created by Administrator on 2016/1/28.
 */
public class Music {
    private String yr;
    private String musicrid;
    private String name;
    private String artist;
    private String album;
    private String formats;

    public String getYr() {
        return yr;
    }

    public void setYr(String yr) {
        this.yr = yr;
    }

    public String getMusicrid() {
        return musicrid;
    }

    public void setMusicrid(String musicrid) {
        this.musicrid = musicrid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }
}
