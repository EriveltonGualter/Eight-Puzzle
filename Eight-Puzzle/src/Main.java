import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Main {
	// Main method of the project
	public static void main (String args[]) throws IOException
	{		
		LogFile logFileObj = new LogFile();
		
        //String title = "Log file for the puzzle";
        //logFileObj.WriteThis(title);
                
        Boolean loop = true;
        int nMoves = 0;
        
		// Generate the sequence
		int[] sequence = {1,2,3,8,0,4,7,6,5}; // 123804765
		int[] data = {0,1,2,3,4,5,6,7,8};
		int[] dataInicial;
		int blank = 0;
	   
		Random rnd = ThreadLocalRandom.current();
	    for (int i = data.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = data[index];
	      data[index] = data[i];
	      data[i] = a;
	    }
	    dataInicial = data;
	    
	    Tree arv = new Tree();
	    
	    // Loop of the system
	    while(loop){
	    		    	
	    	// Heuristicas
	    	int h1,h2,h3;
	    	Heuristica h = new Heuristica();
	    	h1 = h.getHeuristica1(data, sequence);
	    	h2 = h.getHeuristica2(data, sequence);
	    	h3 = h.getManhattan(data, sequence);
	 
	    		    	
	    	System.out.printf("\n Heuristica 1 = %d\n Heuristica 2 = %d\n Heuristica 3 = %d\n", h1, h2, h3);
	    	
	    	// Show vector and Matrix do the sequence 
		    System.out.printf(" Data: ");
		    for (int i=0;i<9;i++)
		    	System.out.printf("%d", data[i]);
		    System.out.printf("\n Numero de movimentos %d", nMoves);
	    	System.out.printf("\n\t\t %d %d %d", data[0], data[1], data[2]);
			System.out.printf("\n\t\t %d %d %d", data[3], data[4], data[5]);
			System.out.printf("\n\t\t %d %d %d", data[6], data[7], data[8]);
			
			// Get keyboard information
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\n Choose the direction: ");
			String dir = keyboard.next();

		    // Find the blank space
			for (int i=0; i<9; i++){
		    	if (data[i] == 0){
		    		blank = i;
		    	}
		    }	
			
			switch (dir){
			// Move blank space up
			case "w":
				if (blank > 2){
					data[blank] = data[blank-3];
					data[blank-3] = 0;
				}
		    	arv.insert(data);
		    	nMoves++;
				break;
			// Move blank space down
			case "s":
				if (blank < 6){
					data[blank] = data[blank+3];
					data[blank+3] = 0;
				}
		    	arv.insert(data);
		    	nMoves++;
		    	break;
			// Move blank space left
			case "a":
				if ( (blank % 3) != 0 ){
					data[blank] = data[blank-1];
					data[blank-1] = 0;
				}				
		    	arv.insert(data);
		    	nMoves++;
		    	break;
			// Move blank space right
			case "d":
				if ( (blank != 2) && (blank != 5) && (blank != 8)){
					data[blank] = data[blank+1];
					data[blank+1] = 0;
				}
		    	arv.insert(data);
		    	nMoves++;
		    	break;
			case "q":
				arv.insertTree(dataInicial);
				logFileObj.WriteTree(arv.getNode());
				logFileObj.CloseFile();	
				loop = false;
				nMoves++;
				break;
			default:
				break;
			}    
			
	    }
	}
}
