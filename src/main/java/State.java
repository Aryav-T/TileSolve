import java.util.ArrayList;
import java.util.List;

public class State {
   /* attributes to represent current state */
   private int[][] board;
   private int emptyR;
   private int emptyC;
   private State parent;// ← reference to parent.  Null if no parent.

   private int depth;

   public State(int[][] initial) {
       parent = null;
       board = copy(initial);
       setEmptyLocation();
   }

   public State(State toCopy) {
       this.board = copy(toCopy.board);
       this.parent = toCopy.parent;
       this.emptyC = toCopy.emptyC;
       this.emptyR = toCopy.emptyR;
   }

   public int getDepth() {
       return depth;
   }

   public void setDepth(int depth) {
       this.depth = depth;
   }

   public void setEmptyR(int emptyR) {
       this.emptyR = emptyR;
   }

   public void setEmptyC(int emptyC) {
       this.emptyC = emptyC;
   }

   public int[][] getBoard() {
       return board;
   }

   public void setParent(State p) {
       this.parent = p;
   }

   public boolean isGoal(int[][] board) {
       int correctVal = 1;
       for (int r = 0; r < board.length; r++) {
           for (int c = 0; c < board[r].length; c++) {
               if (correctVal == board.length*board[0].length) correctVal = 0;  // for lower right corner val
               if (board[r][c] != correctVal) return false;
               correctVal++;
           }
       }
       return true;
   }


   public ArrayList<State> getNextStates() {
       ArrayList<State> nextTileState = new ArrayList<>();

       Move moveUp = new Move(emptyR - 1, emptyC); 
       if (isValidMove(moveUp, board)){
           State nextState = new State(this);
           makeMove(nextState, moveUp, nextState.emptyR, nextState.emptyC);
           nextState.setParent(this);
           if (!nextState.equals(nextState.parent)){
               nextTileState.add(nextState);
           }
       }

       Move moveDown = new Move(emptyR + 1, emptyC);
       if (isValidMove(moveDown, board)){
           State nextState = new State(this);
           makeMove(nextState, moveDown, nextState.emptyR, nextState.emptyC);
           nextState.setParent(this);
           if (!nextState.equals(nextState.parent)){
               nextTileState.add(nextState);
           }
       }

       Move moveRight = new Move(emptyR, emptyC + 1);
       if (isValidMove(moveRight, board)){
           State nextState = new State(this);
           makeMove(nextState, moveRight, nextState.emptyR, nextState.emptyC);
           nextState.setParent(this);
           if (!nextState.equals(nextState.parent)){
               nextTileState.add(nextState);
           }
       }

       Move moveLeft = new Move(emptyR, emptyC - 1);
       if (isValidMove(moveLeft, board)){
           State nextState = new State(this);
           makeMove(nextState, moveLeft, nextState.emptyR, nextState.emptyC);
           nextState.setParent(this);
           if (!nextState.equals(nextState.parent)){
               nextTileState.add(nextState);
           }
       }

       return nextTileState;
   }


   public void makeMove(State nextState, Move move, int emptyR, int emptyC){
       int[][] arr = nextState.getBoard();
       int temp = arr[move.getRowMove()][move.getColMove()];
       arr[move.getRowMove()][move.getColMove()] = arr[emptyR][emptyC];
       nextState.setEmptyR(move.getRowMove());
       nextState.setEmptyC(move.getColMove());
       arr[emptyR][emptyC] = temp;
   }

   public boolean isValidMove(Move move, int[][] arr){
       if (move.getRowMove() < arr.length && move.getColMove() < arr[0].length && move.getRowMove() >= 0 && move.getColMove() >= 0){
           return true;
       }
       return false;
   }


   public boolean equals(State other) {   // return true if current state is same as other
       int[][] otherArr = other.getBoard();
       if (otherArr.length != board.length || otherArr[0].length != board[0].length) {
           return false;
       }
       for (int r = 0; r < board.length; r++) {
           for (int c = 0; c < board.length; c++) {
               if (otherArr[r][c] != board[r][c]){
                   return false;
               }
           }
       }
       return true;
   }

   public State getParent() {
       return parent;
   }

   public boolean equals(int[][] otherArr){

       if (otherArr.length != board.length || otherArr[0].length != board[0].length) {
           return false;
       }
       for (int r = 0; r < board.length; r++) {
           for (int c = 0; c < board.length; c++) {
               if (otherArr[r][c] != board[r][c]){
                   return false;
               }
           }
       }
       return true;
   }

   public int[][] copy(int[][] arr) {
       int[][] copyArr = new int[arr.length][arr[0].length];

       for (int r = 0; r < arr.length; r++) {
           for (int c = 0; c < arr[0].length; c++) {
               copyArr[r][c] = arr[r][c];
           }
       }
       return copyArr;
   }


   private void setEmptyLocation() {
       for (int r = 0; r < board.length; r++) {
           for (int c = 0; c < board[r].length; c++) {
               if (board[r][c] == 0) {
                   this.emptyR = r;
                   this.emptyC = c;
               }
           }
       }
   }


   public String toString() {       // what the current state should look like when
       String out = "";
       for (int r = 0; r < board.length; r++) {
           for (int c = 0; c < board[r].length; c++) {
               out += "[ " + board[r][c] + "] ";
           }
           out += "\n";
       }
       return out;
   }

}
