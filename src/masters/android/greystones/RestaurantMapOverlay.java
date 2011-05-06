package masters.android.greystones;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;


/**
 * Populates a Map with a set of restaurant markers and displays an info when each is selected.
 *
 * @author Conor Gallagher
 * Date: 03/05/11
 * Time: 22:50
 */
public class RestaurantMapOverlay extends ItemizedOverlay {

    /**
     * List of restaurant markers
     */
    private ArrayList<OverlayItem> attractions = new ArrayList<OverlayItem>();

    /**
     * The context to apply overlay to
     */
    Context mContext;

    /**
     * Utility Class To Handle Alerts
     */
    Alerts alerts;

    /**
     * Default Constructor
     * @param defaultMarker
     */
    public RestaurantMapOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
        alerts = new Alerts();
    }

    /**
     * Add marker to collection
     *
     * @param overlay the marker to add
     */
    public void addOverlay(OverlayItem overlay) {
        attractions.add(overlay);
        populate();
    }


    @Override
    protected OverlayItem createItem(int i) {
        return attractions.get(i);
    }

    @Override
    public int size() {
        return attractions.size();
    }

    /**
     * Constructor with marker and context. Chained constructor
     * @param defaultMarker
     * @param context
     */
    public RestaurantMapOverlay(Drawable defaultMarker, Context context) {
        this(defaultMarker);
        mContext = context;
    }

    /**
     * Handles tap on markers and generates alert message
     *
     * @param index of marker tapped
     * @return
     */
    @Override
    protected boolean onTap(int index) {
        OverlayItem item = attractions.get(index);
        alerts.restaurantInfoAlert(mContext, item);
        return true;
    }

}
