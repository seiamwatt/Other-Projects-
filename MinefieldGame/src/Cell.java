public class Cell {
    private boolean revealed;
    private String status;
    private int rowLocation;
    private int columnLocation;
    public Cell(boolean revealed, String status,int row,int column) {
        this.revealed = revealed;
        this.status = status;
        this.rowLocation = row;
        this.columnLocation = column;
    }

    public boolean getRevealed() {
        return revealed;
    }

    public void setRevealed(boolean r) { revealed = r; }


    public String getStatus() {
        return status;
    }

    public void setStatus(String c) {
        status = c;
    }

    public int getRow(){
        return this.rowLocation;
    }

    public int getColumn(){
        return this.columnLocation;
    }

}
