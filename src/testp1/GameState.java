/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;

/**
 *
 * @author usuario
 */

/*Esta clase permitirá, de forma muy sencilla, almacenar una representación del estado completo del
juego: el estado del laberinto, el estado de los jugadores, el estado de los monstruos, el índice del
jugador que tiene el turno, un indicador sobre si ya hay un ganador y un atributo adicional para
guardar en una cadena de caracteres eventos interesantes que hayan ocurrido desde el turno anterior.
*/

public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    //Crea un constructor para esta clase con un parámetro cara inicializar cada atributo.
    
    public GameState(String _lab, String _play, String _monst, int _currPlay, boolean _win, String _log){
        labyrinth=_lab;
        players=_play;
        monsters=_monst;
        currentPlayer=_currPlay;
        winner=_win;
        log=_log;
    }
    
    //Añade un consultor para cada atributo.
    
    public String GetLabyrinthv(){
        return labyrinth;
    }
    
    public String GetPlayers(){
        return players;
    }
    
    public String GetMonsters(){
        return monsters;
    }
    
    public int GetCurrentPlayer(){
        return currentPlayer;
    }
    
    public boolean GetWinner(){
        return winner;
    }
    
    public String GetLog(){
        return log;
    }
}
 
