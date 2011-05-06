package masters.android.greystones;

import android.app.Activity;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * Generates the weather forecast for Greystones. Uses the mobile site of
 * yr.no and parses and transforms the html into a phone friendly display
 *
 * @author Conor Gallagher
 * Date: 20/02/11
 * Time: 21:02
 */
public class WeatherForecast extends Activity {

    /**
     * The url of the raw weather resource
     */
    String url;

    /**
     * The HTML Parser and transformer used to filter weather content
     */
    WeatherParser weatherParser;


    /**
     * Create Weather Web View and populate with cleaned weather data
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url =" ";

        // Create HTML Parser, used to filter content
        weatherParser = new WeatherParser();

        // Create Webview
		WebView browser=new WebView(this);
		setContentView(browser);

        // Get url from extras
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			url = extras.getString("forecast");
		}

        // Parse and transform HTML from weather resource
        String tabularData = weatherParser.ParseHtml(url);

        //Display cleaned weather results in webview
        browser.loadData(tabularData, "text/html", "utf-8");


    }
}