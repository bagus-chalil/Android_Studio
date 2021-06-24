package com.example.myudinus;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myudinus.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    public static final String nama_fakultas[]={"Udinus","Ilmu Komputer","Teknik","Ilmu Budaya","Ekonomi dan Bisnis","Kesehatan"};
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spinner=findViewById(R.id.pilih_fakultas);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,nama_fakultas);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-6.9828203481222735, 110.40908964848379);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Udinus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        double lat,lang;

        switch (position)
        {
            case 1:
                LatLng fik = new LatLng(-6.982349118293466, 110.40884556745075);
                mMap.addMarker(new MarkerOptions().position(fik).title("Fakultas Ilmu Komputer"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(fik));
                break;
            case 2:
                LatLng teknik = new LatLng(-6.981119927993806, 110.40880149578769);
                mMap.addMarker(new MarkerOptions().position(teknik).title("Fakultas Teknik"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(teknik));
                break;
            case 3:
                LatLng fib = new LatLng(-6.98270992208556, 110.40868745812027);
                mMap.addMarker(new MarkerOptions().position(fib).title("Fakultas Ilmu Budaya"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(fib));
                break;
            case 4:
                LatLng feb = new LatLng(-6.9810966492023345, 110.40927966958559);
                mMap.addMarker(new MarkerOptions().position(feb).title("Fakultas Ekonomi Bisnis"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(feb));
                break;
            case 5:
                LatLng kes = new LatLng(-6.980903952476786, 110.40933178664);
                mMap.addMarker(new MarkerOptions().position(kes).title("Fakultas Kesehatan Masyrakat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kes));
                break;
            default:
                Toast.makeText(this,"Mohon Pilih Dengan Benar ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
