class JSONValidateIndex extends HttpRequest implements Runnable {
    // Variable Declarations.
    private String firstName;
    private String lastName;
    private String preferredName;

    public String getFirstName() { 
        return firstName; 
    }
    public String getLastName() {
        return lastName; 
    }
    public String getPreferredName() {
        return preferredName; 
    }


    JSONValidateIndex(String urlIn) {
        super(urlIn);

        firstName = ""; // Sets firstName to blank.
        lastName = ""; // Sets lastName to blank.
        preferredName = ""; // Sets preferredName to blank.
    }

    public Boolean TrueOrFalse() {
        Boolean returnValue = false;
        System.out.println(requestURL);
        if (super.readURL()) {
            Parse();
            returnValue = true;
        }
        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            if (subString.length > 2 && subString.length < 16) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                if (subString[1].equals("preferredName")) {
                    preferredName = subString[3];
                }
            }
        }    
    }

    public void Validate() {
        if (firstName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    Failed: First Name not found");
        } else if (lastName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    Failed: Last Name not found");
        } else if (preferredName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    Failed: Preferred Name not found");
        } else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        }
    }

    public void run() {
        TrueOrFalse();
    }
}
