package com.example.segundoexamenmovil.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segundoexamenmovil.listAdapter;
import com.example.segundoexamenmovil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class ListaMusica extends AsyncTask<Void, Void, Void> {

    LinkedList<Musica> dataIntern = new LinkedList<>();
    LinkedList<Musica> listaMusica = new LinkedList<>();
    Activity ListaMusica;

    public ListaMusica(Activity activityContext, LinkedList<Musica> dataIntern) {
        this.ListaMusica = activityContext;
        this.dataIntern = dataIntern;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(this.ListaMusica.getBaseContext(), "Cargando canciones por favor espere ...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder canciones = new StringBuilder();
        try {
            URL url = new URL("https://api.deezer.com/playlist/93489551/tracks");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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
            JSONArray listadetracks = jsonObj.getJSONArray("data");

            for (int i = 0; i < listadetracks.length(); i++) {

                JSONObject track = listadetracks.getJSONObject(i);
                Musica musica = new Musica(track.getInt("id"));

                JSONObject artista = track.getJSONObject("artist");
                musica.Artista = artista.getString("name");

                JSONObject album = track.getJSONObject("album");
                musica.Album = album.getString("title");

                musica.Nombre_Cancion= track.getString("title");
                musica.Duracion = track.getInt("duration");

                listaMusica.add(musica);
            }
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
        if(this.dataIntern.size()>0) {
            for (int i = 0; i < this.dataIntern.size(); i++) {
                listaMusica.addLast(this.dataIntern.get(i));
            }
        }
        RecyclerView listaM;
        listAdapter adapterList = new listAdapter(this.ListaMusica.getBaseContext(), listaMusica);
        listaM = ListaMusica.findViewById(R.id.listaMusica);
        listaM.setAdapter(adapterList);
        listaM.setLayoutManager(new LinearLayoutManager(this.ListaMusica.getBaseContext()));
    }

}

