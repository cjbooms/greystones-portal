package masters.android.greystones;

import android.app.Activity;
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
public class InteractiveMap extends MapActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.interactive_map);

        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.car);
        GreystonesAttractionsOverlay attractionsOverlay = new GreystonesAttractionsOverlay(drawable, this);

        GeoPoint point = new GeoPoint(19240000,-99120000);
        OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");

        attractionsOverlay.addOverlay(overlayitem);
        mapOverlays.add(attractionsOverlay);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }



}