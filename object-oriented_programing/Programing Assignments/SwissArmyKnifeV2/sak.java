/*******************************************************************************
 * @author George Pappas
 * 
 * Assignment: Programming Assignment (PA) 6
 * 
 * This application is using HttpRequest.java by Instrustor Eric Pogue.
 ******************************************************************************/

// Variable Declarations.
import java.util.ArrayList;

public class sak {
    public static void main(String[] args) {
        // Variable Declarations.
        long time = System.currentTimeMillis(); // Stores current system time.
        long timeElapsed;

        System.out.print("\n"); // Adds new line.
        if (args.length < 1) {
            System.out.println("This application requires at least one argument. Utilize the \"-Help\" parameter for more information.");
            Help.printHelp();

        } else if (args[0].equalsIgnoreCase("-Help")) {
            System.out.println("Executing Help...");
            System.out.println();
            System.out.println("You have entered the Help menu. Below are examples of valid command line inputs.");
            Help.printHelp();

        } else if (args[0].equalsIgnoreCase("-HttpRequest")) {
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
        } else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
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
        } else if (args[0].equalsIgnoreCase("-Sleep")) {
            System.out.println("Executing Sleep...");

            sleep sleeper0 = new sleep(0);
            sleep sleeper1 = new sleep(1);

            System.out.println("\nNon-threaded Sleep");
            long start = System.currentTimeMillis();
            sleeper0.executeSleep();
            sleeper1.executeSleep();
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");

        } else if (args[0].equalsIgnoreCase("-SleepFast")) {
            System.out.println("Executing SleepFast...");

            SleepFast sleeper0 = new SleepFast(0);
            SleepFast sleeper1 = new SleepFast(1);

            System.out.println("\nNon-threaded Sleep");
            long start = System.currentTimeMillis();
            sleeper0.sleep();
            sleeper1.sleep();
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");

            System.out.println("\nThreaded Sleep");
            start = System.currentTimeMillis();
            sleeper0.start();
            sleeper1.start();

            try {
                sleeper0.join();
                sleeper1.join();
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");

            // Sleeping ArrayList
            ArrayList<SleepFast> sleeperList = new ArrayList<SleepFast>();
            for (int i=2; i<12; i++) {
                sleeperList.add(new SleepFast(i));
            }

            System.out.println("\nNon-threaded ArrayList sleep:");
            start = System.currentTimeMillis();
            for (SleepFast s: sleeperList) {
                s.sleep();
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));

            System.out.println("\nThreaded ArrayList sleep:");
            start = System.currentTimeMillis();
            for (SleepFast s: sleeperList) {
                s.start();
            }

            try {
                for (SleepFast s: sleeperList) {
                    s.join();
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            System.out.println("Elapsed time = "+(System.currentTimeMillis()-start));

        } else if (args[0].equalsIgnoreCase("-SleepFastImplementsRunnable")) {
            System.out.println("Executing SleepFastImplementsRunnable...");

            SleepFastImplementsRunnable sleeper0 = new SleepFastImplementsRunnable(0);
            SleepFastImplementsRunnable sleeper1 = new SleepFastImplementsRunnable(1);
    
            System.out.println("\nNon-threaded Sleep");
            long start = System.currentTimeMillis();
            sleeper0.sleep();
            sleeper1.sleep();
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");
    
            System.out.println("\nThreaded Sleep");
            start = System.currentTimeMillis();
    
            Thread t0 = new Thread(sleeper0);
            Thread t1 = new Thread(sleeper1);
    
            t0.start();
            t1.start();
    
            try {
                t0.join();
                t1.join();
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start) + "\n");
    
            // Sleeping ArrayList
            ArrayList<SleepFastImplementsRunnable> sleeperList = new ArrayList<SleepFastImplementsRunnable>();
            for (int i=2; i<12; i++) {
                sleeperList.add(new SleepFastImplementsRunnable(i));
            }
    
            System.out.println("\nNon-threaded ArrayList sleep:");
            start = System.currentTimeMillis();
            for (SleepFastImplementsRunnable s: sleeperList) {
                s.sleep();
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis() - start));
    
            System.out.println("\nThreaded ArrayList sleep:");
            ArrayList<Thread> threadList = new ArrayList<Thread>();
            for (SleepFastImplementsRunnable s: sleeperList) {
                threadList.add(new Thread(s));
            }
    
            start = System.currentTimeMillis();
            for (Thread t: threadList) {
                t.start();
            }
    
            try {
                for (Thread t: threadList) {
                    t.join();
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            System.out.println("Elapsed time = " + (System.currentTimeMillis()-start));

        } else if (args[0].equalsIgnoreCase("-JSONValidateIndex")) {
            System.out.println("Executing JSONValidateIndex...");
            String indexURL = "https://thunderbird-data.azurewebsites.net/url-list.json"; // Variable Declarations.
            
            JsonValidateIndexRequestURLs jsonRequest = new JsonValidateIndexRequestURLs();
            if (jsonRequest.readURL(indexURL)) {
                System.out.println("JSON files to extract information from:"); // Change discrption
                jsonRequest.CharacterInformation();
                jsonRequest.ValidateCharacterInformation();
            }
        
        } else if (args[0].equalsIgnoreCase("-JSONValidateIndexThreaded")) {
            System.out.println("Executing JSONValidateIndexThreaded...");
            // Variable Declarations.
            long start = System.currentTimeMillis();
            String indexURL = "https://thunderbird-data.azurewebsites.net/url-list.json";
            JsonValidateIndexRequestURLs jsonRequest = new JsonValidateIndexRequestURLs();

            // Validate ArrayList
            ArrayList<JSONValidateIndexThreaded> validateList = new ArrayList<JSONValidateIndexThreaded>();
            for (int i=2; i<12; i++) {
                validateList.add(new JSONValidateIndexThreaded(i));
            }
    
            System.out.println("\nNon-threaded ArrayList validation:");
            start = System.currentTimeMillis();
            
            if (jsonRequest.readURL(indexURL)) {
                System.out.println("JSON file links:");
                jsonRequest.CharacterInformation();
                jsonRequest.ValidateCharacterInformation();
            }
            System.out.println("Elapsed time for non-threaded ArrayList validation = " + (System.currentTimeMillis() - start));
        

            System.out.println("\nThreaded ArrayList validation:");
            ArrayList<Thread> threadList = new ArrayList<Thread>();
            for (JSONValidateIndexThreaded s: validateList) {
                threadList.add(new Thread(s));
            }
    
            start = System.currentTimeMillis();
            System.out.println("JSON file links:");
            System.out.println();
            for (Thread t: threadList) {
                t.start();
            }
    
            try {
                for (Thread t: threadList) {
                    t.join();
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            System.out.println("Elapsed time for threaded ArrayList validation = " + (System.currentTimeMillis()-start));

        }

        timeElapsed = (System.currentTimeMillis() - time); // Calculates elapsed time.
        System.out.println(); // Adds new line.
        System.out.format("Total Time Elapsed: %d milliseconds", timeElapsed); // Displays elapsed time.
        System.out.println(); // Adds new line.
    }
}
