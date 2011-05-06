package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * This is the home page of the application.
 * It displays an image and a menu of all options in large screens.
 * And simply a menu in smaller screens.
 *
 * @author Conor Gallagher
 * Date: 18/02/11
 * Time: 22:17
 */
public class HomePage extends Activity implements AdapterView.OnItemClickListener {

    /**
     * Utility Class To Handle Alerts
     */
    Alerts alerts;


    /**
     * Setup the homepage with a list of all options taken from resources
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        alerts = new Alerts();

        String[] homepage_menu = getResources().getStringArray(R.array.homepage_menu);

        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,homepage_menu));
        lv.setOnItemClickListener(this);

    }


    /**
     * Handle individual menu item clicks.
     * Launches correct activities based on positions
     *
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


        switch (position) {
            // Launch Promotional Video in Youtube if present, otherwise use HTML5 embeded video
            case 0:
                launchPromotionalVideoInAppropriateFormat();
                break;
             // Launch Weather Forecasting
             case 1: startActivityForResult(new Intent(this, WeatherTabs.class), 99);
                break;
             // Launch Restaurants Section
             case 2: startActivityForResult(new Intent(this, CustomizedTabbedView.class).putExtra("activity","restaurants"), 99);
                 break;
             // Launch Leisure Centre Section
             case 3: startActivityForResult(new Intent(this, CustomizedTabbedView.class).putExtra("activity","leisure"), 99);
                 break;
             // Launch Theatre Listings
             case 4: startActivityForResult(new Intent(this, Browser.class).putExtra("address", "http://www.greystonestheatre.com/listings/listings.html"), 99);
                 break;
             // Launch Dart Website
             case 5: startActivityForResult(new Intent(this, Browser.class).putExtra("address", "http://www.irishrail.ie/home/"), 99);
                 break;
             //Launch Dublin Bus Timetables
             case 6: startActivityForResult(new Intent(this, Browser.class).putExtra("address", "http://www.dublinbus.ie/en/Your-Journey1/Timetables/All-Timetables/84-/"), 99);
                 break;

        }
    }


    /**
     * Launch video in youtube if the applicaiton is present, otherwise launch browser
     * and view embeded clip from HTML assets file
     */
    private void launchPromotionalVideoInAppropriateFormat() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:Sb9OvofxTzM"));
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);

        // If youtube is not present switch intent to promotional video in browser.
        if (list.size() == 0) {
            i = new Intent(this, PromotionalVideo.class);
        }
        startActivityForResult(i, 99);
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
        inflater.inflate(R.menu.home_menu, menu);
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