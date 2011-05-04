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

        // Create an Intent to launch an Activity for the tab (to be reused)
        Intent intent = new Intent().setClass(this, WeatherForecast.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("today").setIndicator("Today",
                          res.getDrawable(R.drawable.weather_tabs))
                      .setContent(intent.putExtra("forecast","http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour.html"));
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, WeatherForecast.class);
        spec = tabHost.newTabSpec("tomorrow").setIndicator("Tomorrow",
                          res.getDrawable(R.drawable.weather_tabs))
                      .setContent(intent.putExtra("forecast","http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour_tomorrow.html"));
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, WeatherForecast.class);
        spec = tabHost.newTabSpec("long").setIndicator("Long Term Outlook",
                          res.getDrawable(R.drawable.weather_tabs))
                .setContent(intent.putExtra("forecast","http://m.yr.no/place/Ireland/Leinster/Greystones/long.html"));
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

    }
}