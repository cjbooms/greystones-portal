package masters.android.greystones;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Handles the setup of a customized tab view for both Restaurant Display and Leisure Centre Information.
 *
 * The actual display is decided based on the extras passed on launch.
 *
 * For Leisure Centre it displays two tabs containing information,
 * one displaying the webpage, the other displaying the pool timetables.
 *
 * For Restaurants it displays two tabs, one containing restaurant listings,
 * the other showing the restaurant locations on a map.
 *
 * @author Conor Gallagher
 *         <p/>
 *         Date: 05/05/11
 *         Time: 23:02
 */
public class CustomizedTabbedView extends TabActivity {

    /**
     * Utility Class To Handle Alerts
     */
    Alerts alerts;


    /**
     * Class to display the separate tabs
     */
    TabHost mTabHost;


    /**
     * Class to display the separate tabs
     */
    String activityToLaunch;

    /**
     * Create Leisure Center Tabs
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alerts = new Alerts();


        // Get Fruit Array Position and set fruit variables
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            activityToLaunch = extras.getString("activity");
        }
        if (activityToLaunch != null){

            if (activityToLaunch.equalsIgnoreCase("leisure")) {
                startLeisureCentreTabs();

            } else if (activityToLaunch.equalsIgnoreCase("restaurants")) {
                startRestaurantTabs();
            } else {
                this.finish();
            }
        } else {
            this.finish();
        }


    }


    /**
     * Populate a Tab View with Leisure Centre Information.
     * First tab to display Leisure Centre Webpage
     * Second tab to display swimming pool opening times
     */
    private void startLeisureCentreTabs() {
        setContentView(R.layout.leisure_centre_tabs);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

        Intent intent = new Intent().setClass(this, Browser.class);

        intent.putExtra("address", "http://www.shorelineleisure.ie/shorelinegreystones.0.html");
        setupTab(new TextView(this), "Website", intent);

        intent.putExtra("address", "file:///android_res/drawable/timetable.png");
        setupTab(new TextView(this), "Pool Times", intent);

        mTabHost.setCurrentTab(0);
    }

    /**
     * Populate a Tab View with Restaurant Information.
     * First tab displays a listing of Restaurants details
     * Second Tab displays their locations on a map
     */
    private void startRestaurantTabs() {

        setContentView(R.layout.restaurant_tabs);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

        // Create an Intent to launch an Activity for the tab (to be reused)
        Intent intent = new Intent().setClass(this, RestaurantDetails.class);

        setupTab(new TextView(this), "Restaurant Details", intent);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, RestaurantMap.class);
        setupTab(new TextView(this), "Map Locations", intent);

        mTabHost.setCurrentTab(0);

    }


    /**
     * Setup individual tabs
     *
     * @param view   the actual tab view
     * @param tag    the text to display in the tab
     * @param intent the activity to launch within the tab
     */
    private void setupTab(final View view, final String tag, Intent intent) {
        //Create the look and feel of the tabs
        View tabview = createTabView(mTabHost.getContext(), tag);

        TabHost.TabSpec tabContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(intent);
        mTabHost.addTab(tabContent);
    }

    /**
     * Create the custom look for the tabs from resource files
     *
     * @param context The current context
     * @param text    The text to display in the tab
     * @return
     */
    private static View createTabView(final Context context, final String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tabs, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
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