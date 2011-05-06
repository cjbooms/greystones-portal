package masters.android.greystones;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
     * The map overlay containing restaurant locations
     */
    private RestaurantMapOverlay restaurantsOverlay;

    /**
     *
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
    }

    private void createAndPopulateRestuarantsOverlayForMap() {

       Drawable drawable = this.getResources().getDrawable(R.drawable.drink);

       restaurantsOverlay = new RestaurantMapOverlay(drawable, this);

       populateOverlayFromResources();
    }

    private void populateOverlayFromResources() {
        String[] restaurantNames = getResources().getStringArray(R.array.restaurant_names);
        String[] restaurantDescriptions = getResources().getStringArray(R.array.restaurant_websites);
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



}