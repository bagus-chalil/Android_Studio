package com.example.mylbs;

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
import com.example.mylbs.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    public static final String nama_pulau[]={"Sumatera","Jawa","Kalimantan","Sulawesi","Bali","NTB","NTT","Maluku","Papua"};
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

        spinner=findViewById(R.id.pilih_pulau);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,nama_pulau);
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

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        double lat,lang;

        switch (position)
        {
            case 0:
                LatLng aceh = new LatLng(4.646528, 96.461367);
                mMap.addMarker(new MarkerOptions().position(aceh).title("NAD"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aceh));

                LatLng sumut = new LatLng(1.880386, 99.623137);
                mMap.addMarker(new MarkerOptions().position(sumut).title("Sumatera Utara"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sumut));

                LatLng sumbar = new LatLng(-0.7394955533520565, 100.84487919695971);
                mMap.addMarker(new MarkerOptions().position(sumbar).title("Sumatera Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sumbar));

                LatLng riau = new LatLng(0.3000956249511976, 101.6658224487192);
                mMap.addMarker(new MarkerOptions().position(riau).title("Riau"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(riau));

                LatLng kpriau = new LatLng(1.6999105,103.9764607);
                mMap.addMarker(new MarkerOptions().position(kpriau).title("Kepulauan Riau"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kpriau));

                LatLng jambi = new LatLng(-1.5299105362678485, 103.5871422746196);
                mMap.addMarker(new MarkerOptions().position(jambi).title("Jambi"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(jambi));

                LatLng sumsel = new LatLng(-3.3281815605483285, 103.993011881978);
                mMap.addMarker(new MarkerOptions().position(sumsel).title("Sumatra Selatan"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sumsel));

                LatLng bengkulu = new LatLng(-3.048235, 101.749088);
                mMap.addMarker(new MarkerOptions().position(bengkulu).title("Bengkulu"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(bengkulu));

                LatLng lampung = new LatLng(-4.557839547692331, 105.39523520689052);
                mMap.addMarker(new MarkerOptions().position(lampung).title("Lampung"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lampung));

                LatLng bangka = new LatLng(-2.7522748686668903, 106.46951914070641);
                mMap.addMarker(new MarkerOptions().position(bangka).title("Bangka Belitung"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(bangka));
                break;
            case 1:
                mMap.clear();
                LatLng banten = new LatLng(-6.4252641922529605, 105.98324258446485);
                mMap.addMarker(new MarkerOptions().position(banten).title("Banten"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(banten));

                LatLng jakarta = new LatLng(-6.0220332605136155, 106.8431621323179);
                mMap.addMarker(new MarkerOptions().position(jakarta).title("Jakarta"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(jakarta));

                LatLng jabar = new LatLng(-6.877778534814364, 107.6516187503712);
                mMap.addMarker(new MarkerOptions().position(jabar).title("Jawa Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(jabar));

                LatLng jateng = new LatLng(-7.153633951646275, 110.0996522169217);
                mMap.addMarker(new MarkerOptions().position(jateng).title("Jawa Tengah"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(jateng));

                LatLng yogya = new LatLng(-7.864097380874183, 110.4978114520557);
                mMap.addMarker(new MarkerOptions().position(yogya).title("D.I. Yogyakarta"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(yogya));

                LatLng jatim = new LatLng(-7.541433355061165, 112.30008986608567);
                mMap.addMarker(new MarkerOptions().position(jatim).title("Jawa Timur"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(jatim));
                break;
            case 2:
                mMap.clear();
                LatLng kalbar = new LatLng(-0.08295273621563898, 110.60270913530456);
                mMap.addMarker(new MarkerOptions().position(kalbar).title("Kalimantan Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kalbar));

                LatLng kalteng = new LatLng(-1.130628622895002, 114.0999740079467);
                mMap.addMarker(new MarkerOptions().position(kalteng).title("Kalimantan Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kalteng));

                LatLng kalsel = new LatLng(-2.7007462168345784, 115.4842514235001);
                mMap.addMarker(new MarkerOptions().position(kalsel).title("Kalimantan Selatan"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kalsel));

                LatLng kaltim = new LatLng(-0.08700082260551026, 116.23132177475114);
                mMap.addMarker(new MarkerOptions().position(kaltim).title("Kalimanatan Timur"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kaltim));

                LatLng kalut = new LatLng(2.5573467201629794, 115.87650216597562);
                mMap.addMarker(new MarkerOptions().position(kalut).title("Kalimantan Utara"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(kalut));

                break;
            case 3:
                mMap.clear();
                LatLng sulut = new LatLng(0.8875712069097091, 124.33828248493838);
                mMap.addMarker(new MarkerOptions().position(sulut).title("Sulawesi Utara"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sulut));

                LatLng gorontalo = new LatLng(0.559907811036932, 123.04774465985209);
                mMap.addMarker(new MarkerOptions().position(gorontalo).title("Gorontalo"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(gorontalo));

                LatLng sulteng = new LatLng(-1.558972753150907, 121.49920478232157);
                mMap.addMarker(new MarkerOptions().position(sulteng).title("SUlawesi Tengah"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sulteng));

                LatLng sulsel = new LatLng(-4.506071976734995, 119.9111313919198);
                mMap.addMarker(new MarkerOptions().position(sulsel).title("Sulawesi Selatan"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sulsel));

                LatLng sulbar = new LatLng(-2.910685178009013, 119.23789741369998);
                mMap.addMarker(new MarkerOptions().position(sulbar).title("Sulawesi Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sulbar));

                LatLng sultengg = new LatLng(-4.249853069310133, 122.23579379223438);
                mMap.addMarker(new MarkerOptions().position(sultengg).title("Sulawesi Tenggara"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sultengg));

                break;
            case 4:
                mMap.clear();
                LatLng bali = new LatLng(-8.310624500464762, 115.08935104068595);
                mMap.addMarker(new MarkerOptions().position(bali).title("Bali"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(bali));
                break;
            case 5:
                mMap.clear();
                LatLng ntb = new LatLng(-8.741022562967528, 117.28901034106465);
                mMap.addMarker(new MarkerOptions().position(ntb).title("Nusa Tenggara Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(ntb));
                break;
            case 6:
                mMap.clear();
                LatLng ntt = new LatLng(-8.567780493937956, 120.78051248558607);
                mMap.addMarker(new MarkerOptions().position(ntt).title("Nusa Tenggara Timur"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(ntt));
                break;
            case 7:
                mMap.clear();
                LatLng maluku = new LatLng(-3.302607077858466, 130.19536894484105);
                mMap.addMarker(new MarkerOptions().position(maluku).title("Maluku"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(maluku));

                LatLng malut = new LatLng(1.3800738689104906, 127.70549260545964);
                mMap.addMarker(new MarkerOptions().position(malut).title("Maluku Utara"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(malut));
                break;

            case 8:
                mMap.clear();
                LatLng papua = new LatLng(-4.079466683930577, 137.97244648706655);
                mMap.addMarker(new MarkerOptions().position(papua).title("Papua"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(papua));

                LatLng papbar = new LatLng(-1.5214198962296315, 133.10905098426673);
                mMap.addMarker(new MarkerOptions().position(papbar).title("Papua Barat"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(papbar));
                break;

            default:
                Toast.makeText(this,"Mohon Pilih Dengan Benar ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}