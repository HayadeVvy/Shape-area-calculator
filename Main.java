// Area calculation program| Written by Anthony(Jinu) Lee, NET-ID: lxa151030 
//
//
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
public static void main(String[] args) throws IOException {
		
		
		//Sets scanner for user input and name for input and output file.
		Scanner user_input = new Scanner(System.in);
		String input_file;
		PrintWriter output = new PrintWriter("pilot_areas.txt");	
		//Prompts user to enter file name.
		System.out.println("Please type your file name.");
		input_file = user_input.nextLine();
		//Assigns the file name for the input file.
		File input = new File(input_file);
		//Reserves a 1-D array for pilot names and 3-D array for the coordinates of the pilot.
		String[] pilot = new String[20];	
		double[][][] array = new double[20][16][2];	
		//This creates sets a default value of the number of pilot and default area to 0.
		int each_pilot = 0; 
		double area = 0.0;	
		//If the file is opened correctly, proceeds to accessing its element.
		if (input.exists())	
		{
			System.out.println("File opened. Accessing element.....");
			//Sets a scanner for the file element that traverses through each line.
			Scanner in_f = new Scanner(input);
			//This loop keep launches until the scanner has met the end of the file and the number of pilot is not exceeding 20.
			while (in_f.hasNextLine() && each_pilot < 20)	
			{
				//Scans the first element of the line as a name of the pilot and stores it into pilot array.
				String a = in_f.next();	
				pilot[each_pilot] =a;	
				//Calls coordinate function.
				Get_Coordinates(array,in_f, each_pilot);	
				//Calls Calculate_area function to receive area of the pilot.
				area = Calculate_area(array, each_pilot);
				//Output the pilot name and his/her achieved area into the output file.
				output.println(a + " " +String.format("%.2f", area));	
				//Increment counter for number of pilot.
				each_pilot++;	
			}
		System.out.println("Pilot names and areas are successfully written to file.");
		//Closes input and output files. Then, proceed to terminate program.
		in_f.close();	
		}
		//If file is not opened correctly, throws out the error statement.
		else	
		{
			System.out.println("The file does not exist.");
		}
		//Closes each scanner.
		user_input.close();
		output.close();	
	}
	//This function receives the filestream and array from the main function, Scans through the line and receives the coordinate, proceeding to input them input array until the end of line has met.
	public static void Get_Coordinates(double[][][] array, Scanner in, int row)	
	{
		//column counter
		int column = 0; 
		//place holder for value that has been read from the file.
		double temp; 
		//A string place holder to contain a line from the file.
		String k;	
		//String for using split function.
		String[] h;	
		//Receives each line from the file.
		k =in.nextLine();	
		//Set scanner to scan the line until the end of the line.
		Scanner u = new Scanner(k); 
		u.useDelimiter(" ");//Separates element by space
		// This loop executes and input values into array until the end of line and maximum number of coordinates have been met.
		while (u.hasNext() && column != 15)
		{
			//Takes another string from the Scanner u and separate element by ,
			 String kr = u.next(); 
			 h = kr.split(",");	  
			
			for(int check=0; check < 2; check++)	//This loop parse the string element into double and puts it in to x,y coordinate.
			{
				temp = Double.parseDouble(h[check]); 
				array[row][column][check] = temp;
	
			}
			column++;	//Increment the column counter.
		
		}
		//closes a scanner.
		u.close();
	}

	//This function calculates the area of the each pilot covered.
	public static double Calculate_area(double [][][] array, int row)	
	{
		
		double sum =0;	//Initialize the sum of coordinate to 0.
		
		for (int i =14; i >= 0; i--)	//Scan through all the x,y coordinate and apply this formula: (X1*Y2-Y1*X2) for each coordinates
		{	
			
			sum += ((array[row][i][0]*array[row][i+1][1]) - (array[row][i][1]*array[row][i+1][0]));
		}
		
		//Returns the result of formula 0.5*|(X(N)*Y(N+1)-Y(N)*X(n+1))|
		return .5 * Math.abs(sum);	
	}

}
