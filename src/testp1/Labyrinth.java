/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Labyrinth extends Player{
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
    
    tabla_monsters = new Monster[nRows][nCols];
    Player[][] tabla_players;
    tabla_players = new Player[nRows][nCols];
    char[][] tabla_estados;
    char[][] tabla_estados = new char[nRows][nCols];
    
    public Labyrinth (int _nRows, int _nCols, int _exitRows, int _exitCol){
        nRows=_nRows;
        nCols=_nCols;
        exitRows=_exitRows;
        exitCol=_exitCol;
    }
    
    public void spreadPLayers(ArrayList <Player>ArrayList <Player> players){
        throw new UnsupportedOperationException();
    }
    
    public boolean haveaAWinner(){
       for(int i=0; i<nRows; i++){
           for(int j=0; j<nCols; j++){
               if(tabla_players[i][j].getRow()==0 && tabla_players[i][j].getCol()==0)
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
            tabla_monsters[row][col]=monster;
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    public ArrayList <Directions> validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    public boolean posOK(int row, int col){
        if (row<=nRows && col<=nCols)
            return true;
        else
            return false;                    
    }
    
    public boolean emptyPos(int row, int col){
        if(tabla_estados[row][col]==EMPTY_CHAR)
            return true;
        else
            return false;
    }
    
    public boolean monsterPos(int row, int col){
        if(tabla_estados[row][col]==MONSTER_CHAR)
            return true;
        else
            return false;
    }
    
    public boolean exitPos(int row, int col){
        if(tabla_estados[row][col]==EXIT_CHAR)
            return true;
        else
            return false;
    }
    
    public boolean combatPos(int row, int col){
        if(tabla_estados[row][col]==COMBAT_CHAR)
            return true;
        else
            return false;
    }
    
    public boolean canStepOn(int row, int col){
        if(posOK(row, col)){
            if(emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col))
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row, col))
                tabla_estados[row][col]=MONSTER_CHAR;
            else
                tabla_estados[row][col]=EMPTY_CHAR;
        }
    }
    
    public int[] dir2Pos(int row, int col, Directions direction){
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
    
    public int [] randomEmptyPos(){
        //hay que crear una instancia de Dice?
        int[] newpos = new int[2];
        int newrow, newcol;
        do{
            newrow=randomPos(nRows);
            newcol=randomPos(nCols);
        } while(!emptyPos(newrow, newcol));
        
        newpos[0]=newrow;
        newpos[1]=newcol;
        
        return newpos;            
    }
    
    public Monster putPlayer2D(int oldRow, int oldCold, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }
}
