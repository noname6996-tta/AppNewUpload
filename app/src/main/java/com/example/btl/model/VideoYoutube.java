package com.example.btl.model;

public class VideoYoutube {
    private String Title;
    private String Thumbnail;
    private String idVideo;

    public VideoYoutube(String title, String thumbnail, String idVideo) {

        Title = title;
        Thumbnail = thumbnail;
        this.idVideo = idVideo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }
}
