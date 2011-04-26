package masters.android.greystones;

import com.sun.org.apache.xml.internal.serialize.HTMLSerializer;
import org.htmlcleaner.*;

import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by IntelliJ IDEA.
 * User: conor
 * Date: 21/02/11
 * Time: 23:52
 * To change this template use File | Settings | File Templates.
 */
public class ParseHtml {

    HtmlCleaner cleaner;
    CleanerProperties props;


    public ParseHtml() {
        // create an instance of HtmlCleaner
        cleaner = new HtmlCleaner();

        // take default cleaner properties
        props = cleaner.getProperties();
    }



// customize cleaner's behaviour with property setters
//props.setXXX(...);

// Clean HTML taken from simple string, file, URL, input stream,
// input source or reader. Result is root node of created
// tree-like structure. Single cleaner instance may be safely used
// multiple times.
    public String ParseHtml(String forecast){

        // Create Weather URL from passed forecast type. i.e today's / tomorrow's etc
       // String webPage = "http://m.yr.no/place/Ireland/Wicklow/Greystones/hour_by_hour.html" + forecast + ".html";
        String returnValue = " ";
        try {
            URL url = new URL(forecast);
            TagNode node = cleaner.clean(url);
            Object[] all_nodes = node.evaluateXPath("//table");

            if (all_nodes.length > 0){
                TagNode weather_table_node = (TagNode) all_nodes[0];

                returnValue = new SimpleHtmlSerializer(props).getAsString(weather_table_node);

            }
            else{
               returnValue = "Forecasting for today is over";
            }

            return returnValue;


        }
        catch (MalformedURLException e) {}
        catch (IOException e){}
        catch (XPatherException e){}

        return returnValue;
}

/*

// optionally find parts of the DOM or modify some nodes
TagNode[] myNodes = node.getElementsByXXX(...);
// and/or
Object[] myNodes = node.evaluateXPath(xPathExpression);
// and/or
aNode.removeFromTree();
// and/or
aNode.addAttribute(attName, attValue);
// and/or
aNode.removeAttribute(attName, attValue);
// and/or
cleaner.setInnerHtml(aNode, htmlContent);
// and/or do some other tree manipulation/traversal

// serialize a node to a file, output stream, DOM, JDom...
new XXXSerializer(props).writeXmlXXX(aNode, ...);
myJDom = new JDomSerializer(props, true).createJDom(aNode);
myDom = new DomSerializer(props, true).createDOM(aNode);

*/

}
