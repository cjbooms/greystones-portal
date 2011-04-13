package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 18/02/11
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class WelcomePage extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String[] homepage_menu = getResources().getStringArray(R.array.homepage_menu);
     //   setListAdapter(new ArrayAdapter<String>(this, R.layout.main, homepage_menu));

        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,homepage_menu));
//        lv.setOnClickListener(this);
        lv.setOnItemClickListener(this);
/*        setContentView(R.layout.main);

        final Button weather = (Button) findViewById(R.id.weather);
        weather.setOnClickListener(this);

        final Button restaurants = (Button) findViewById(R.id.dining);
        restaurants.setOnClickListener(this);

        final Button directions = (Button) findViewById(R.id.directions);
        directions.setOnClickListener(this);
*/

    }

    public void onClick(View view) {
	   	 Intent forecasts = new Intent(this,TabbedForecasts.class);
       //  Intent forecasts = new Intent(this,WeatherForecast.class);


	 //  	 speak.putExtra("fruitName", fruitName);

	     startActivity(forecasts);
	}

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
Intent forecasts = new Intent(this,TabbedForecasts.class);
       //  Intent forecasts = new Intent(this,WeatherForecast.class);


	 //  	 speak.putExtra("fruitName", fruitName);

	     startActivity(forecasts);    }
}