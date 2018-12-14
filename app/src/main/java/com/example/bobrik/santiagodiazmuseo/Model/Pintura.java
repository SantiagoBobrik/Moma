package com.example.bobrik.santiagodiazmuseo.Model;

public class Pintura {

//    private String image;
    private String name;
    private String artistId;
    private String image;

    public Pintura() {
    }

    public Pintura(String name, String artistId,String imagen) {
        this.name = name;
        this.artistId = artistId;
        this.image = imagen;
    }


    public String getName() {
        return name;
    }
    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Pintura{" +
                "name='" + name + '\'' +
                ", artistId='" + artistId + '\'' +
                ", imagen='" + image + '\'' +
                '}';
    }
}
