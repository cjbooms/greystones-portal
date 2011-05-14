package masters.android.greystones;

import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;
import com.google.android.maps.OverlayItem;

/**
 * Utility Class to Handle Printing of Common Alert Messages.
 *
 * @author Conor Gallagher
 *         Date: 05/05/11
 *         Time: 21:59
 */
public class Alerts {

    /**
     * Default Constructor
     */
    public Alerts() {
    }


    /**
     * Print restaurant_page application information from resources
     *
     * @param ctx The context to display message
     * @return true when finished
     */
    protected boolean generateInfoAlert(Context ctx, String type) {
        final TextView message = new TextView(ctx);
        final SpannableString s;
        if (type.equalsIgnoreCase("home")){
            s = new SpannableString(ctx.getText(R.string.home_page));
        } else if (type.equalsIgnoreCase("weather")){
            s = new SpannableString(ctx.getText(R.string.weather_page));
        } else if (type.equalsIgnoreCase("restaurants")){
            s = new SpannableString(ctx.getText(R.string.restaurant_page));
        }  else{
            s = new SpannableString(ctx.getText(R.string.generic_page));
        }
        Linkify.addLinks(s, Linkify.WEB_URLS);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Greystones Portal V1.0 - by Conor Gallagher");
        dialog.setView(message);
        dialog.show();
        return true;
    }
    /**
     * Print details about the map location item
     *
     * @param item A map location item
     * @param ctx  The context to display message
     * @return true when finished
     */
    protected boolean restaurantInfoAlert(Context ctx, OverlayItem item) {
        final TextView message = new TextView(ctx);
        final SpannableString s = new SpannableString("Phone: " + item.getSnippet());
        Linkify.addLinks(s, Linkify.PHONE_NUMBERS);
        message.setText(s);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle(item.getTitle());
        dialog.setView(message);
        dialog.show();
        return true;
    }
}
