package com.junbaor.kuworadio.model;

import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
public class Kuwo {
    private String ret;
    private String total;
    private List<Music> musiclist;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Music> getMusiclist() {
        return musiclist;
    }

    public void setMusiclist(List<Music> musiclist) {
        this.musiclist = musiclist;
    }
}


