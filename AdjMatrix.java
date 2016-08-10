import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{

	/**
	 * Contructs empty graph.
	 */
    protected ArrayList<T> verlabels;
    protected boolean[][] adjmatrix;

    // Variables for method shortestdistance;
    int moves = 0;

    public AdjMatrix() {

	// Implementing arrays for storing data (For now kept as 10).         
	adjmatrix = new boolean[10][10];
	verlabels = new ArrayList<T>();
	
	// Setting the arrays to default values.
	for(int i = 0; i < 10; i++){


		for(int k = 0; k < adjmatrix.length; k++){


			adjmatrix[i][k] = false;

		}

	}
	
	// Printing for debug use.
	System.out.println("[!] AdjMaxtrix initialised.");


    } // end of AdjMatrix()
    
    
    public void addVertex(T vertLabel) {

		// Checking if the array is empty;
		if(verlabels.size() == 0){

			verlabels.add(vertLabel);

			// For debug purposes.
			System.out.println("[!] Vertex Added!");

		}else {

		// Checking if variable exists;
		if(findvertex(vertLabel) != -1){
	
			System.out.println("[!] Vertex already exists, please use a different value.");

		}else {

			verlabels.add(vertLabel);

			// For debug purposes.
			System.out.println("[!] Vertex Added!");
		}
	}

    } // end of addVertex()
	
    
    public void addEdge(T srcLabel, T tarLabel) {

	// Variables for use;
	int src_location = -1;
	int tar_location = -1;

	// Checking the srcLabel location with a for loop;
	for(int i = 0; i < verlabels.size(); i++){

		try{
			if(verlabels.get(i).equals(srcLabel)){
			
				src_location = i;
			
				// For debug purposes.
				System.out.println("[!] Found srcLabel at: " + i + ".");	
			} 

			if(verlabels.get(i).equals(tarLabel)){
			
				tar_location = i;
			
				// For debug purposes.
				System.out.println("[!] Found tarLabel at: " + i + ".");	
			} 		

		}catch (NullPointerException e){

			// Do nothing;
		}
	}


	if((src_location == -1) || (tar_location == -1)){

		System.out.println("[!] Not a valid edge. ");
		
	}else {

		adjmatrix[src_location][tar_location] = true;
		adjmatrix[tar_location][src_location] = true;

		// For debug purposes.
		System.out.println("[!] AdjMatrix updated.");
	}

    } // end of addEdge()
	

    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();

	// Variables for use;
	int location = findvertex(vertLabel);

	if(location == -1){

		System.out.println("[!] Vertex does not exist.");
		
	}else {
		
		for(int i = 0; i < verlabels.size(); i++){

			if(adjmatrix[i][location] == true){

				neighbours.add(verlabels.get(i));
			
			}

		} 

	}
  
        return neighbours;
    } // end of neighbours()
    
    
    public void removeVertex(T vertLabel) {

	// Variables for use;
	int location = findvertex(vertLabel);
	int temp = location;
	
	// Checking if the vertex actually exists.
	// findvertex() returns -1 if it doesn't.
	if(location == -1){

		System.out.println("[!] Vertex does not exist.");
		
	}else {
		
		// Moving the vertlabels to -1 position;
		while(temp < verlabels.size()){

			if(temp != verlabels.size() - 1){
			
				verlabels.set(temp, verlabels.get(temp + 1));
			}

			temp++;
		} 
		
		// Setting the last variable in the array to null;
		verlabels.remove(verlabels.size() - 1);
		
		// Reseting the values;
		temp = location;

		// For debug purposes;
		System.out.println("[!] Verlabels array reshuffled.");

		// Shuffling the boolean matrix to -1;
		while(temp < 10){

			for(int i = 0; i < 10; i++){

				try{
					if(i == adjmatrix.length - 1){
	
						adjmatrix[temp][i] = false;

					}else {

						adjmatrix[temp][i] = adjmatrix[temp + 1][i];
						adjmatrix[i][temp] = adjmatrix[i][temp + 1];
					}

				}catch(ArrayIndexOutOfBoundsException e){
					
					// Do nothing;
				}
			}

			temp++;
		}

		temp = location;

		while(temp < 10){

			for(int i = 0; i < 10; i++){

				try{
					if(i == adjmatrix.length - 1){
	
						adjmatrix[temp][i] = false;

					}else {

						adjmatrix[temp][i] = adjmatrix[temp + 1][i];
					
					}

				}catch(ArrayIndexOutOfBoundsException e){
					
					// Do nothing;
				}
			}

			temp++;
		}
				
	}

     } // end of removeVertex()
	
    
    public void removeEdge(T srcLabel, T tarLabel) {
        
	// Variables for use;
	int src_location = findvertex(srcLabel);
	int tar_location = findvertex(tarLabel);

	// Checking if the values are -1
	if((src_location == -1) || (tar_location == -1)){

		System.out.println("[!] Edge does not exist.");

	}else {

		// Checking if they are currently not an Edge;	
		// Checking only the column and row as the graph
		// is symtrical;
		if((adjmatrix[src_location][tar_location] == false)){
			
			System.out.println("[!] Edge does not exist.");

		}else {

			adjmatrix[src_location][tar_location] = false;
			adjmatrix[tar_location][src_location] = false;


			// For debug purposes.
			System.out.println("[!] AdjMatrix updated.");
		}
	}


    } // end of removeEdges()
	
    
    public void printVertices(PrintWriter os) {

	
	// Printing the verlabels.
	for(int i = 0; i < verlabels.size(); i++){

		System.out.print("  " + verlabels.get(i)+ "   ");
	}

	System.out.println(" ");

	for(int i = 0; i < verlabels.size(); i++){

		for(int l = 0; l < verlabels.size(); l++){

			System.out.print("------");

		}	

		System.out.println("");

		for(int k = 0; k < verlabels.size(); k++){
		
			if(adjmatrix[i][k] == false){
				
				System.out.print("  0  |");

			}else {

				System.out.print("  1  |");
			}
		}

		System.out.println("");

	}

	for(int l = 0; l < verlabels.size(); l++){

			System.out.print("------");

	}

	System.out.println("");

    } // end of printVertices()
	
    
    public void printEdges(PrintWriter os) {
        
	// Checking the boolean adjmatrix, if true prints out the edges.
	for(int i = 0; i < verlabels.size(); i++){

		for(int k = 0; k < verlabels.size(); k++){

			if(adjmatrix[i][k] == true){
			
				System.out.println(verlabels.get(i) + " " + verlabels.get(k));

			}
		}

	}
    } // end of printEdges()
    
    
    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	
	// Variables for use;
	int vert1_location = findvertex(vertLabel1);
	int vert2_location = findvertex(vertLabel2);

	// Array for storing previous;
	int[] previous = new int[verlabels.size()];

	// Initialising for the recusive loop.
	for(int i = 0; i < previous.length; i++){
		previous[i] = -1;
	}

	previous[0] = vert1_location;

	// Array for storing edges.
	int distance = findedges(vert1_location, vert2_location, previous, moves++);
				
        // if we reach this point, source and target are disconnected
        return -1;    	
    } // end of shortestPathDistance()


    // Recursive loop;
    public int findedges(int source, int target, int previous[], int counter){

	// For debug purposes;
	System.out.println("Source: " + source);
	System.out.println("Target: " + target);
	System.out.println("Counter: " + counter);
	
	// Array 
	int[] edges = new int[verlabels.size()];

	// Array counter;
	int array_counter = 0;
	int distance;

	// Incrementing counter;
	counter++;

	// Finding all edges in the matrix;
	for(int k = 0; k < verlabels.size(); k++){

		if(adjmatrix[source][k] == true){

			// Checking if its in the previous;
			boolean found = false;
			for(int i = 0; i < previous.length; i++){
				if(previous[i] == k){
					found = true;
				}
			}
			if(found == false)
			{
				edges[array_counter] = k;
				array_counter++;
				
			}

		}
	}

	// Adding a previous to the node;
	for(int l = 0; l < previous.length; l++){

		if(previous[l] == -1){
			
			previous[l] = source;
			break;
		}

	} 

	// Checking if the array has the target;	
	for(int k = 0; k < array_counter; k++){

		// For debug purposes;
		System.out.println(edges[k]);


		if(edges[k] == target){

			return counter;		
		}else {

			distance = findedges(edges[k], target, previous, counter);

			if(distance != -1){

				return counter;

			}			
		}
	}

	

	// If nothing calling for each edge adding	

	return counter;

    }

	
    public int findvertex(T vertex){

	// Variable for use;
	int location = -1;

	// Searching for the column/row location of the vertLabel;
	// Checking the Label location with a for loop;
	for(int i = 0; i < verlabels.size(); i++){

		try{
			if(verlabels.get(i).equals(vertex)){
			
				// For debug purposes.
				System.out.println("[!] Found Label at: " + i + ".");

				location = i;
				break;					
				
			} 


		}catch (NullPointerException e){

			// Do nothing;
		}
	

	}

	return location;
	
    }
    
} // end of class AdjMatrix