import java.util.Scanner;

class roundrobin
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        String title[]={"Processes","BT","WT","TAT"};

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        String processes[] = new String[n];
        int BT[] = new int[n];
        int WT[] = new int[n];
        int TAT[] = new int[n];
        int RBT[] = new int[n];

        // Input process details
        for(int i = 0; i < n; i++) {
            System.out.print("Enter process name (e.g., p" + (i+1) + "): ");
            processes[i] = scanner.next();

            System.out.print("Enter burst time for " + processes[i] + ": ");
            BT[i] = scanner.nextInt();
            RBT[i] = BT[i];
        }

        System.out.print("Enter the time quantum: ");
        int TQ = scanner.nextInt();

        int t = 0;
        float total_BT = 0, total_WT = 0, total_TAT = 0;

        // Round Robin scheduling
        while(true) {
            boolean done = true;
            for(int i = 0; i < n; i++) {
                if(RBT[i] > 0) {
                    done = false;
                    if(RBT[i] > TQ) {
                        t += TQ;
                        RBT[i] -= TQ;
                    }
                    else {
                        t += RBT[i];
                        WT[i] = t - BT[i];
                        RBT[i] = 0;
                    }
                }
            }
            if(done) break;
        }

        // Calculate Turn Around Time
        for(int i = 0; i < n; i++) {
            TAT[i] = BT[i] + WT[i];
        }

        // Print table header
        for(int i = 0; i < 4; i++) {
            System.out.print(title[i] + "\t");
        }
        System.out.println("\n-------------------------------------");

        // Print process details and calculate totals
        for(int i = 0; i < n; i++) {
            System.out.println(processes[i] + "\t\t" + BT[i] + "\t" + WT[i] + "\t" + TAT[i]);
            total_BT += BT[i];
            total_WT += WT[i];
            total_TAT += TAT[i];
        }

        // Calculate averages
        float avg_BT = total_BT / n;
        float avg_WT = total_WT / n;
        float avg_TAT = total_TAT / n;

        // Print totals and averages
        System.out.println("\n");
        System.out.println("Total Burst Time = " + total_BT);
        System.out.println("Total Waiting Time = " + total_WT);
        System.out.println("Total Turn Around Time = " + total_TAT);
        System.out.println("Average Burst Time = " + avg_BT);
        System.out.println("Average Waiting Time = " + avg_WT);
        System.out.println("Average Turn Around Time = " + avg_TAT);

        scanner.close();
    }
}