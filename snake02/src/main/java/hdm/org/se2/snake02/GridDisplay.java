package hdm.org.se2.snake02;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridDisplay {

	private static final double ELEMENT_SIZE = 20;
    private static final double GAP = 1;
	
	private TilePane tilePane = new TilePane();
    private Group display = new Group(tilePane);
    private int nRows;
    private int nCols;
	
    public GridDisplay(int nRows, int nCols) {
        tilePane.setStyle("-fx-background-color: rgba(0, 0, 0, 1);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP);
        setColumns(nCols);
        setRows(nRows);
    }
    
    public void setColumns(int newColumns) {
        nCols = newColumns;
        tilePane.setPrefColumns(nCols);
        createElements();
    }

    public void setRows(int newRows) {
        nRows = newRows;
        tilePane.setPrefRows(nRows);
        createElements();
    }

    public Group getDisplay() {
        return display;
    }

    private void createElements() {
        tilePane.getChildren().clear();
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
                tilePane.getChildren().add(createElement(i,j));
                System.out.println("COL: "+i+" ROW: "+j);
            }
        }
    }

    private Rectangle createElement(int y, int x) {
        Rectangle rectangle = new Rectangle(ELEMENT_SIZE, ELEMENT_SIZE);
        rectangle.setStroke(Color.WHITE);
        rectangle.setFill(Color.BLACK);
        rectangle.setY(y);
        rectangle.setX(x);
        String id = "ra_"+y+""+x;
        rectangle.setId(id);

        System.out.println(rectangle);
        
        return rectangle;
    }
    
}
