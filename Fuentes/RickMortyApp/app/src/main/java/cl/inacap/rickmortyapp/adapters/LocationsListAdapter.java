package cl.inacap.rickmortyapp.adapters;

import android.app.Activity;
import android.content.Context;
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
import cl.inacap.rickmortyapp.dto.Location;

public class LocationsListAdapter extends ArrayAdapter<Location> {
    private List<Location> locations;
    private Activity contexto;

    public LocationsListAdapter(@NonNull Activity context, int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        this.locations = objects;
        this.contexto = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.contexto.getLayoutInflater();
        View fila = inflater.inflate(R.layout.list_locations, null, true);
        TextView nombreTxt = fila.findViewById(R.id.nombre_tv_planeta);
        TextView typeTxt = fila.findViewById(R.id.type_tv);
        TextView dimensionTxt = fila.findViewById(R.id.dimension_tv);
        nombreTxt.setText(this.locations.get(position).getName());
        typeTxt.setText(this.locations.get(position).getType());
        dimensionTxt.setText(this.locations.get(position).getDimension());

        return fila;
    }
}
