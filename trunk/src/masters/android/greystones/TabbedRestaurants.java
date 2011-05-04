package masters.android.greystones;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 23/02/11
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class TabbedRestaurants extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_tabs);

       Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab


        // Create an Intent to launch an Activity for the tab (to be reused)
        Intent intent = new Intent().setClass(this, Restaurants.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("today").setIndicator("Restaurant Details").setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, InteractiveMap.class);

        spec = tabHost.newTabSpec("tomorrow").setIndicator("Map Locations").setContent(intent);
        tabHost.addTab(spec);



        tabHost.setCurrentTab(0);

    }
}