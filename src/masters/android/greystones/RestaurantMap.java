package masters.android.greystones;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.maps.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 03/05/11
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class RestaurantMap extends MapActivity {

    /**
     * The map containing restaurant locations
     */
     private MapView mapView;

     /**
     * Utility Class To Handle Alerts
     */
    Alerts alerts;

    /**
     * The map overlay containing restaurant locations
     */
    private RestaurantMapOverlay restaurantsOverlay;


    /**
     * Create Map View
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.restaurant_map);

        createAndPopulateMap();

    }

    /**
     * Create the map and populate with local restaurant locations
     */
    private void createAndPopulateMap(){
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        List<Overlay> mapOverlays = mapView.getOverlays();

        createAndPopulateRestuarantsOverlayForMap();

        mapOverlays.add(restaurantsOverlay);

        mapView.getController().setCenter(new GeoPoint(53144441,-6062130));
        mapView.getController().setZoom(20);
        mapView.setSatellite(false);

    }


    /**
     * Create a map overlay and populate with restaurant details
     */
    private void createAndPopulateRestuarantsOverlayForMap() {

       Drawable drawable = this.getResources().getDrawable(R.drawable.drink);
       Drawable selected = this.getResources().getDrawable(R.drawable.drinkgreen);

       restaurantsOverlay = new RestaurantMapOverlay(drawable, this, selected);

       populateOverlayFromResources();
    }


    /**
     * Populate the map overlay with restaurant details from xml resource files
     */
    private void populateOverlayFromResources() {

        // Get restaurant descriptions from resources
        String[] restaurantNames = getResources().getStringArray(R.array.restaurant_names);
        String[] restaurantDescriptions = getResources().getStringArray(R.array.restaurant_numbers);

        // Get Geo coordinates from resources
        int[] xCoordinates= getResources().getIntArray(R.array.XCoordinates);
        int[] yCoordinates= getResources().getIntArray(R.array.YCoordinates);

        for (int key = 0; key < restaurantNames.length; key++) {

            GeoPoint pointOnMap = new GeoPoint(xCoordinates[key], yCoordinates[key]);
            OverlayItem singleRestaurant = new OverlayItem(pointOnMap, restaurantNames[key], restaurantDescriptions[key]);
            restaurantsOverlay.addOverlay(singleRestaurant);

        }
    }


    /**
     * Method to control display of route on map
     *
     * @return false to indicate no route is displayed
     */
    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

   /**
     * Handle Android Menu button push
     *
     * @param menu
     * @return result
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);

        return true;
    }

 @Override

public boolean onPrepareOptionsMenu(Menu menu) {

    menu.findItem(R.id.map).setVisible(mapView.isSatellite());
    menu.findItem(R.id.satellite).setVisible(!mapView.isSatellite());

    return true;

}
    /**
     * Handle Android Menu button selection events
     *
     * @param item The button pushed
     * @return result
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.exit:
                setResult(99);
                this.finish();
                return true;
            case R.id.home:
                startActivityForResult(new Intent(this, HomePage.class), 99);
                return true;
            case R.id.satellite:
                 mapView.setSatellite(true);
                 return true;
            case R.id.map:
                 mapView.setSatellite(false);
                 return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}