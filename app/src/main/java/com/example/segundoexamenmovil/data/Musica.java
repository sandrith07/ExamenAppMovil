package com.example.segundoexamenmovil.data;

public class Musica {
    public int id;
    public String Nombre_Cancion;
    public String Artista;
    public String Album;
    public int Duracion;


    public Musica(int id, String nombre_Cancion, String artista, String album, int duracion) {
        this.id = id;
        Nombre_Cancion = nombre_Cancion;
        Artista = artista;
        Album = album;
        Duracion = duracion;
    }

    public Musica(int id){
        id=id;
    }
}


