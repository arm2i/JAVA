package puissance4.model;

/**
 *
 * @author G37269
 */
public class Position {

    private int line;
    private int column;

    public Position(int line, int column) {
        if (line < 0 || line > Config.var5 || column < 0 || column > Config.var6) {
            throw new IllegalArgumentException("invalide position");
        }
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    // @Override
    @Override
    public String toString() {
        String chain;
        chain = "(" + line + "," + column + ")";

        return chain;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        Position autre = (Position) o;
        return this.line == autre.line && this.column == autre.column;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.line;
        hash = 17 * hash + this.column;
        return hash;
    }
}
