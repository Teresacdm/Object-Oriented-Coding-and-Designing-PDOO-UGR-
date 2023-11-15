/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Labyrinth{
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRows;
    private int exitCol;
    
    private Player [][] players; //Square?????????
    private Monster [][] monsters;
    private char[][] labyrinth;
    
    public Labyrinth (int _nRows, int _nCols, int _exitRows, int _exitCol){
        nRows=_nRows;
        nCols=_nCols;
        exitRows=_exitRows;
        exitCol=_exitCol;
        labyrinth = new char[nRows][nCols];
        for(int i=0; i<nRows; i++){
            for(int j=0; j<nCols; j++){
                labyrinth[i][j]=EMPTY_CHAR;
            }
        }
    }
    
    public void spreadPLayers(ArrayList <Player> players){
        int[] pos = new int[2];
        for(Player p : players){
            pos = randomEmptyPos();
            Monster m = putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    public boolean haveaAWinner(){
       for(int i=0; i<nRows; i++){
           for(int j=0; j<nCols; j++){
               if(players[i][j].getRow()==0 && players[i][j].getCol()==0)
                   return true;
           }
       }
       return false;
    }
    
    public String toString(){
        return "Número de filas: " + nRows + "\nNúmero de columnas: " + nCols + 
                "\nNúmero de Filas de Salida: " + exitRows + "\nNúmero de Columnas de Salida: " + exitCol;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            monster.setPos(row, col);
            monsters[row][col]=monster;
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }
    
    //startRow y startCol indican la fila y columna desde donde se empieza a poner el obstáculo
    //la orientación indica si vamos a prolongar nuestro obstáculo hacia izq/dcha o arriba/abajo
    //y length cuánto vamos a prolongar ese obstáculo
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow, incCol;
        if(orientation==Orientation.VERTICAL){
            incRow=1;
            incCol=0;
        }
        else{
            incRow=0;
            incCol=1;
        }
        int row=startRow;
        int col=startCol;
        while((posOK(row,col))&&(emptyPos(row,col))&&(length>0)){
            labyrinth[row][col]=BLOCK_CHAR;
            length-=1;
            row+=incRow;
            col+=incCol;
        }
    }
    
    public ArrayList <Directions> validMoves(int row, int col){
       ArrayList <Directions> output = new ArrayList<>();
       if(canStepOn(row+1,col))
           output.add(Directions.DOWN);
       if(canStepOn(row-1,col))
           output.add(Directions.UP);
       if(canStepOn(row,col+1))
           output.add(Directions.RIGHT);
       if(canStepOn(row,col-1))
           output.add(Directions.LEFT);
       return output;
    }
    
    private boolean posOK(int row, int col){
        if (row<=nRows && col<=nCols)
            return true;
        else
            return false;                    
    }
    
    private boolean emptyPos(int row, int col){
        if(labyrinth[row][col]==EMPTY_CHAR)
            return true;
        else
            return false;
    }
    
    private boolean monsterPos(int row, int col){
        if(labyrinth[row][col]==MONSTER_CHAR)
            return true;
        else
            return false;
    }
    
    private boolean exitPos(int row, int col){
        if(labyrinth[row][col]==EXIT_CHAR)
            return true;
        else
            return false;
    }
    
    private boolean combatPos(int row, int col){
        if(labyrinth[row][col]==COMBAT_CHAR)
            return true;
        else
            return false;
    }
    
    private boolean canStepOn(int row, int col){
        if(posOK(row, col)){
            if(emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row, col))
                labyrinth[row][col]=MONSTER_CHAR;
            else
                labyrinth[row][col]=EMPTY_CHAR;
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int newcol=col;
        int newrow=row;
        int[] newpos = new int[2];
        switch (direction){
            case LEFT:
                newcol--;
                break;
            case RIGHT:
                newcol++;
                break;
            case UP:
                newrow--;
                break;
            case DOWN:
                newrow++;
                break;
        }
        newpos[0]=newrow;
        newpos[1]=newcol;
        return newpos;
    }
    
    private int [] randomEmptyPos(){
        int[] newpos = new int[2];
        int newrow, newcol;
        do{
            newrow=Dice.randomPos(nRows);
            newcol=Dice.randomPos(nCols);
        } while(!emptyPos(newrow, newcol));
        
        newpos[0]=newrow;
        newpos[1]=newcol;
        
        return newpos;            
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output;
        output =null;
        if(canStepOn(row, col)){
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol];
                if(p==player){
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol]=null;
                }
            }
            boolean monsterPos = monsterPos(row, col);
            if(monsterPos){
                labyrinth[row][col]=COMBAT_CHAR;
                output=monsters[row][col];
            }
            else{
                char number = (char) player.getNumber();
                labyrinth[row][col]=number;
            }
            players[row][col]=player;
            player.setPos(row, col);
        }
        return output;
    }
}

