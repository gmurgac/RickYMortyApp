package cl.inacap.rickmortyapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.rickmortyapp.adapters.LocationsListAdapter;
import cl.inacap.rickmortyapp.adapters.PersonajesListAdapter;
import cl.inacap.rickmortyapp.dto.Location;
import cl.inacap.rickmortyapp.dto.Personaje;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UbicacionesFragment} factory method to
 * create an instance of this fragment.
 */
public class UbicacionesFragment extends Fragment {

    private RequestQueue queue;
    private List<Location> locations = new ArrayList<>();
    private LocationsListAdapter adaptador;
    private ListView listViewLoc;

    public UbicacionesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this.getActivity());

        this.listViewLoc = getView().findViewById(R.id.listViewLoc);
        this.adaptador = new LocationsListAdapter(this.getActivity(), R.layout.list_locations, this.locations);
        this.listViewLoc.setAdapter(this.adaptador);
        this.locations.clear();
        for (int i = 1; i < 3; i++) {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, "https://rickandmortyapi.com/api/location/?page="+i
                    , null
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        Location[] locationObt = new Gson().fromJson(response.getString("results"), Location[].class);
                        locations.addAll(Arrays.asList(locationObt));
                    } catch (Exception e) {
                        locations = null;
                    } finally {
                        adaptador.notifyDataSetChanged();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    locations = null;
                    adaptador.notifyDataSetChanged();
                }

            });
            queue.add(jsonReq);

        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubicaciones, container, false);
    }
}