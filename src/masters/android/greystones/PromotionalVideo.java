package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Simple class to launch the promotional video in a web browser
 * @author Conor Gallagher
 * Date: 14/04/11
 * Time: 23:33
 */
public class PromotionalVideo extends Activity {

	WebView browser;
    Alerts alerts;

    /**
     * Launch promo video in browser with plugins enabled
     *
     * @param savedInstanceState
     */
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
                alerts.generateInfoAlert(this,"default");
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


