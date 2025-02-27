import java.util.Scanner;

import javax.naming.ldap.ExtendedRequest;

class CircularQueue{
    int [] arr;
    int front;
    int rear;

    CircularQueue(int size){
    
        arr = new int[ size ];
        front = -1;
        rear = -1;
    }

    boolean isQueueFull( ){
        return ( front == (rear+1) % arr.length );
    }   

    boolean isQueueEmpty( ){
        return ( rear == -1 &&  front == rear );
    }   

    void enqueue(int ele){
        //step-1: check queue is not full 
        if( isQueueFull() ){
            throw new RuntimeException("Queue is full !!!");
        }else{//if queue is not full then only we can insert an element into it
            //step-2: increment the value of rear by 1
            rear = (rear+1) % arr.length;
            //step-3: insert an element into the queue from rear end
            arr[ rear ] = ele;
            /* step-4: if( front == -1 ) => front = 0 */
            if( front == -1 )
                front = 0; 
        }
    }

    int getFront(){
        if( isQueueEmpty() ){
            throw new RuntimeException("Queue is empty");
        }else{
            //return the value of an ele at front end
            return ( arr[ front ] );
        }
    }

    void dequeue(){
        //step-1: check queue is not empty
        if( isQueueEmpty() ){
            throw new RuntimeException("Queue is empty");
        }else{//if queue is not empty then only we can delete an element from it
            if( front == rear ){//if we are deleting last ele
                front = rear = -1;//reinitialize queue
            }else{
                //step-2: increment the value of front by 1 [ by means of incrementing value of front by 1 we are achieving deletion of an element from queue ].
                front = (front+1) % arr.length;
            }
        }
    }

}

public class CircularQueueMain {
    
    public static int menu( ){
        
        //display menu list
        System.out.println("***** circular queue *****");
        System.out.println("0. exit");
        System.out.println("1. enqueue");
        System.out.println("2. dequeue");
        
        //accept choice from user and return it to the calling function
        System.out.print("enter the choice : ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        return choice;
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);

        int ele;
        Scanner sc =new Scanner(System.in);

        while( true ){
            int choice = menu();
            switch( choice ){
                case 0: System.exit(0);

                case 1://insert ele into the queue
                    try{
                        //accept ele from user
                        System.out.print("enter an ele : ");
                        ele = sc.nextInt();

                        cq.enqueue(ele);
                        System.out.println(ele+" inserted into the queue ....");
                    }catch( RuntimeException e){
                        System.out.println(e.getMessage() );
                    }
                    break;

                case 2://delete ele from the queue
                    try{
                        //get the value of an ele at front end
                        ele = cq.getFront();
                        //delete ele from the queue
                        cq.dequeue();
                        System.out.println("deleted ele is : "+ele);
                    }catch( RuntimeException e){
                        System.out.println(e.getMessage() );
                    }

                    break;
            }

        }

    }   
}
