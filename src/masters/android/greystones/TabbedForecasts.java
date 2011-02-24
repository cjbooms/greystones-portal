package masters.android.greystones;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 23/02/11
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class TabbedForecasts extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_tabs);

       Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, WeatherForecast.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("today").setIndicator("Today",
                          res.getDrawable(R.drawable.weather_tabs))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, WeatherForecast.class);
        spec = tabHost.newTabSpec("tomorrow").setIndicator("Tomorrow",
                          res.getDrawable(R.drawable.weather_tabs))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, WeatherForecast.class);
        spec = tabHost.newTabSpec("long").setIndicator("Long",
                          res.getDrawable(R.drawable.weather_tabs))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(2);

    }
}