package masters.android.greystones;

import android.app.Activity;

import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 20/02/11
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class WeatherForecast extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url =" ";
        // Create HTML Parser, used to filter content
        ParseHtml parseHtml = new ParseHtml();

		WebView browser=new WebView(this);
		setContentView(browser);
		//browser.loadUrl("http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour.html");

        // Get Fruit Array Position and set fruit variables
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			url = extras.getString("forecast");
		}


        //String tabularData = parseHtml.ParseHtml("http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour.html");
        String tabularData = parseHtml.ParseHtml(url);
        browser.loadData(tabularData, "text/html", "utf-8");



        //browser.loadData();

  /*     setContentView(R.layout.weatherforecast);
        WebView webview;
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour.html");
        */
    }
}