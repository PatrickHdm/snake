package hdm.org.se2.snake02;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridController {

	private static final double ELEMENT_SIZE = 20;
    private static final double GAP = 1;
    
    private GridPane gridPane = new GridPane();
    private Group display = new Group(gridPane);
    private int nRows;
    private int nCols;
    
    public GridController(int nRows, int nCols)	{
    	for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
            	gridPane.add(createElement(i,j), i, j);
                System.out.println("COL: "+i+" ROW: "+j);
            }
        }
    }    
    
    private Rectangle createElement(int x, int y) {
        Rectangle rectangle = new Rectangle(ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setFill(Color.BLACK);
        rectangle.setId("rec_"+x+""+y);

        return rectangle;
    }
    
    public Group getGrid() {
        return display;
    }
	
}
