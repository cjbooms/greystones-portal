package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    Alerts alerts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

    	setContentView(R.layout.promotional_video);

        alerts = new Alerts();

		browser=(WebView)findViewById(R.id.promovideo);

        browser.getSettings().setPluginsEnabled(true);

		// Enable Java Script
		browser.getSettings().setJavaScriptEnabled(true);


		browser.loadUrl("file:///android_asset/html/PromotionalVideo.html");


	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.general_menu, menu);
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
        case R.id.home:
             startActivityForResult(new Intent(this,HomePage.class), 99);
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
