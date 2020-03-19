package SjLottery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class SjLottery {

public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Random rand = new Random();
        
        
        int procamt = 10;
        char[] process = new char[procamt];
        
        for(int i = 0;i < procamt;i++){
            process[i] = (char) ('a'+i);
        }
        int[] arrival = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        
        int[] burst = new int [procamt];
        for(int i = 0;i < procamt;i++){
           System.out.print("Enter Burst Time for "+process[i]+" : ");
           int burstinp = Integer.parseInt(br.readLine());
           burst[i] = burstinp;
           System.out.println(burst[i]);
       }
        
        
        boolean insert = false;
        int p = 0;
        int q=0;
        int max_tick = 0;
        int ticket[][] = new int[10][10];
        char temp[] = new char[10];
        for (int z = 0; z < 10; z++) {
            System.out.print("Enter Arrival Time for"+process[z]+" : ");
            int digit = Integer.parseInt(br.readLine());
            arrival[z] = digit;
        }


        for (int i = 0; i < procamt; i++)
            for (int j = 0; j < procamt-1; j++) {
                if (arrival[j] >= arrival[j + 1]) {
                    int temp2 = arrival[j];
                    arrival[j] = arrival[j + 1];
                    arrival[j + 1] = temp2;

                    char temp1 = process[j];
                    process[j] = process[j + 1];
                    process[j + 1] = temp1;
                }
            }
        int time = arrival[0];

        
        int[] lottery = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int z = 0; z < 10; z++) {
            int digit = (int)rand.nextInt(20);

            if (digit == 0) {
                digit = 5;
            }

            //request[z] = digit;

        }
        for (int i = 0; i < 10; i++) {
            System.out.print("\t process : " + process[i]);
            System.out.print("\t arival : " + arrival[i]);
            System.out.println("\t burst : " + burst[i]);
            System.out.println("");
        }

        int quantom = 10;
        if (quantom == 0)
            quantom = 5;
        System.out.println("quantom : " + quantom);
        System.out.print("\t" + "time");
        System.out.print("\t" + "burst");
        System.out.print("\t" + "process");
        System.out.print("\t" + "Lottery");
        System.out.println("\t" + "ticket");

        System.out.println("---------------------------------------------------------");
        while ((burst[0] + burst[1] + burst[2] + burst[3] + 
                burst[4] + burst[5] + burst[6] + burst[7] + 
                burst[8] + burst[9]) > 0) {
            System.out.println("---------------------------------------------------------");
            p = 0;
            for (int i = 0; i < 10; i++) {
                if ((burst[i] > 0)) /*&& (time > arrival[i])*/ {
                    lottery[i] = burst[i] / 2;
                    if ((lottery[i] == 0) && (burst[i] > 0))
                        lottery[i] = 1;
                    for (int z = 0; z < lottery[i]; z++) {
                        ticket[i][z] = p++;
                        max_tick = p;
                    }
                    temp[i] = process[i];                


                    System.out.print("\t" );
                    System.out.print("\t" + burst[i]);
                    System.out.print("\t" + temp[i]);
                    System.out.print("\t" + lottery[i]);
                    for (int z = 0; z < lottery[i]; z++)
                        System.out.print("\t" + "[ " + ticket[i][z] + " ]");
                    System.out.println();
                } else if ((burst[i] > 0) && (time < arrival[i])) {
                    temp[i] = process[p];
                    lottery[i] = burst[i] / 2;
                    if ((lottery[i] == 0) && (burst[i] > 0))
                        lottery[i] = 1;
                    for (int z = 0; z < lottery[i]; z++){
                        ticket[i][z] = p++;
                        max_tick=p;
                    }

                    if (burst[i] > 0) {
                        time += quantom;
                    } else {
                        time += (burst[i] + quantom);
                    }

                    System.out.print("\t" + time);
                    System.out.print("\t" + burst[p]);
                    System.out.print("\t" + temp[i]);
                    System.out.print("\t" + lottery[i]);
                    for (int z = 0; z < lottery[i]; z++)
                        System.out.print("\t" + "[ " + ticket[i][z] + " ]");
                    System.out.println();

                }
            }

            int winner = rand.nextInt(max_tick);
            for(int i =0;i<10;i++)
                for(int z=0;z<lottery[i];z++)
                    if(ticket[i][z]==winner)
                         q=i;
            System.out.println("---------------------------------------------------------");
            System.out.println("Ticket Winner :" +"[ " +winner+" ]" );
        
            if ((burst[q] > 0))  {
            temp[q] = process[q];
            burst[q] -= quantom;


            if (burst[q] > 0) {
                time += quantom;
            } else {
                time += (burst[q] + quantom);
            }

            if (burst[q] < 0) {
                burst[q] = 0;
            }
            
            System.out.print("\t" + time);
            System.out.print("\t" + burst[q]);
            System.out.print("\t" + temp[q]);
            
                System.out.println();
            }else if ((burst[q] > 0) && (time < arrival[q])) {
                                temp[q] = process[p];
            if (burst[q] > 0) {
                time += quantom;
            } else {
                time += (burst[q] + quantom);
            }

            System.out.print("\t" + time);
            System.out.print("\t" + burst[q]);
            System.out.print("\t" + temp[q]);
            System.out.println();
            
        }
    }
        
}
}
