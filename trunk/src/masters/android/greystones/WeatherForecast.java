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
        setContentView(R.layout.weatherforecast);
        WebView webview;
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://m.yr.no/place/Ireland/Leinster/Greystones/hour_by_hour.html");
    }
}