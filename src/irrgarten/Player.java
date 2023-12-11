/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author usuario
 */
public class Player extends LabyrinthCharacter{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH =10;
    private static final int HITS2LOSE = 3;
    private char number;
    private int consecutiveHits = 0;
    
    private ArrayList <Weapon> weapons; //CardDeck?
    private ArrayList <Shield> shields;
    
    public Player (char _number, float _intelligence, float _strength){
        super("Player #"+_number, _intelligence, _strength, INITIAL_HEALTH);
        number=_number;
        weapons= new ArrayList <>();
        shields= new ArrayList <>();        
    }
    
    public Player (Player other){
        super(other);
    }
    
    //Este método realiza las tareas asociadas a la resurrección. Debe hacer que las listas
    //de armas y escudos sean listas vacías, que el nivel de salud sea el determinado para el inicio del
    //juego y el número consecutivo de impactos cero.
    public void resurrect(){
        setHealth(INITIAL_HEALTH);
        resetHits();
        weapons.clear();
        shields.clear();
    }
    
    
    public char getNumber(){
        return number;
    }

    
    public Directions move (Directions direction, ArrayList <Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        if((size>0)&&(!contained)){
            Directions firstElement=validMoves.get(0);
            return firstElement;
        }
        else
            return direction;
    }
    
    //Calcula la suma de la fuerza del jugador y la suma de lo aportado por sus armas
    @Override
    public float attack(){
        return getStrength() + sumWeapons();
    }
    
    @Override
    public boolean defend (float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){ //currentPlayer? (Diagrama de Secuencia)
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        for(int i=0; i<wReward; i++){
            Weapon wnew = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            receiveWeapon(wnew);
        }
        for(int i=0; i<sReward; i++){
            Shield snew = new Shield(Dice.shieldPower(), Dice.usesLeft());
            receiveShield(snew);
        }
        int extraHealth = Dice.healthReward();
        setHealth(getHealth()+extraHealth);
    }
    
    public String toString(){
        return "\nPlayer:" + super.toString();
    }
    
    private void receiveWeapon(Weapon w){
        for(int i=0; i<weapons.size();i++){
            boolean discard = weapons.get(i).discard();
            if(discard){
                weapons.remove(i);
            }
        }
        int size = weapons.size();
        if(size<MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s){
        for(int i=0; i<shields.size();i++){
            boolean discard = shields.get(i).discard();
            if(discard){
                shields.remove(i);
            }
        }
        int size = shields.size();
        if(size<MAX_SHIELDS){
            shields.add(s);
        }
    }
    
    /*private Weapon newWeapon(){ //llamas a carddeck
        Weapon arma = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return arma;
    }*/
    /*
    private Shield newShield(){
        Shield escudo = new Shield(Dice.shieldPower(), Dice.usesLeft());
        return escudo;
    }*/
    
    protected float sumWeapons(){
        float total=0;
        for(int i=0; i<weapons.size(); i++){
            total+=weapons.get(i).produceEffect();
        }
        return total;
    }
    
    protected float sumShields(){
        float total=0;
        for(int i=0; i<shields.size(); i++){
            total+=shields.get(i).produceEffect();
        }
        return total;
    }
    
    protected float defensiveEnergy(){
        return getIntelligence() + sumShields();
    }
    
    private boolean manageHit(float receivedAttack){
        boolean lose;
        float defense = defensiveEnergy();
        if(defense<receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }
        else
            resetHits();
        if((consecutiveHits==HITS2LOSE)||dead()){
            resetHits();
            lose=true;
        }
        else
            lose=false;
        return lose;
    }
    
    private void resetHits(){
        consecutiveHits=0;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}
