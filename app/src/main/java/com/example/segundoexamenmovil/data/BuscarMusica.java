package com.example.segundoexamenmovil.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class BuscarMusica extends AsyncTask<Void, Void, Void> {
    String buscarMusica = "";
    Activity context;
    ArrayList<EditText> inputs;
    Musica musica = null;

    public BuscarMusica(String BuscarMusica, Activity context, ArrayList<EditText> inputs) {
        this.buscarMusica = BuscarMusica;
        this.context = context;
        this.inputs = inputs;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder canciones = new StringBuilder();
        try {
            URL url = new URL("https://api.deezer.com/search?q=" + this.buscarMusica);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                canciones.append(scanner.next());
            }

            JSONObject jsonObj = new JSONObject(canciones.toString());
            JSONArray cancion = jsonObj.getJSONArray("data");

            JSONObject track = cancion.getJSONObject(0);
            musica = new Musica(track.getInt("id"));
            JSONObject artista = track.getJSONObject("artist");
            musica.Artista = artista.getString("name");
            JSONObject album = track.getJSONObject("album");
            musica.Album = album.getString("title");
            musica.Nombre_Cancion = track.getString("title");
            musica.Duracion = track.getInt("duration");

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(this.context.getBaseContext(),  this.buscarMusica + ": Musica encontrada: " , Toast.LENGTH_SHORT).show();
        inputs.get(0).setText(musica.Nombre_Cancion);
        inputs.get(1).setText(musica.Artista);
        inputs.get(2).setText(musica.Album);
        inputs.get(3).setText(String.valueOf(musica.Duracion));
    }

}

