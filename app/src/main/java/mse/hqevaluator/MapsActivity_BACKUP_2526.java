package mse.hqevaluator;

<<<<<<< HEAD
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;
=======
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
>>>>>>> 13f2942ca6980fdcbeb5ef3b3089109549747525

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
<<<<<<< HEAD
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.maps.android.heatmaps.WeightedLatLng;
import com.google.maps.android.heatmaps.Gradient;
import android.graphics.Color;
import java.util.Collection;
import com.google.android.gms.plus.Plus;
=======
>>>>>>> 13f2942ca6980fdcbeb5ef3b3089109549747525

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;

import mse.hqevaluator.asynctasks.GetAllMotorwayRampsTask;
import mse.hqevaluator.asynctasks.OnAllMotorwayRampsReceivedListener;
import mse.hqevaluator.asynctasks.OnAllNuclearPowerPlantsReceivedListener;
import mse.hqevaluator.asynctasks.AsyncTaskResult;
import mse.hqevaluator.asynctasks.AsyncTaskResultStatus;
import mse.hqevaluator.asynctasks.GetAllNuclearPowerPlantsTask;

public class MapsActivity extends ActionBarActivity
    implements OnAllNuclearPowerPlantsReceivedListener, OnAllMotorwayRampsReceivedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private GoogleApiClient googleApiClient;

    private Location location;

    private HeatmapTileProvider mProvider;

    private TileOverlay mOverlay;

    private final LatLng ZURICH = new LatLng(47.377911, 8.524798);

    private final WeightedLatLng ZU = new WeightedLatLng(ZURICH, 1);

    private static final int[] colors = {
            Color.rgb(102, 225, 0),
            Color.rgb(255, 0, 0)
    };

    private static final float[] startPoints = {
            0.2f, 2f
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        buildGoogleApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        googleApiClient.connect();

    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
<<<<<<< HEAD
         new GetAllNuclearPowerPlantsTask(this).execute();
    }

    @Override
    public void processFinish(Object output) {
        List<NuclearPowerPlant> list = (List<NuclearPowerPlant>)output;
        Iterator<NuclearPowerPlant> iterator = list.iterator();

        while(iterator.hasNext()){
            NuclearPowerPlant plant = iterator.next();
            mMap.addMarker(
                new MarkerOptions()
                    .position(new LatLng(plant.Latitude, plant.Longitude))
                    .title(plant.Name + "\nLatitude: " + plant.Latitude + "\nLongitude: " + plant.Longitude));
        }
        addHeatMap();
=======
        new GetAllNuclearPowerPlantsTask(this).execute();
        new GetAllMotorwayRampsTask(this).execute();
>>>>>>> 13f2942ca6980fdcbeb5ef3b3089109549747525
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        //setlocationtocurent();

       if (location != null) {
            // Display toast for debugging purposes
            Helpers.showToast("Lat: " + location.getLatitude() + "\nLng: " + location.getLongitude(), getApplicationContext());

            LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
            float zoom = 8.0f;    // valid values between 2.0 and 21.0
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, zoom));
        }
        else {
            // Display toast for debugging purposes
            Helpers.showToast("Location was empty", getApplicationContext());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Helpers.showToast("Disconnected", getApplicationContext());
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Helpers.showToast("Connection failed", getApplicationContext());
    }

    private void addHeatMap() {

        ArrayList<WeightedLatLng> list = new ArrayList<WeightedLatLng>();
        list.add(ZU);
        LatLng nlocationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        WeightedLatLng locationLatLng = new WeightedLatLng(nlocationLatLng,1);

        list.add(locationLatLng);


        Gradient gradient = new Gradient(colors, startPoints);

<<<<<<< HEAD
        // Create a heat map tile provider, passing it the latlngs of the police stations.
        mProvider = new HeatmapTileProvider.Builder()
                .weightedData(list)
                .gradient(gradient)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));

        mProvider.setOpacity(1);

=======
    @Override
    public void onAllNuclearPowerPlantsReceived(AsyncTaskResult<List<NuclearPowerPlant>> result) {
        List<NuclearPowerPlant> list = result.getResult();
        Exception e = result.getException();
        AsyncTaskResultStatus status = result.getStatus();

        if (status.equals(AsyncTaskResultStatus.SUCCESS)) {
            // Everything was fine.
            Iterator<NuclearPowerPlant> iterator = list.iterator();

            while(iterator.hasNext()){
                NuclearPowerPlant plant = iterator.next();
                mMap.addMarker(
                    new MarkerOptions()
                        .position(new LatLng(plant.Latitude, plant.Longitude))
                        .title(plant.Name + "\nLatitude: " + plant.Latitude + "\nLongitude: " + plant.Longitude));
            }
        } else {
            // There was an error. We should display an error to the user.
            // TODO: Display error message
        }
    }

    @Override
    public void onAllMotorwayRampsReceived(AsyncTaskResult<List<MotorwayRamp>> result) {
        List<MotorwayRamp> list = result.getResult();
        Exception e = result.getException();
        AsyncTaskResultStatus status = result.getStatus();

        if (status.equals(AsyncTaskResultStatus.SUCCESS)) {
            // Everything was fine.
            Iterator<MotorwayRamp> iterator = list.iterator();

            while(iterator.hasNext()){
                MotorwayRamp ramp = iterator.next();
                mMap.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(ramp.Latitude, ramp.Longitude))
                                .title(ramp.Name + "\nLatitude: " + ramp.Latitude + "\nLongitude: " + ramp.Longitude)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        } else {
            // There was an error. We should display an error to the user.
            // TODO: Display error message
        }
>>>>>>> 13f2942ca6980fdcbeb5ef3b3089109549747525
    }
}