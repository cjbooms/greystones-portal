package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 18/02/11
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class WelcomePage extends Activity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button weather = (Button) findViewById(R.id.weather);
        weather.setOnClickListener(this);

        final Button restaurants = (Button) findViewById(R.id.dining);
        restaurants.setOnClickListener(this);

        final Button directions = (Button) findViewById(R.id.directions);
        directions.setOnClickListener(this);


    }

    public void onClick(View view) {
	   	 Intent forecasts = new Intent(this,TabbedForecasts.class);
       //  Intent forecasts = new Intent(this,WeatherForecast.class);


	 //  	 speak.putExtra("fruitName", fruitName);

	     startActivity(forecasts);
	}
}