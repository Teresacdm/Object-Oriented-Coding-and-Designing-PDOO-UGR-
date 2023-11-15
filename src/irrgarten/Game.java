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
public class Game {
    private static final int MAX_ROUNDS=10;
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private ArrayList <Player> players;
    private ArrayList <Monster> monsters;
    private Labyrinth labyrinth;
    
    public Game(int nplayers){
        players= new ArrayList<>();
        for (int i=0; i<nplayers; i++){  
            char num = Integer.toString(i).charAt(0);
            Player p = new Player( num , Dice.randomIntelligence(), Dice.randomStrength());
            players.add(p);
        }
        currentPlayerIndex=Dice.whoStarts(nplayers);
        currentPlayer=players.get(currentPlayerIndex);
        log="Comienza la partida!\n";
        monsters=new ArrayList <>();
        labyrinth= new Labyrinth(6,4,3, 3);
        configureLabyrinth();
        labyrinth.spreadPLayers(players);
    }
    
    public boolean finished (){
        return labyrinth.haveaAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        boolean dead=currentPlayer.dead();
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
        GameState gameState;
        gameState = new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, finished(), log);
        return gameState;
    }
    
    //Configura el laberinto añadiendo bloques de obstáculos y monstruos.
    //Los monstruos, además de en el laberinto son guardados en el contenedor
    //propio de esta clase para este tipo de objetos.
    private void configureLabyrinth(){
        Orientation v = Orientation.VERTICAL;
        Orientation h = Orientation.HORIZONTAL;
        labyrinth.addBlock(v, 0, 1, 1);
        labyrinth.addBlock(v, 0, 3, 1);
        labyrinth.addBlock(v, 2, 3, 1);
        labyrinth.addBlock(v, 2, 0, 2);
        labyrinth.addBlock(h, 4, 2, 2);
        labyrinth.addBlock(v, 5, 0, 1);
        Monster monster1 = new Monster ("Marisol", Dice.randomIntelligence(), Dice.randomStrength());
        labyrinth.addMonster(3, 1, monster1);
        monsters.add(monster1);
        Monster monster2 = new Monster ("Terraluna", Dice.randomIntelligence(), Dice.randomStrength());
        labyrinth.addMonster(5, 2, monster2);
        monsters.add(monster2);
        
    }
    
    //Actualiza los dos atributos que indican el jugador (current*) con el turno pasando al siguiente jugador
    private void nextPlayer(){
        if(currentPlayerIndex!=players.size()-1)
            currentPlayerIndex++;
        else
            currentPlayerIndex=0;
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
                winner = GameCharacter.PLAYER; 
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
        log+="El jugador " + currentPlayer.getNumber() + " ha ganado el combate";
        log+="\n";
    }
    
    private void logMonsterWon(){
        log+="El monstruo ha ganado el combate";
        log+="\n";
    }
    
    private void logResurrected(){
        log+="El jugador " + currentPlayer.getNumber() + " ha resucitado";
        log+="\n";
    }
    
    private void logPlayerSkipTurn(){
        log+="El jugador " + currentPlayer.getNumber() + " ha perdido el turno por estar muerto";
        log+="\n";
    }
    
    private void logPlayerNoOrders(){
        log+="El jugador " + currentPlayer.getNumber() + " no ha seguido las instrucciones del jugador humano";
        log+="\n";
    }
    
    private void logNoMonster(){
        log+="El jugador " + currentPlayer.getNumber() + " se ha movido a una celda vacía o no le ha sido posible moverse";
        log+="\n";
    }
    
    private void logRounds(int rounds, int max){
        log+="Se han producido " + rounds + " rondas de " + max + "en el combate";
        log+="\n";
    }
}
