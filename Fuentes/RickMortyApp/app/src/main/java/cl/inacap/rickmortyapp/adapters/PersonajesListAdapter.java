package cl.inacap.rickmortyapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.rickmortyapp.R;
import cl.inacap.rickmortyapp.dto.Personaje;

public class PersonajesListAdapter extends ArrayAdapter<Personaje> {
    private List<Personaje> personajes;
    private Activity contexto;

    public PersonajesListAdapter(@NonNull Activity context, int resource, @NonNull List<Personaje> objects) {
        super(context, resource, objects);
        this.personajes = objects;
        this.contexto = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.contexto.getLayoutInflater();
        View fila = inflater.inflate(R.layout.list_personajes, null, true);
        TextView nombreTxt = fila.findViewById(R.id.nombreTv);
        ImageView imagePer = fila.findViewById(R.id.imagen_personaje);
        nombreTxt.setText(this.personajes.get(position).getName());
        Picasso.get().load(this.personajes.get(position).getImage())
                .resize(300,300).centerCrop().into(imagePer);

        return fila;
    }
}
