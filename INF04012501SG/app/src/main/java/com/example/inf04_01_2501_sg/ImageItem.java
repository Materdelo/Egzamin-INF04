package com.example.inf04_01_2501_sg;

public class ImageItem {
    private int id;
    private String alt;
    private String filename;
    private int category;
    private int downloads;

    public ImageItem(int id, String alt, String filename, int category, int downloads) {
        this.id = id;
        this.alt = alt;
        this.filename = filename;
        this.category = category;
        this.downloads = downloads;
    }


    public void increaseDownload(){
        this.downloads++;
    }
    public String getFilename() { return filename; }
    public int getCategory() { return category; }
    public int getDownloads() { return downloads; }
}
