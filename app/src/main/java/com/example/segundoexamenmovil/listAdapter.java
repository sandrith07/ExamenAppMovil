package com.example.segundoexamenmovil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segundoexamenmovil.data.Musica;

import java.util.LinkedList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.MusicaViewHolder> {
    private int positionActual;
    private Context contextActual;
    private LayoutInflater mInflater;
    private final LinkedList<Musica> listaMusica ;

    public listAdapter(Context context, LinkedList<Musica> lista) {
        contextActual = context;
        mInflater = LayoutInflater.from(context);
        this.listaMusica = lista;
    }

    @NonNull
    @Override
    public listAdapter.MusicaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.itemlist, parent, false);
        return new MusicaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull listAdapter.MusicaViewHolder holder,int position) {
        Musica musica = this.listaMusica.get(position);
        holder.Info.setText(musica.Nombre_Cancion + " - "+  musica.Artista + " - " +  musica.Duracion);

    }

    @Override
    public int getItemCount() {
        return listaMusica.size();
    }


    class MusicaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView Info;
        private listAdapter adapter;

        public MusicaViewHolder(@NonNull View itemView, listAdapter adapter) {
            super(itemView);
            Info = itemView.findViewById(R.id.title);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {

        }
    }
}


