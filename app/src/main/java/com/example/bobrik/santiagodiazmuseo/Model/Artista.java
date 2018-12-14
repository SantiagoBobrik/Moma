package com.example.bobrik.santiagodiazmuseo.Model;

public class Artista {
    private String Influenced_by;
    private String artistId;
    private  String name;
    private String nationality;

    public Artista() {
    }

    public Artista(String influenced_by, String artistId, String name, String nationality) {
        Influenced_by = influenced_by;
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "Influenced_by='" + Influenced_by + '\'' +
                ", artistId='" + artistId + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
