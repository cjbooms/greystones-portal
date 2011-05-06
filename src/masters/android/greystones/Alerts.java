package masters.android.greystones;

import android.app.AlertDialog;
import android.content.Context;
import com.google.android.maps.OverlayItem;

/**
 * Utility Class to Handle Printing of Common Alert Messages.
 * @author Conor Gallagher
 * Date: 05/05/11
 * Time: 21:59
 */
public class Alerts {

    /**
     * Default Constructor
     */
    public Alerts() {
    }

    /**
     * Print basic application information
     *
     * @param ctx The context to display message
     * @return true when finished
     */
    protected boolean applicationInfoAlert(Context ctx) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Greystones Portal - Version 1.0");
        dialog.setMessage("by Conor Gallagher");
        dialog.show();
        return true;
    }

    /**
     * Print details about the map location item
     *
     * @param item A map location item
     * @param ctx The context to display message
     * @return true when finished
     */
    protected boolean restaurantInfoAlert(Context ctx, OverlayItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }}
