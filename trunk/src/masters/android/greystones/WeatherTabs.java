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
 * Creates and populates 3 separate weather forecasting tabs.
 *
 * @author Conor Gallagher
 * Date: 23/02/11
 * Time: 23:29
 */
public class WeatherTabs extends TabActivity {


     /**
     * Utility Class To Handle Alerts
     */
    Alerts alerts;

    /**
     * Resources for weather tab icons
     */
    Resources res;

    /**
     * The activity TabHost
     */
    TabHost tabHost;

    /**
     * Reusable tab specifications
     */
    TabHost.TabSpec spec;



    /**
     * Create and populate weather tabs by passing raw weather urls to the Weather Forecasting Class
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_tabs);

        alerts = new Alerts();
        res = getResources();
        tabHost = getTabHost();


        // Setup all weather tabs
        setupWeatherTabs();

        // Default View to first tab
        tabHost.setCurrentTab(0);

    }


   /**
    * Initialize a TabSpec for each weather tab and add it to the TabHost
    */
    private void setupWeatherTabs() {

        // Create an Intent to launch an Activity for each tab (to be reused)
        Intent intent = new Intent().setClass(this, WeatherForecast.class);

       spec = tabHost.newTabSpec("today").setIndicator("Today",
                res.getDrawable(R.drawable.weather_tabs))
                .setContent(intent.putExtra("forecast","http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour.html"));
                tabHost.addTab(spec);

        //Tomorrows Weather
         intent = new Intent().setClass(this, WeatherForecast.class);
        spec = tabHost.newTabSpec("tomorrow").setIndicator("Tomorrow",
                res.getDrawable(R.drawable.weather_tabs))
                .setContent(intent.putExtra("forecast","http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour_tomorrow.html"));
                tabHost.addTab(spec);

        //Long Range Forecast
         intent = new Intent().setClass(this, WeatherForecast.class);
        spec = tabHost.newTabSpec("long").setIndicator("Long Term Outlook",
                res.getDrawable(R.drawable.weather_tabs))
                .setContent(intent.putExtra("forecast","http://m.yr.no/place/Ireland/Leinster/Greystones/long.html"));
                tabHost.addTab(spec);
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
        inflater.inflate(R.menu.general_menu, menu);
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
            case R.id.info:
                alerts.applicationInfoAlert(this);
                return true;
            case R.id.home:
                startActivityForResult(new Intent(this, HomePage.class), 99);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Cascade through all activities and close on exit command
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 99) {
            setResult(99);
            this.finish();
        }
    }
}