package com.example.raneenandnemat;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link editproFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editproFragment extends Fragment {




    public editproFragment() {
        // Required empty public constructor
    }


    public static editproFragment newInstance(String param1, String param2) {
        editproFragment fragment = new editproFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView fname = (TextView) view.findViewById(R.id.fname);
        TextView lname = (TextView) view.findViewById(R.id.lname);
        TextView password = (TextView) view.findViewById(R.id.pass);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        DataBaseHelper d = new DataBaseHelper(getContext());
        String email = d.getEmail();
        String Continent = d.getCONTINENT(email);

// Define the array of items
        String[] items = {Continent,"Asia", "Europe", "Africa", "North America"};

// Create an ArrayAdapter using the array of items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items);

// Set the adapter for the spinner
        spinner.setAdapter(adapter);


        DataBaseHelper db1;
        db1 = new DataBaseHelper(getContext());

        Cursor cursor1 = db1.getProfileDate1();

        if (cursor1.getCount() != 0) {

            while (cursor1.moveToNext()) {

                fname.setText(cursor1.getString(0));
                lname.setText(cursor1.getString(1));
            }
        }





        Cursor cursor2 = db1.getProfilePass();

        if (cursor2.getCount() != 0) {

            while (cursor2.moveToNext()) {

                password.setText(cursor2.getString(0));
            }
        }



        Button button = (Button) view.findViewById(R.id.updateBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DataBaseHelper db1 ;
                db1 = new DataBaseHelper(getContext());
                db1.UpdateUser(email,fname.getText().toString(),lname.getText().toString(),password.getText().toString(),(String) spinner.getSelectedItem());
                Toast.makeText(getActivity(), "All Updated", Toast.LENGTH_LONG).show();

            }
        });


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editpro, container, false);
    }


}