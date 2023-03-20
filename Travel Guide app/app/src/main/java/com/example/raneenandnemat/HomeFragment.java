package com.example.raneenandnemat;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Random;


public class HomeFragment extends Fragment {
    private TextView cityTextView, countryTextView, continentTextView, longitudeTextView, latitudeTextView, costTextView, descriptionTextView;
    private ImageView imageView;

    String Email,CONTINENT;
    DataBaseHelper dataBaseHelper;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Email = getArguments().getString("EMAIL");
            dataBaseHelper = new DataBaseHelper(getActivity(), "RaneenAndNemat", null, 1);
            CONTINENT = dataBaseHelper.getCONTINENT(Email);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        cityTextView = view.findViewById(R.id.city_detail);
        countryTextView = view.findViewById(R.id.country_detail);
        continentTextView = view.findViewById(R.id.continent_detail);
        longitudeTextView = view.findViewById(R.id.longitude_detail);
        latitudeTextView = view.findViewById(R.id.latitude_detail);
        costTextView = view.findViewById(R.id.cost_detail);
        descriptionTextView = view.findViewById(R.id.description_detail);
        imageView = view.findViewById(R.id.image_view_detail);

        retrieveJSON();

        return view;
    }


    private void retrieveJSON() {
            String url = "https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca";

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            ArrayList<JSONObject> asianCountries = new ArrayList<>();
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject country = response.getJSONObject(i);
                                    if (country.getString("continent").equals(CONTINENT)) {
                                        asianCountries.add(country);
                                    }
                                }

                                if (!asianCountries.isEmpty()) {
                                    Random random = new Random();
                                    int randomIndex = random.nextInt(asianCountries.size());
                                    JSONObject randomCountry = asianCountries.get(randomIndex);

                                    cityTextView.setText(randomCountry.getString("city"));
                                    countryTextView.setText(randomCountry.getString("country"));
                                    continentTextView.setText(randomCountry.getString("continent"));
                                    longitudeTextView.setText(randomCountry.getString("longitude"));
                                    latitudeTextView.setText(randomCountry.getString("latitude"));
                                    costTextView.setText(randomCountry.getString("cost"));
                                    descriptionTextView.setText(randomCountry.getString("description"));
                                    Glide.with(getContext()).load(randomCountry.getString("img")).into(imageView);
                                }
                            } catch (JSONException e) {
                                // handle error
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // handle error
                        }
                    });


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}






