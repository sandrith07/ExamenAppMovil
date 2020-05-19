package com.example.segundoexamenmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.segundoexamenmovil.data.BuscarMusica;
import com.example.segundoexamenmovil.data.Musica;

import java.util.ArrayList;

public class AddMusica extends AppCompatActivity {


    EditText nombreCancion;
    EditText artista;
    EditText album;
    EditText duracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_musica);
        nombreCancion = (EditText) findViewById(R.id.txt_nombre_cancion);
        artista = (EditText) findViewById(R.id.txt_artista_cancion);
        album = (EditText) findViewById(R.id.txt_album_cancion);
        duracion =  (EditText) findViewById(R.id.txt_duracion_cancion);
        SearchView BuscarMusica = (SearchView) findViewById (R.id.BuscarMusica);
        BuscarMusica.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<EditText> editTexts = new ArrayList<>();
                editTexts.add(nombreCancion);
                editTexts.add(artista);
                editTexts.add(album);
                editTexts.add(duracion);
                new BuscarMusica(query,AddMusica.this, editTexts).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_add_musica, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu

        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                String nombre= this.nombreCancion.getText().toString();

                if (nombre.equals("")) {
                    Toast.makeText(this, "No se ha encontrado las cancion a guardar",
                            Toast.LENGTH_LONG).show();
                }else{
                    Musica data = new Musica(1);
                    data.Nombre_Cancion = nombreCancion.getText().toString();
                    data.Duracion = Integer.valueOf(duracion.getText().toString());
                    data.Album = album.getText().toString();
                    data.Artista = artista.getText().toString();
                    MainActivity.listaMusica.addLast(data);
                    finish();
                }

                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for not

                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
