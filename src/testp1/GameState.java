/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;

/**
 *
 * @author usuario
 */
public class GameState {
    private String labyrinthv;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    GameState(String _lab, String _play, String _monst, int _currPlay, boolean _win, String _log){
        labyrinthv=_lab;
        players=_play;
        monsters=_monst;
        currentPlayer=_currPlay;
        winner=_win;
        log=_log;
    }
    
    public String GetLabyrinthv(){
        return labyrinthv;
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
 
