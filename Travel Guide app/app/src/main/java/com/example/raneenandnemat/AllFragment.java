package com.example.raneenandnemat;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/*
At this Fragment we displays a list of destinations which are fetched from a JSON
URL using the Volley library,The destinations are displayed in a RecyclerView.
 */

public class AllFragment extends Fragment implements  Adapter.OnItemClickListener {
    public static final String Extra_URL="imgUrl";
    public static final String Extra_City="city";
    public static final String Extra_Country="country";
    public static final String Extra_Continent="continent";
    public static final String Extra_longitude="longitude";
    public static final String Extra_latitude="latitude";
    public static final String Extra_cost="cost";
    public static final String Extra_description="description";

    RecyclerView recyclerView;
    List<Destination> destinations;
    List<Destination> asiaDestinations;
    List<Destination> europeDestinations;
    List<Destination> africaDestinations;
    List<Destination> northAmericaDestinations;
    // I define the flag to know which button is clicked to make the destination in it clickable
    int flag=0;
    private static String JSON_URL="https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca";
    Adapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = view.findViewById(R.id.Destinations_list);
        destinations = new ArrayList<>();
        asiaDestinations = new ArrayList<>();
        europeDestinations = new ArrayList<>();
        africaDestinations = new ArrayList<>();
        northAmericaDestinations = new ArrayList<>();
        extractDestination();
        return view;
    }
    private void extractDestination() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject destinationobj = response.getJSONObject(i);
                        Destination destination = new Destination();
                        destination.setCity(destinationobj.getString("city").toString());
                        destination.setCountry(destinationobj.getString("country").toString());
                        destination.setImg(destinationobj.getString("img").toString());
                        destination.setContinent(destinationobj.getString("continent").toString());
                        destination.setLongitude(destinationobj.getString("longitude").toString());
                        destination.setLatitude(destinationobj.getString("latitude").toString());
                        destination.setCost(destinationobj.getString("cost").toString());
                        destination.setDescription(destinationobj.getString("description".toString()));

  /* The destinations are filtered  by continent by clicking on one of four buttons
 Asia, Europe, Africa, and North America. The destinations are stored in different
 lists based on their continent, and when a button is clicked, the corresponding list
  is passed to the adapter to update the RecyclerView.*/


                        if (destination.getContinent().equals("Asia")) {
                            asiaDestinations.add(destination);
                        } else if (destination.getContinent().equals("Europe")) {
                            europeDestinations.add(destination);
                        } else if (destination.getContinent().equals("Africa")) {
                            africaDestinations.add(destination);
                        } else if (destination.getContinent().equals("North America")) {
                            northAmericaDestinations.add(destination);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }


                Button btnAsia = (Button) getView().findViewById(R.id.btnAsia);
                Button btnEurope = (Button) getView().findViewById(R.id.btnEurope);
                Button btnAfrica=(Button) getView().findViewById(R.id.btnAfrica);
                Button North_America_btn=(Button) getView().findViewById(R.id.North_America_btn);
                btnAsia.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        flag=1;
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new Adapter(getContext(), asiaDestinations);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(AllFragment.this);

                    }


                });
                btnEurope.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        flag=2;

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new Adapter(getContext(), europeDestinations);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(AllFragment.this);
                    }
                });
                btnAfrica.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        flag=3;
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new Adapter(getContext(), africaDestinations);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(AllFragment.this);
                    }
                });

                North_America_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        flag=4;
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new Adapter(getContext(), northAmericaDestinations);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(AllFragment.this);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: "+error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int position) {
/*
When we clicked any of  RecyclerView item(destination), an intent is created to
start a new activity that displays more details about the clicked destination.
The details are passed to the new activity using intent extras.
  */
        if (flag==1) {
            Intent destIntent = new Intent(getActivity(),DestinationActivity.class);

            Destination clickedDestination = asiaDestinations.get(position);

            destIntent.putExtra(Extra_URL, clickedDestination.getImg());
            destIntent.putExtra(Extra_City, clickedDestination.getCity());
            destIntent.putExtra(Extra_Country, clickedDestination.getCountry());
            destIntent.putExtra(Extra_Continent, clickedDestination.getContinent());
            destIntent.putExtra(Extra_latitude, clickedDestination.getLatitude());
            destIntent.putExtra(Extra_longitude, clickedDestination.getLongitude());
            destIntent.putExtra(Extra_cost, clickedDestination.getCost());
            destIntent.putExtra(Extra_description, clickedDestination.getDescription());
            startActivity(destIntent);
        }


        if(flag==2){
            Intent destIntent2 = new Intent(getActivity(),DestinationActivity.class);

            Destination clickedDestination2 = europeDestinations.get(position);

            destIntent2.putExtra(Extra_URL, clickedDestination2.getImg());
            destIntent2.putExtra(Extra_City, clickedDestination2.getCity());
            destIntent2.putExtra(Extra_Country, clickedDestination2.getCountry());
            destIntent2.putExtra(Extra_Continent, clickedDestination2.getContinent());
            destIntent2.putExtra(Extra_latitude, clickedDestination2.getLatitude());
            destIntent2.putExtra(Extra_longitude, clickedDestination2.getLongitude());
            destIntent2.putExtra(Extra_cost, clickedDestination2.getCost());
            destIntent2.putExtra(Extra_description, clickedDestination2.getDescription());

            startActivity(destIntent2);
        }


        if(flag==3){
            Intent destIntent3 = new Intent(getActivity(),DestinationActivity.class);

            Destination clickedDestination3 = africaDestinations.get(position);

            destIntent3.putExtra(Extra_URL, clickedDestination3.getImg());
            destIntent3.putExtra(Extra_City, clickedDestination3.getCity());
            destIntent3.putExtra(Extra_Country, clickedDestination3.getCountry());
            destIntent3.putExtra(Extra_Continent, clickedDestination3.getContinent());
            destIntent3.putExtra(Extra_latitude, clickedDestination3.getLatitude());
            destIntent3.putExtra(Extra_longitude, clickedDestination3.getLongitude());
            destIntent3.putExtra(Extra_cost, clickedDestination3.getCost());
            destIntent3.putExtra(Extra_description, clickedDestination3.getDescription());

            startActivity(destIntent3);
        }


        if(flag==4){

            Intent destIntent4 = new Intent(getActivity(),DestinationActivity.class);

            Destination clickedDestination4 = northAmericaDestinations.get(position);

            destIntent4.putExtra(Extra_URL, clickedDestination4.getImg());
            destIntent4.putExtra(Extra_City, clickedDestination4.getCity());
            destIntent4.putExtra(Extra_Country, clickedDestination4.getCountry());
            destIntent4.putExtra(Extra_Continent, clickedDestination4.getContinent());
            destIntent4.putExtra(Extra_latitude, clickedDestination4.getLatitude());
            destIntent4.putExtra(Extra_longitude, clickedDestination4.getLongitude());
            destIntent4.putExtra(Extra_cost, clickedDestination4.getCost());
            destIntent4.putExtra(Extra_description, clickedDestination4.getDescription());

            startActivity(destIntent4);
        }
    }
}