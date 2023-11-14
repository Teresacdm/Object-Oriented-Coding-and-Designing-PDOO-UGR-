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
        labyrinth= new Labyrinth(6,4,3, 3);
        currentPlayerIndex=Dice.whoStarts(nplayers);
        currentPlayer=players.get(currentPlayerIndex);
        configureLabyrinth();
        labyrinth.spreadPLayers(players);
    }
    
    public boolean finished (){
        return labyrinth.haveaAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        boolean dead=currentPlayer.dead();
        String log = "";
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            if(direction!=preferredDirection){
                logPlayerNoOrders();
            }
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            if(monster==null){
                logNoMonster();
            }
            else{
                GameCharacter winner=combat(monster);
                manageReward(winner);
            }
        }
        else{
            manageResurrection();
        }
        boolean endGame = finished();
        if(!endGame)
            nextPlayer();
        return endGame;
    }
    
    public GameState getGameState(){
        GameState gst;
        //cuando lo comprobemos, ver si nos gusta el formato con el que imprime toString directamente aplicado a un array
        gst = new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(), log);
        return gst;
    }
    
    private void configureLabyrinth(){
        Orientation v = Orientation.VERTICAL;
        Orientation h = Orientation.HORIZONTAL;
        labyrinth.addBlock(v, 0, 1, 1);
        labyrinth.addBlock(v, 0, 3, 1);
        labyrinth.addBlock(v, 2, 3, 1);
        labyrinth.addBlock(v, 2, 1, 2);
        labyrinth.addBlock(h, 4, 2, 2);
        labyrinth.addBlock(v, 5, 0, 1);
        Monster monster1 = new Monster ("Marisol", Dice.randomIntelligence(), Dice.randomStrength());
        labyrinth.addMonster(3, 1, monster1);
        Monster monster2 = new Monster ("Terraluna", Dice.randomIntelligence(), Dice.randomStrength());
        labyrinth.addMonster(5, 2, monster2);
        
    }
    
    private void nextPlayer(){
        currentPlayerIndex++;
        currentPlayer=players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList <Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        int rounds=0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        //Es un while porque en el diagrama de secuencia no aparece ni un paratodos ni un siguiente
        while((!lose) && (rounds < MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            if(!lose){
                playerAttack=currentPlayer.attack();
                winner = GameCharacter.PLAYER; //este es el orden?
                lose = monster.defend(playerAttack);
            }
        }
        logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if(winner==GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        else{
            logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else{
            logPlayerSkipTurn();
        }
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
    
    private void logPlayerSkipTurn(){
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
