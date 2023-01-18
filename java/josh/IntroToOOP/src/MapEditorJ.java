/**
 * @author tora
 */
import java.util.Scanner;

class MapCoord {
    private final int row;
    private final int column;

    /**
     * Constructor for objects of class MapCoord
     *
     * @param r the row of the coordinate
     * @param c the column of the coordinate
     */
    public MapCoord(int r, int c) {
        this.row = r;
        this.column = c;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    /**
     * Returns a string representation of the coordinate
     *
     * @return a string representation of the coordinate
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", this.row, this.column);
    }
}

class TextMap {
    private final char[][] map;
    private final int rowCnt;
    private final int colCnt;
    private final char defaultChar;


    /**
     * Constructor for TextMap.
     *
     * @param rowCnt Number of rows in the map.
     *               Must be greater than 0.
     * @param colCnt Number of columns in the map.
     *               Must be greater than 0.
     * @param defaultChar The character to use for empty spaces.
     */
    public TextMap(int rowCnt, int colCnt, char defaultChar) {
        this.rowCnt = rowCnt;
        this.colCnt = colCnt;
        this.defaultChar = defaultChar;
        this.map = new char[rowCnt][colCnt];
        for (int r = 0; r < rowCnt; r++) {
            for (int c = 0; c < colCnt; c++) {
                this.map[r][c] = defaultChar;
            }
        }
    }

    public int getRowCnt() {
        return rowCnt;
    }


    public int getColCnt() {
        return colCnt;
    }

    /**
     * Checks if the given coordinates are within the map.
     *
     * @param coord The coordinates to check.
     *              The coordinates are assumed to be 0-based.
     *              The coordinates are assumed to be non-negative.
     *              The coordinates are assumed to be within the map.
     *
     * @return True if the coordinates are within the map, false otherwise.
     */
    public boolean isValideCoord(MapCoord coord) {
        return coord.getRow() >= 0 && coord.getRow() < this.rowCnt
                && coord.getColumn() >= 0 && coord.getColumn() < this.colCnt;
    }



    /**
     * Returns the character at the given coordinate.
     *
     * @param coord the coordinate to be checked
     *              if the coordinate is valid, the character at the coordinate is returned
     *              if the coordinate is invalid, the default character is returned
     *
     * @return the character at the coordinate
     */
    public char getPos(MapCoord coord) {
        // check if the coordinate is valid if false return the character defaultChar
        if (!isValideCoord(coord)) {
            return this.defaultChar;
        }
        // return the character at the coordinate
        return this.map[coord.getRow()][coord.getColumn()];
    }


    /**
     * @param coord The coordinate to set
     *              If the coordinate is not valid, nothing will be done.
     *              If the coordinate is valid, the character will be set.
     *
     * @return true if the coordinate is valid, false otherwise.
     */
    public boolean setPos(MapCoord coord, char c) {
        // Check if the coordinate is valid return false if it is not
        if (!isValideCoord(coord)) {
            return false;
        }
        // Set the character at the coordinate
        this.map[coord.getRow()][coord.getColumn()] = c;
        return true;
    }

    /**
     * Returns a string representation of the map.
     *
     * @return a string representation of the map
     */
    @Override
    public String toString() {
        // start with an empty string
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < this.rowCnt; r++) {
            for (int c = 0; c < this.colCnt; c++) {
                // append the character at the current position
                sb.append(this.map[r][c]);
            }
            // append a newline character
            sb.append(System.lineSeparator());
        }
        // return the string representation
        return sb.toString();
    }
}

public class MapEditorJ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter rows, columns, and default character:");
        int rowC = scanner.nextInt();
        int colC = scanner.nextInt();
        char defaultCh = scanner.next().charAt(0);
        TextMap t = new TextMap(rowC, colC, defaultCh);
        System.out.println(t);


        System.out.println("Enter row, column, and character:");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        char ch = scanner.next().charAt(0);
        t.setPos(new MapCoord(row, col), ch);
        System.out.println(t);

    }
}

