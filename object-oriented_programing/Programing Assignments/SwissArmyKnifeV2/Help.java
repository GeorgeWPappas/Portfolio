class Help {
    public static void printHelp() {
        System.out.println(); // Adds new line.
        System.out.println("This application finds a specific JSON file from the internet. It then takes the content in the JSON file and parses it out; only keeping keeping wanted information. ");
        System.out.print("It then displays the information on the console.");
        System.out.println(); // Adds new line.
        System.out.println(); // Adds new line.
        System.out.println("HttpRequest [URL] examples:");
        System.out.println("java sak -HttpRequest https://thunderbird-data.azurewebsites.net/");
        System.out.println("java sak -HttpRequest https://thunderbird-data.azurewebsites.net/ldw6txsjg5.json");
        System.out.println(); // Adds new line.
        System.out.println("HttpRequestIndex [URL] example:");
        System.out.println("java sak -HttpRequestIndex https://thunderbird-data.azurewebsites.net/url-list.json");
        System.out.println(); // Adds new line.
        System.out.println("Sleep examples using non-Threaded & threaded implementation:");
        System.out.println("java sak -Sleep");
        System.out.println("java sak -SleepFast");
        System.out.println("java sak -SleepFastImplementsRunnable");
        System.out.println(); // Adds new line.
        System.out.println("Reads JSON index and validates personal information using non-threaded implementation. Example:");
        System.out.println("java sak -JSONValidateIndex");
        System.out.println(); // Adds new line.
        System.out.println("Reads JSON index and validates personal information using threaded implementation. Example:");
        System.out.println("java sak -JSONValidateIndexThreaded");
    }
}
