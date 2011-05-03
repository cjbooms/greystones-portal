package masters.android.greystones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        String[] homepage_menu = getResources().getStringArray(R.array.homepage_menu);

        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,homepage_menu));
//        lv.setOnClickListener(this);
        lv.setOnItemClickListener(this);

    }

    public void onClick(View view) {
	   	 Intent forecasts = new Intent(this,TabbedForecasts.class);

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
                startActivity(i);
                break;
             case 1: startActivity(new Intent(this,Restaurants.class));
                break;
             case 2: startActivity(new Intent(this,TabbedForecasts.class));
                 break;
             case 3: startActivity(new Intent(this,TabbedForecasts.class));
                 break;
             case 4: startActivity(new Intent(this,InteractiveMap.class));
                 break;
         }
    }



}