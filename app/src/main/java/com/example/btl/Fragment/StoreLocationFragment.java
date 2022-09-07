package com.example.btl.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.btl.Interface.StoreLocationInterface;
import com.example.btl.Model.Store;
import com.example.btl.Presenter.StoreLocationPresenter;
import com.example.btl.R;
import com.example.btl.View.StoreActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreLocationFragment extends Fragment implements OnMapReadyCallback , StoreLocationInterface {
    GoogleMap mMap;
    SupportMapFragment supportMapFragment;
    private StoreActivity storeActivity;
    private View view;
    private TextView store_address_location;
    private Store store;
    private StoreLocationPresenter storeLocationPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store_location,container,false);
        storeActivity = (StoreActivity) getActivity();
        store = storeActivity.getStore();
        storeLocationPresenter = new StoreLocationPresenter(getContext(),this);
        storeLocationPresenter.Check(store);
        initUI();
        return view;
    }

    private void initUI() {
        supportMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapMain, supportMapFragment);
        fragmentTransaction.commit();
        supportMapFragment.getMapAsync(this);
        store_address_location = view.findViewById(R.id.store_address_location);
        store_address_location.setText(store.getAddress_store());
    }

    @Override
    rpublic void onMapReady(@NonNull GoogleMap googleMap) {
            mMap = googleMap;
            Float a= Float.parseFloat(store.getLat());
            Float b= Float.parseFloat(store.getLot());
            LatLng latLng = new LatLng(a, b);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
            mMap.addMarke(new MarkerOptions().position(latLng).title(store.getName_store()).snippet(store.getAddress_store()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onNullItems() {
        Toast.makeText(storeActivity, "Dữ liệu trống", Toast.LENGTH_SHORT).show();
        return;
    }
}
