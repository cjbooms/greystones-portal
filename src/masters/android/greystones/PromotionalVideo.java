package masters.android.greystones;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 14/04/11
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class PromotionalVideo extends Activity {

	WebView browser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.promotionalvideo);
		browser=(WebView)findViewById(R.id.promovideo);

        browser.getSettings().setPluginsEnabled(true);

		// Enable Java Script
		browser.getSettings().setJavaScriptEnabled(true);


		browser.loadUrl("file:///android_asset/html/PromotionalVideo.html");


	}
}
