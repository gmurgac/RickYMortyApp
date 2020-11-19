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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.rickmortyapp.adapters.PersonajesListAdapter;
import cl.inacap.rickmortyapp.dto.Personaje;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonajesFragment} factory method to
 * create an instance of this fragment.
 */
public class PersonajesFragment extends Fragment {
    private RequestQueue queue;
    private List<Personaje> personajes = new ArrayList<>();
    private PersonajesListAdapter adaptador;
    private ListView listViewPer;




    public PersonajesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this.getActivity());

        this.listViewPer = getView().findViewById(R.id.lista_personajes);
        this.adaptador = new PersonajesListAdapter(this.getActivity(), R.layout.list_personajes, this.personajes);
        this.listViewPer.setAdapter(this.adaptador);
        this.personajes.clear();

        for (int i = 1; i < 10; i++) {
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, "https://rickandmortyapi.com/api/character/?page="+i
                    , null
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {

                        Personaje[] personajeObt = new Gson().fromJson(response.getString("results"), Personaje[].class);
                        personajes.addAll(Arrays.asList(personajeObt));
                    } catch (Exception e) {
                        personajes = null;
                    } finally {
                        adaptador.notifyDataSetChanged();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    personajes = null;
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
        return inflater.inflate(R.layout.fragment_personajes, container, false);
    }
}