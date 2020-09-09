package pathfinder;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
	private Stack<Point> path;
	private char[][] fieldReadings;
	private int maxCollumns;
	private int maxRows;
	private Point entranceLocation;
	private Point exitLocation;
	
	public DepthFirstSearch(char[][] fieldReadings) {
		path = new Stack<Point>();
		setField(fieldReadings);
	}
	
	public void setField(char[][] fieldReadings) {
		this.fieldReadings = fieldReadings;
		this.maxRows = fieldReadings.length;
		this.maxCollumns = fieldReadings[0].length;
		for(int i = 0; i < this.maxRows; ++i) {
			for(int k = 0; k < this.maxCollumns; ++k) {
				getEntranceLocation(i, k, fieldReadings[i][k]);
				getExitLocation(i, k, fieldReadings[i][k]);
			}
		}
	}
	
	public void reset() {
		path.clear();
	}
	
	private void getEntranceLocation(int row, int collumn, char fieldReading) {
		if(fieldReading == 'I')
			this.entranceLocation = new Point(row, collumn);
	}
	
	private void getExitLocation(int row, int collumn, char fieldReading) {
		if(fieldReading == 'O')
			this.exitLocation = new Point(row, collumn);
	}
	
	public List<Point> getPath(){
		List<Point> finalPath = new ArrayList<Point>();
		if(move(entranceLocation.x, entranceLocation.y)) {
			for(Point p: path) {
				finalPath.add((Point) p.clone());
			}
		}
		return finalPath;
	}
	
	private boolean move(int row, int collumn) {
		System.out.println(row + "," + collumn);
		if(isValidPosition(row, collumn) && evaluateMove(row, collumn))
			return true;
		else
			return false;			
	}
		
	private boolean evaluateMove(int row, int collumn) {
		if(reachEndLocation(row, collumn) || moveRight(row, collumn) || moveDown(row, collumn) || moveLeft(row, collumn) || moveUp(row, collumn))
			return true;
		else {
			removeLastInPath();
			return false;
		}		
	}
	
	private boolean isValidPosition(int row, int collumn) {
		if(exceedFieldLimit(row) || exceedFieldLimit(collumn) || reachBlockage(row, collumn) || isAlreadyTried(row, collumn))
			return false;
		else {
			addToPath(row, collumn);
			return true;
		}
	}
	
	private boolean isAlreadyTried(int row, int collumn) {
		if(path.contains(new Point(row,collumn)))
			return true;
		else
			return false;
	}
	
	private void addToPath(int row, int collumn) {
		path.add(new Point(row,collumn));
	}
	
	private void removeLastInPath() {
		path.pop();
	}
	
	private boolean reachBlockage(int row, int collumn) {
		if(fieldReadings[row][collumn] == 'X')
			return true;
		else 
			return false;
	}
	
	private boolean exceedFieldLimit(int rowOrCollumn) {
		if(rowOrCollumn >= maxCollumns || rowOrCollumn < 0)
			return true;
		else
			return false;
	}
	
	private boolean reachEndLocation(int row, int collumn) {
		if(row == exitLocation.x && collumn == exitLocation.y)
			return true;
		else
			return false;
	}
		
	private boolean moveUp(int row, int collumn) {
		if(move(row - 1, collumn))
			return true;
		else
			return false;
	}
	
	private boolean moveLeft(int row, int collumn) {
		if(move(row, collumn - 1))
			return true;
		else
			return false;
	}
	
	private boolean moveDown(int row, int collumn) {
		if(move(row+1, collumn))
			return true;
		else
			return false;
	}
	
	private boolean moveRight(int row, int collumn) {
		if(move(row, collumn + 1))
			return true;
		else
			return false;
	}
}
