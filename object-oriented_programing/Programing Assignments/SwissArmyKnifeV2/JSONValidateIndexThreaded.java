// Variable declarations.
import java.util.ArrayList;

class JSONValidateIndexThreaded extends HttpRequest implements Runnable {
    private int validateNumber;

    JSONValidateIndexThreaded(int validateNumberIn) {
        validateNumber = validateNumberIn;
    }

    public void run() {
        String indexURL = "https://thunderbird-data.azurewebsites.net/url-list.json";
            
        JsonValidateIndexRequestURLs jsonRequest = new JsonValidateIndexRequestURLs();
        if (jsonRequest.readURL(indexURL)) {
            jsonRequest.CharacterInformation();
            jsonRequest.ValidateCharacterInformation();
        }
    }
}
