package masters.android.greystones;

import com.sun.org.apache.xml.internal.serialize.HTMLSerializer;
import org.htmlcleaner.*;

import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Cleans and parses the HTML using a cleaner along with an XPATH expression
 *
 * @author Conor Gallagher
 * Date: 21/02/11
 * Time: 23:52
 */
public class WeatherParser {

    /**
     * The HTML cleaner object
     */
    HtmlCleaner cleaner;

    /**
     * Cleaner properties
     */
    CleanerProperties props;


    /**
     * Default constructor
     */
    public WeatherParser() {
        // create an instance of HtmlCleaner
        cleaner = new HtmlCleaner();

        // take default cleaner properties
        props = cleaner.getProperties();
    }


    /**
     * Parse HTML and run XPATH filtering and return cleaned formatted result in a string
     * or default error message
     *
     * @param forecast The url of the HTML page to clean
     * @return The cleaned weather data in String format
     */
    public String ParseHtml(String forecast){

        String returnValue = " ";

        try {
            URL url = new URL(forecast);
            TagNode node = cleaner.clean(url);

            // Run XPATH expression to filter HTML
            Object[] all_nodes = node.evaluateXPath("//table");

            //Check whether weather data is returned. Should be null in the last hour of every day.
            if (all_nodes.length > 0){

                // Select the weather tagnode
                TagNode weather_table_node = (TagNode) all_nodes[0];

                //Convert tagnode to String
                returnValue = new SimpleHtmlSerializer(props).getAsString(weather_table_node);

            }
            else{
               returnValue = "Forecasting for today is over";
            }

            return returnValue;

        }
        catch (MalformedURLException e) {
             e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (XPatherException e){
            e.printStackTrace();
        }

        return returnValue;
}


}
