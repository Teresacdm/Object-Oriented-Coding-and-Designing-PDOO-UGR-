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
public class Game {
    private static final int MAX_ROUNDS=10;
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private ArrayList <Player> players;
    private ArrayList <Monster> monsters;
    private Labyrinth labyrinth;
    
    public Game(int nplayers){
        for (int i=0; i<nplayers; i++){
            players.add(new Player((char)i, Dice.randomIntelligence(), Dice.randomStrength()));
        }
        monsters=new ArrayList <>();
        labyrinth= new Labyrinth(10,10,9, 9);
        currentPlayerIndex=Dice.whoStarts(nplayers);
        currentPlayer=players.get(currentPlayerIndex);
        configureLabyrinth();
        labyrinth.spreadPLayers(players);
    }
    
    public boolean finished (){
        return labyrinth.haveaAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState(){
        GameState gst;
        //cuando lo comprobemos, ver si nos gusta el formato con el que imprime toString directamente aplicaco a un array
        gst = new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(), log);
        return gst;
    }
    
    private void configureLabyrinth(){
        //práctica 3, método addBlock de labyrinth
    }
    
    private void nextPlayer(){
        currentPlayerIndex++;
        currentPlayer=players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection(){
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon(){
        log+="El jugador" + currentPlayer.getNumber() + "ha ganado el combate";
        log+="\n";
    }
    
    private void logMonsterWon(){
        log+="El monstruo ha ganado el combate"; //Qué mosntruo???
        log+="\n";
    }
    
    private void logResurrected(){
        log+="El jugador" + currentPlayer.getNumber() + "ha resucitado";
        log+="\n";
    }
    
    private logPlayerSkipTurn(){
        log+="El jugador" + currentPlayer.getNumber() + "ha perdido el turno por estar muerto";
        log+="\n";
    }
    
    private void logPlayerNoOrders(){
        log+="El jugador" + currentPlayer.getNumber() + "no ha seguido las instrucciones del jugador humano";
        log+="\n";
    }
    
    private void logNoMonster(){
        log+="El jugador" + currentPlayer.getNumber() + "se ha movido a una celda vacía o no le ha sido posible moverse";
        log+="\n";
    }
    
    private void logRounds(int rounds, int max){
        log+="Se han producido " + rounds + " rondas de " + max + "en el combate";
        log+="\n";
    }
}
