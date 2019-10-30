class sleep {
    private int sleepNumber;
    
    sleep(int sleepNumberIn) {
        sleepNumber = sleepNumberIn;
    }

    public void executeSleep() {
        System.out.println(sleepNumber + " - Going to sleep");

        try {
            Thread.sleep(1000); // Sleep for one second. 
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        System.out.println("... " + sleepNumber + " - Done sleeping");
    }
}
