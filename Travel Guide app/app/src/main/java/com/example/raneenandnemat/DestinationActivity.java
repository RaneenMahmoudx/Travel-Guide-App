package com.example.raneenandnemat;


import static com.example.raneenandnemat.AllFragment.Extra_City;
import static com.example.raneenandnemat.AllFragment.Extra_URL;
import static com.example.raneenandnemat.AllFragment.Extra_description;
import static com.example.raneenandnemat.AllFragment.Extra_latitude;
import static com.example.raneenandnemat.AllFragment.Extra_longitude;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        Intent intent = getIntent();
        ToggleButton buttonAttachDesc = findViewById(R.id.Description);
        ToggleButton buttonAttachImage = findViewById(R.id.destinationImage);
        ToggleButton buttonAttachMap = findViewById(R.id.destinationMap);
        final DescriptionFragment DescriptionFragment = new DescriptionFragment();
        final ImgFragment imageFragment = new ImgFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        String city = intent.getStringExtra(Extra_City);
        String latitude = intent.getStringExtra(Extra_latitude);
        String longitude = intent.getStringExtra(Extra_longitude);
        TextView cityView = findViewById(R.id.city_detail);
        cityView.setText(city);


        buttonAttachDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = intent.getStringExtra(Extra_description);
                Bundle bundle = new Bundle();
                bundle.putString("description", description);
                DescriptionFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(DescriptionFragment.isAdded()) {
                    fragmentTransaction.remove(DescriptionFragment);
                } else {
                    fragmentTransaction.add(R.id.LinearLayoutCompat, DescriptionFragment, "DetailsFrag");
                }
                fragmentTransaction.commit();
            }
        });




        buttonAttachImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageUrl = intent.getStringExtra(Extra_URL);
                Bundle bundle = new Bundle();
                bundle.putString("img", imageUrl);
                imageFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(imageFragment.isAdded()) {
                    fragmentTransaction.remove(imageFragment);
                } else {
                    fragmentTransaction.add(R.id.LinearLayoutCompat, imageFragment, "ImageFrag");
                }
                fragmentTransaction.commit();
            }
        });




        buttonAttachMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double longitudeValue = Double.parseDouble(longitude);
                double latitudeValue = Double.parseDouble(latitude);

                String uri = "geo:" + latitudeValue + "," + longitudeValue;
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(mapsIntent);
            }
        });
    }


}
