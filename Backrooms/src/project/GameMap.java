package project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GameMap {
	private List vertexList;
	private int n;
	
	public GameMap() {
		vertexList = null;
		n = 0;
	}

	public List getVertexList() {
		return vertexList;
	}

	public void setVertexList(List vertexList) {
		this.vertexList = vertexList;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
	public void load(String filepath) {
		File f = new File(filepath);
		Scanner reader;
		int i;
		String vertex, edge, distance, data;
		
		this.vertexList = new List();
		
		try {
			reader = new Scanner(f);
			while(reader.hasNextLine()) {
				vertex = "";
				edge = "";
				distance = "";
				i = 0;
				data = reader.nextLine();
					
				// Consume string until '(' is reached
				for(; i < data.length() && data.charAt(i) != '('; i++);
				
				// Consume string until it hits a character
				i++;
				for(; i < data.length() && data.charAt(i) == ' '; i++);
				
				// Reads the vertex name until it hits a comma or blank space
				for(; i < data.length() && (data.charAt(i) != ',' && data.charAt(i) != ' '); i++)
					vertex += data.charAt(i);
						
				// Consumes blank spaces after the vertex name
				if(data.charAt(i) == ' ')
					for(; i < data.length() && data.charAt(i) != ','; i++);
				
				// Consume string until it hits a character
				i++;
				for(; i < data.length() && data.charAt(i) == ' '; i++);
								
				// Read the edge name until it hits a comma or blank space
				for(; i < data.length() && (data.charAt(i) != ',' && data.charAt(i) != ' '); i++)
					edge += data.charAt(i);
				
				// Consumes blank spaces after the edge name
				if(data.charAt(i) == ' ')
					for(; i < data.length() && data.charAt(i) != ','; i++);
		
				// Consume string until it hits a character
				i++;
				for(; i < data.length() && data.charAt(i) != ' '; i++);
					
				// Read the weight of the edge
				i++;
				for(; i < data.length() && (data.charAt(i) != ')' && data.charAt(i) != ' '); i++)
					distance += data.charAt(i);
					
				vertexList.add(vertex, edge, Integer.parseInt(distance));
			}
			reader.close();
		} catch (IOException e) {
			vertexList = null;
			e.printStackTrace();
		}	
	}
	
}
