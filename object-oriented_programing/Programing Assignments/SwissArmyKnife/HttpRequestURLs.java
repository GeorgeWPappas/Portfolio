// Variable Declarations
import java.util.ArrayList;

class HttpRequestURLs extends HttpRequest {
    // Variable Declarations
    ArrayList<HttpRequest> list;

    HttpRequestURLs() {
        super();
        list = new ArrayList<HttpRequest>();
    }

    public Boolean readURL(String urlIn) {
        Boolean TrueOrFalse = false; // Sets TrueOrFalse as false by default.
        list = new ArrayList<HttpRequest>();

        if (super.readURL(urlIn)) {
            for (String s : urlContent) {
                String[] subS = s.split("\"");
                if (subS.length >= 13) {
                    if (subS[11].indexOf("https://") >= 0) { // Checks to see if the 11th spot in the line is a "http" or "https" link.
                        list.add(new HttpRequest(subS[11])); // Adds link to the ArrayList.
                        TrueOrFalse = true; // Sets TrueOrFalse to true so the content cant be initialized in the next step.
                    }
                }
            }
        }

        if (TrueOrFalse) { // Will initialize content in the ArrayList.
            for (HttpRequest s : list) {
                s.readURL(s.requestURL);
            } 
        }
        return TrueOrFalse;
    }

    public String toString() { // Implements toString
        String returnStatement = "";
        for (HttpRequest d : list) {
            returnStatement = returnStatement + "\n" + d;
        }
        return returnStatement;
    }
}
