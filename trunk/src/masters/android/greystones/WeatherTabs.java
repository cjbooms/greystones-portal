package masters.android.greystones;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 23/02/11
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class WeatherTabs extends TabActivity {

    Alerts alerts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_tabs);

        alerts = new Alerts();
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


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.general_menu, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
    case R.id.exit:
         setResult(99);
         this.finish();
        return true;
    case R.id.info:
         alerts.applicationInfoAlert(this);
        return true;
    case R.id.home:
         startActivityForResult(new Intent(this,HomePage.class), 99);
        return true;
    default:
        return super.onOptionsItemSelected(item);
    }
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 99) {
            setResult(99);
            this.finish();
        }
}

}