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
    private int exitRow;
    private int exitCol;
    
    private Player [][] players; 
    private Monster [][] monsters;
    private char[][] labyrinth;
    
   public Labyrinth (int _nRows, int _nCols, int _exitRow, int _exitCol){
        nRows=_nRows;
        nCols=_nCols;
        exitRow=_exitRow;
        exitCol=_exitCol;
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];
        labyrinth = new char[nRows][nCols];
        for(int i=0; i<nRows; i++){
            for(int j=0; j<nCols; j++){
                labyrinth[i][j]=EMPTY_CHAR;
            }
        }
        labyrinth[exitRow][exitCol]=EXIT_CHAR;
    }
    
    public void spreadPLayers(ArrayList <Player> players){
        int[] pos = new int[2];
        for(Player p : players){
            pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    public boolean haveaAWinner(){
       return (players[exitRow][exitCol]!=null);
    }
    
    public String toString(){
        return "Estado del laberinto:\nNúmero de filas: " + nRows + "\nNúmero de columnas: " + nCols + 
                "\nFila de Salida: " + exitRow + "\nColumna de Salida: " + exitCol;
    }
    
    //Si la posición suministrada está dentro del tablero y
    //está vacía, anota en el laberinto la presencia de un monstruo, guarda la referencia del monstruo en el
    //atributo contenedor adecuado e indica al monstruo cual es su posición actual (setPos).
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            labyrinth[row][col]=MONSTER_CHAR;
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
        return (row >= 0 && row<nRows && col >= 0 && col<nCols);                   
    }
    
    private boolean emptyPos(int row, int col){
        return(labyrinth[row][col]==EMPTY_CHAR);
    }
    
    private boolean monsterPos(int row, int col){
        return(labyrinth[row][col]==MONSTER_CHAR);
    }
    
    private boolean exitPos(int row, int col){
        return(labyrinth[row][col]==EXIT_CHAR);
    }
    
    private boolean combatPos(int row, int col){
        return(labyrinth[row][col]==COMBAT_CHAR);
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
    
    //Este método sólo realiza su función si la posición suministrada está
    //dentro del laberinto. Si es el caso, si en esa posición el laberinto estaba indicando el estado de
    //combate, el estado de esa casilla del laberinto pasa a indicar que simplemente hay un monstruo. En
    //otro caso, el estado de esa casilla del laberinto pasa a indicar que está vacía. Este método es llamado
    //cuando un jugador abandona una casilla y se encarga de dejar la casilla que se abandona en el
    //estado correcto.
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row, col))
                labyrinth[row][col]=MONSTER_CHAR;
            else
                labyrinth[row][col]=EMPTY_CHAR;
        }
    }
    
    //Este método calcula la posición del laberinto a la que se llegaría si desde la posición suministrada
    //se avanza en la dirección pasada como parámetro. No es necesario realizar comprobaciones relativas a
    //no generar posiciones fuera del laberinto.
    private int[] dir2Pos(int row, int col, Directions direction){
        int newcol=col;
        int newrow=row;
        int[] newpos = new int[2];
        switch (direction){
            case LEFT:
                if(canStepOn(newrow,(col-1)))
                    newcol--;
                break;
            case RIGHT:
                if(canStepOn(newrow,(col+1)))
                    newcol++;
                break;
            case UP:
                if(canStepOn((row-1),newcol))
                    newrow--;
                break;
            case DOWN:
                if(canStepOn((row+1),newcol))
                    newrow++;
                break;
        }
        newpos[0]=newrow;
        newpos[1]=newcol;
        return newpos;
    }
    
    //Utilizando el dado, genera una posición aleatoria en el laberinto (fila y columna)
    //asegurando que esta esté vacía. Genera internamente posiciones hasta que se cumple esta
    //restricción y una vez generada se devuelve. Si no hay posiciones vacías se producirá un bucle infinito.
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
        if(canStepOn(row, col)){    // el jugador puede avanzar hacia esa casilla (destino)
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol]; // obtenemos el jugador que hay en la posición origen
                if(p==player){  // nos aseguramos que el jugador que hay es el mismo del parámetro con el que se ha llamado al método
                    updateOldPos(oldRow, oldCol);// El jugador se va a ir de ese 'origen' debemos atualizar las matrices para que quede reflejado
                    players[oldRow][oldCol]=null;
                }
            }
            boolean monsterPos = monsterPos(row, col);// Miramos si en el 'destino' hay un monstruo
            if(monsterPos){ // en caso de ser así
                labyrinth[row][col]=COMBAT_CHAR;    // marcamos la casilla como  combate (jugador y monstruo en la misma casilla)
                output=monsters[row][col];          // recuperamos una referencia al monstruo concreto para devolverlo
            }
            else{   // en 'destino' no hay monstruo
                char number = (char) player.getNumber();    // obtenemos el número del jugador para poner dicho número en esa casilla del laberinto
                labyrinth[row][col]=number;
            }
            players[row][col]=player;   // actualizamos el estado de la matriz de players y el estado del propio player
            player.setPos(row, col);
        }
        return output;  // devolvemos (el monstruo si había, o null si no  hay monstruo)
    }
}

