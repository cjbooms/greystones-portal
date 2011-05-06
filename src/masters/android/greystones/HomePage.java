package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.*;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 18/02/11
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class HomePage extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {


    Alerts alerts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        alerts = new Alerts();

        String[] homepage_menu = getResources().getStringArray(R.array.homepage_menu);

        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,homepage_menu));
//        lv.setOnClickListener(this);
        lv.setOnItemClickListener(this);

    }

    public void onClick(View view) {
	   	 Intent forecasts = new Intent(this,WeatherTabs.class);

	     startActivity(forecasts);
	}



    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


        switch (position) {
            // Launch Promotional Video in Youtube if present, otherwise use HTML5 embeded video
            case 0:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:Sb9OvofxTzM"));
                List<ResolveInfo> list = getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.size() == 0) {
                    i = new Intent(this,PromotionalVideo.class);
                }
                startActivityForResult(i, 99);
                break;
             case 1: startActivityForResult(new Intent(this, WeatherTabs.class), 99);
                break;
             case 2: startActivityForResult(new Intent(this, RestaurantTabs.class), 99);
                 break;
             case 3: startActivityForResult(new Intent(this, LeisureCentreTabs.class), 99);
                 break;
             case 4: startActivityForResult(new Intent(this, Browser.class).putExtra("address", "http://www.greystonestheatre.com/listings/listings.html"), 99);
                 break;
             case 5: startActivityForResult(new Intent(this, Browser.class).putExtra("address", "http://www.irishrail.ie/home/"), 99);
                 break;
             case 6: startActivityForResult(new Intent(this, Browser.class).putExtra("address", "http://www.dublinbus.ie/en/Your-Journey1/Timetables/All-Timetables/84-/"), 99);
                 break;

        }
    }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.home_menu, menu);
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