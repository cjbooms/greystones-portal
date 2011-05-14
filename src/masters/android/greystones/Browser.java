package masters.android.greystones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Generic browser class to setup a zoomable webview for displaying web or image url's.
 *
 * @author Conor Gallagher
 * Date: 05/05/11
 * Time: 23:40
 */
public class Browser extends Activity {
    Alerts alerts;

    /**
     * Display a webview of the url passed in extras
     *
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alerts = new Alerts();
        String url = " ";

        WebView browser = new WebView(this);
        setContentView(browser);

        // Get url from extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("address");
        }

        // Make webview zoomable
        browser.getSettings().setBuiltInZoomControls(true);

        // Set Zoom level
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);

        // Load URL
        browser.loadUrl(url);
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