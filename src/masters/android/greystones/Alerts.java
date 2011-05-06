package masters.android.greystones;

import android.app.AlertDialog;
import android.content.Context;
import com.google.android.maps.OverlayItem;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 05/05/11
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class Alerts {

    public Alerts() {
    }

        protected boolean applicationInfoAlert(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Greystones Portal - Version 1.0");
        dialog.setMessage("by Conor Gallagher");
        dialog.show();
        return true;
    }

    protected boolean restaurantInfoAlert(Context ctx, OverlayItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }}
