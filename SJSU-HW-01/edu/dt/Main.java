package edu.dt;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tphadke on 8/29/17.
 */
public class Main {
    Map <Processor, List<Processor> > graph ;

    /**
     * passing root to init
     * @param root
     */
    public  Main(int root){

        init(root);

    }

    /**
     * asks user for selecting a root value to initiate the program
     * @param args
     */
    public static void main ( String args[]){
        Scanner Cin = new Scanner(System.in);

            System.out.println("Please select a root processor(0-5)");
            int root = Cin.nextInt();
            Main m = new Main(root);



        //TODO: Choose a processor as a Root
        //TODO: Send an initial message Message.M to this processor.



    }

    /**
     * Initializing all processors, and taking root from user input to send first
     * message to this processor to begin the algo
     * @param root
     */
    public void init(int root){
        Processor p0 = new Processor();
        p0.id = 0;
        Processor p1 = new Processor();
        p1.id = 1;
        Processor p2 = new Processor();
        p2.id = 2;
        Processor p3 = new Processor();
        p3.id = 3;
        Processor p4 = new Processor();
        p4.id = 4;
        Processor p5 = new Processor();
        p5.id = 5;

        List<Processor> p0neighbors = new ArrayList<Processor>();
        p0neighbors.add(p1);
        p0neighbors.add(p2);
        p0neighbors.add(p3);

        List<Processor> p1neighbors = new ArrayList<Processor>();
        p1neighbors.add(p2);
        p1neighbors.add(p4);
        p1neighbors.add(p0);

        List<Processor> p2neighbors = new ArrayList<Processor>();
        p2neighbors.add(p5);
        p2neighbors.add(p0);
        p2neighbors.add(p1);

        List<Processor> p3neighbors = new ArrayList<Processor>();
        p3neighbors.add(p0);

        List<Processor> p4neighbors = new ArrayList<Processor>();
        p4neighbors.add(p1);
        p4neighbors.add(p5);

        List<Processor> p5neighbors = new ArrayList<Processor>();
        p5neighbors.add(p4);
        p5neighbors.add(p2);
        
        p0.unexplored = p0neighbors;
        p1.unexplored = p1neighbors;
        p2.unexplored = p2neighbors;
        p3.unexplored = p3neighbors;
        p4.unexplored = p4neighbors;
        p5.unexplored = p5neighbors;


        switch(root) {
            case 0:
                p0.sendMessgeToMyBuffer(Message.M,p0);
                break;
            case 1:
                p1.sendMessgeToMyBuffer(Message.M,p1);
                break;
            case 2:
                p2.sendMessgeToMyBuffer(Message.M,p2);
                break;
            case 3:
                p3.sendMessgeToMyBuffer(Message.M,p3);
                break;
            case 4:
                p4.sendMessgeToMyBuffer(Message.M,p4);
                break;
            case 5:
                p5.sendMessgeToMyBuffer(Message.M,p5);
                break;
        }






        graph = new HashMap<>();

        graph.put(p0, p0neighbors);
        graph.put(p1, p1neighbors);
        graph.put(p2, p2neighbors);
        graph.put(p3, p3neighbors);
        graph.put(p4, p4neighbors);
        graph.put(p5, p5neighbors);

    }
}
