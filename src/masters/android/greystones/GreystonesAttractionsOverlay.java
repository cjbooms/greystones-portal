package masters.android.greystones;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;


/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 03/05/11
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
public class GreystonesAttractionsOverlay extends ItemizedOverlay {

    private ArrayList<OverlayItem> attractions = new ArrayList<OverlayItem>();
    Context mContext;

    public GreystonesAttractionsOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }


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


    public GreystonesAttractionsOverlay(Drawable defaultMarker, Context context) {
        this(defaultMarker);
        mContext = context;
    }

    @Override
    protected boolean onTap(int index) {
        OverlayItem item = attractions.get(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();
        return true;
    }

}
