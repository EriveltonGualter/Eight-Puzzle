// CLASS:  LogFile              in PROGRAM:  AppendingToAFile
// AUTHOR:  D. Kaminski
// DESCRIPTION:  This class handles all accessing of the LogFile including opening it,
//      writing to it and closing it.  It checks with the user to determine whether to
//      open it in APPEND mode or OVER-WRITE mode.
// *************************************************************************************

//package appendingtoafile;

import java.io.*;
import java.util.Arrays;

class LogFile 
{
    // -------------------------- DECLARATIONS -------------------------------
    // This is declared HERE rather than in the constructor so it's accessible
    //      from any method in this class.
    // -----------------------------------------------------------------------
    static FileWriter file;
    static BufferedWriter logFile;
    //to read user input(more than one byte) we can redirect stdin
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
    // --------------------------- CONSTRUCTOR -------------------------------
    // This "open" happens only ONCE, BEFORE any writing is done.
    // The true says to create the file if it doesn't exist, or APPENDING to
    //      it if it DOES exist.
    // -----------------------------------------------------------------------

    public LogFile() throws IOException
    {
        //System.out.print("Do you want to APPEND to or OVER-WRITE the LogFile?" +
        //    "(Enter A or O):  ");
            
        //String option = in.readLine();
          String option = "o";  
        if ("A".equals(option.trim()) || "a".equals(option.trim()))
            file = new FileWriter("LogFile.txt", true);
        else
            file = new FileWriter("LogFile.txt");
        
        logFile = new BufferedWriter(file);
        
        System.out.println("OK, LogFile is now opened");
    }
    // ---------------------------- METHODS ----------------------------------
    public void WriteThis(String message) throws IOException
    {
        logFile.write(message);
        logFile.newLine();
        //System.out.format("OK, this was written to LogFile:  %s\n", message);
    }
    // -----------------------------------------------------------------------
    public void CloseFile() throws IOException
    {
        logFile.close();
        System.out.println("OK, LogFile is now closed");
    }
    // -----------------------------------------------------------------------
    public void WriteTree(int tree[][]) throws IOException
    {
    	String data = new String();
    	String filhos = new String();
    	String id = new String();
    	String h = new String();
    	
    	for (int i=0; i<tree.length; i++){	
    		if (tree[i][9] != -1){
	    		String clean;
	    		clean = Arrays.toString(tree[i]);
	    		clean = clean.replace(",", "");
	    		clean = clean.replace(" ", "");
	    		clean = clean.replace("[", "");
	    		clean = clean.replace("]", "");
	    		clean = clean.replace("-2", " ");
	    		if (i<10){
	    			data = clean.substring(0, 9);
	    			id = clean.substring(9, 10);
	    			filhos = clean.substring(10, clean.length()-1);
	    		}
	    		else if (i>=10 && i<100){
	    			data = clean.substring(0, 9);
	    			id = clean.substring(9, 11);
	    			filhos = clean.substring(11, clean.length()-1);
	    		}
	    		else if (i>=100 && i<1000){
	    			data = clean.substring(0, 9);
	    			id = clean.substring(9, 12);
	    			filhos = clean.substring(12, clean.length()-1);
	    		}
	    		else if (i>=1000){
	    			data = clean.substring(0, 9);
	    			id = clean.substring(9, 13);
	    			filhos = clean.substring(13, clean.length()-1);
	    		}
	    		h = clean.substring(clean.length()-2, clean.length());
	    		//filhos = clean.substring(9, clean.length()).replace("", " ").trim();
	    		String w = String.format("%s %s %s h:%s",data,id,filhos,h);
	    		w = w.replace("- ", "-");
	    		WriteThis(w);
    		}
    	}
    }
}
