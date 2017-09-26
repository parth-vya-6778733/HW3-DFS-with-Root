package edu.dt;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by tphadke on 8/29/17.
 * processor has its own message buffer, id, children if applicable,
 * a parent process if itself is not root, and a neighbor pj which may
 * or may not be the parent, but sends the message to this processor.
 */
public class Processor implements Observer {
    //Each processsor has a message Buffer to store messages
    Buffer messageBuffer ;
    Integer id ;
    List<Processor> children ;
    Processor parent;
    Processor pj;

    //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
    List<Processor> unexplored ;

    /**
     * initialize processor's params
     */
    public Processor() {
        messageBuffer = new Buffer();
        id = Integer.MIN_VALUE; //This is an invalid value. Since only +ve values are acceptable as processor Ids.
        children = new ArrayList<>();
        //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
        unexplored = new ArrayList<>();
        //Each processor is observing itself;
        messageBuffer.addObserver(this);
        this.pj = null;
        this.parent = null;
    }

    //This method will only be used by the Processor

    /**
     * remove processor p from this processors unexplored list
     * @param p
     */
    private void removeFromUnexplored(Processor p){
        if(!unexplored.isEmpty()) {
            unexplored.remove(p);
        }
//        for (Processor u : unexplored) {
//            System.out.println("Unexplored Nodes After Removal: " + u.id);
//        }
//        System.out.println("Removed Node: " + p.id);
//        System.out.println("From Node: " + this.id);
    }

    /**
     * send message to this processor with reference to sender as pj
     * @param message
     * @param pj
     */
    //This method will add a message to this processors buffer.
    //Other processors will invoke this method to send a message to this Processor
    public void sendMessgeToMyBuffer(Message message, Processor pj){
    	this.pj = pj;
//    	System.out.println("Pi/this: " + this.id);
//        System.out.println("Pj: " + pj.id);
//        System.out.println("Message: " + message);
        messageBuffer.setMessage(message);
        
    }


    /**
     * this method is called when the message buffer is updated with a new message
     * @param observable
     * @param arg
     */
    //This is analogous to recieve method.Whenever a message is dropped in its buffer this Pocesssor will respond
    //TODO: implement the logic of receive method here
    //      Hint: Add switch case for each of the conditions given in receive
    public void update(Observable observable, Object arg) {
            switch(messageBuffer.getMessage()) {
                case M:
                    if(this.parent == null && this != pj)
                    {
                        this.parent = pj;
                        pj.removeFromUnexplored(this);
                        this.explore();
                    }
                    else if(this.parent == null && this == pj)
                    {
                        this.parent = this;
                        this.explore();
                    }
                    else
                    {
                       	pj.removeFromUnexplored(this);
                    	pj.sendMessgeToMyBuffer(Message.ALREADY,this);
                        
                    }
                    break;
                case ALREADY:
                    this.explore();
                    break;
                case PARENT:
                    if(pj.parent == this) {
                        this.children.add(pj);
                        System.out.println("Parent Node: " + this.id);

//                        for (Processor u : unexplored) {
//                            System.out.println("Unexplored Nodes: " + u.id);
//                        }
                        for (Processor c : children) {
                            System.out.println("Child: " + c.id);
                        }
                    }
                    this.explore();
                    break;
            }

    }

    /**
     * this is called to explore additional neighbors which are in the
     * unexplored list
     */
    private void explore(){
        //TODO: implement this method.
        if(!unexplored.isEmpty())
        {

        	Processor ptemp = unexplored.get(0);
//            System.out.println("Next Node: " + ptemp.id);
        	removeFromUnexplored(unexplored.get(0));
            ptemp.sendMessgeToMyBuffer(Message.M,this);
        }
        else
        {
            if(parent != this)
            {
                parent.sendMessgeToMyBuffer(Message.PARENT, this);
            }
            else
            {
                //print out root id
                System.out.println("Root: " + this.id);
            }
        }
    }

}
