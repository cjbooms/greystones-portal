package masters.android.greystones;

import android.app.ListActivity;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 *Displays a s list of restaurant details and auto dials phone numbers / opens websites.
 *
 * @author Conor Gallagher
 * Date: 03/05/11
 * Time: 02:16
 */
public class RestaurantDetails extends ListActivity {

    /**
     * Restaurant Names
     */
    private String[] restaurantNames;

    /**
     * Restaurant Phone Numbers
     */
    private String[] restaurantPhone;

    /**
     * Restaurant Websites
     */
    private String[] restaurantWebsite;

    /**
     * Cursor to Hold RestaurantDetails
     */
    private  MatrixCursor restaurantsCursor;


    /**
     * Create Restaurant Details List
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        restaurantNames = getResources().getStringArray(R.array.restaurant_names);
        restaurantPhone = getResources().getStringArray(R.array.restaurant_numbers);
        restaurantWebsite = getResources().getStringArray(R.array.restaurant_websites);

        setContentView(R.layout.restaurant_tabs);

        createAndPopulateRestaurantsList();
    }


    /**
     * Create and populate list of restaurant_details
     */
    private void createAndPopulateRestaurantsList() {

        String[] visibleMatrixFields = new String[]{"name", "website", "phone"};
        int[] visibleFieldsMappedToResources = new int[]{R.id.contact_name, R.id.contact_website, R.id.contact_number};

        createAndPopulateCursorWithRestaurantDetails();

        SimpleCursorAdapter completeRestaurantListings = new SimpleCursorAdapter(
                this, R.layout.restaurant_row, restaurantsCursor, visibleMatrixFields, visibleFieldsMappedToResources);

        setListAdapter(completeRestaurantListings);
    }


    /**
     * Create and populate cursor with restaurant details
     */
    private void createAndPopulateCursorWithRestaurantDetails(){

        String[] columnsOfMatrix = new String[]{"_id", "name", "website", "phone"};

        restaurantsCursor = new MatrixCursor(columnsOfMatrix);
        startManagingCursor(restaurantsCursor);

        addRowsToMatrixFromXMLStrings();
    }


    /**
     * Populate cursor with restaurant rows derived from String resources
     */
    private void addRowsToMatrixFromXMLStrings() {

        for (int key = 0; key < restaurantNames.length; key++) {

            restaurantsCursor.addRow(new Object[]{key, restaurantNames[key], restaurantWebsite[key], restaurantPhone[key]});
        }
    }


}