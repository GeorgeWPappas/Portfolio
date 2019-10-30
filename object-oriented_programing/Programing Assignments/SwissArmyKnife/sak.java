/*******************************************************************************
 * @author George Pappas
 * 
 * Assignment: Programming Assignment (PA) 5
 * 
 * This application is using HttpRequest.java by Instrustor Eric Pogue.
 ******************************************************************************/

public class sak {
    public static void main(String[] args) {
        // Variable Declarations
        long time = System.currentTimeMillis(); // Stores current system time.
        long timeElapsed;

        System.out.print("\n"); // Adds new line.
        if (args.length < 1) {
            System.out.println("This application requires at least one argument. Utilize the \"-Help\" parameter for more information.");
            Help.printHelp();
        }
        else if (args[0].equalsIgnoreCase("-Help")) {
            System.out.println("Executing Help...");
            System.out.println();
            System.out.println("You have entered the Help menu. Below are examples of valid command line inputs.");
            Help.printHelp();
        }
        else if (args[0].equalsIgnoreCase("-HttpRequest")) {
            System.out.println("Directing to HttpRequest...");
            if (args.length <2) {
                System.out.println("The -HttpRequest function requires a valid URL as the second parameter.");
            } else {
                String URL = args[1];
                HttpRequest request = new HttpRequest();
                if (request.readURL(URL)) {
                    System.out.println(request);
                }       
            }
        }
        else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
             System.out.println("Directing to HttpRequestIndex...");
            if (args.length != 2) {
                System.out.println("Please enter a vaild URL");
            } else {
                String indexURL = args[1];
                HttpRequestURLs request = new HttpRequestURLs();
                if (request.readURL(indexURL)) {
                    System.out.println(request);
                }
            }
        }
        timeElapsed = (System.currentTimeMillis() - time); // Calculates elapsed time.
        System.out.println(); // Adds new line.
        System.out.format("Time Elapsed: %d milliseconds", timeElapsed); // Displays elapsed time.
        System.out.println(); // Adds new line.
    }
}