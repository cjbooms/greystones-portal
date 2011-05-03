package masters.android.greystones;

import android.app.ListActivity;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 03/05/11
 * Time: 02:16
 * To change this template use File | Settings | File Templates.
 */
public class Restaurants extends ListActivity {

		  // Create Restaurants Array
		  private String[] restaurantNames;
		  // Create Restaurants Array
		  private String[] restaurantEmail;
		  // Create Restaurants Array
		  private String[] restaurantPhone;
		  // Create Restaurants Array
		  private String[] restaurantWebsite;

		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);


		    setContentView(R.layout.restaurants);
            fillData();
          }

    private void fillData() {
        String[] matrixCols = new String[] {"_id", "name", "website", "phone" };
        String[] menuCols = new String[] {"name", "website", "phone" };
        int[] to = new int[] { R.id.contact_name, R.id.contact_website, R.id.contact_number };

        MatrixCursor menuCursor = new MatrixCursor(matrixCols);
        startManagingCursor(menuCursor);

        menuCursor = addRowsFromXMLStrings(menuCursor);

        SimpleCursorAdapter menuItems = new SimpleCursorAdapter(
                    this, R.layout.restaurant_row, menuCursor, menuCols, to);

        setListAdapter(menuItems);
    }

    private MatrixCursor addRowsFromXMLStrings(MatrixCursor cursor) {
	    restaurantNames = getResources().getStringArray(R.array.restaurant_names);
        restaurantPhone = getResources().getStringArray(R.array.restaurant_numbers);
        restaurantWebsite = getResources().getStringArray(R.array.restaurant_websites);
        for (int key = 0; key < restaurantNames.length; key ++ ){

                    cursor.addRow(new Object[] { key, restaurantNames[key], restaurantWebsite[key], restaurantPhone[key]});
        }
        return cursor;
    }


}