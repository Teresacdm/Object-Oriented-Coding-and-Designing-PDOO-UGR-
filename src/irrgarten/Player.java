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
public class Player{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH =10;
    private static final int HITS2LOSE = 3;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits = 0;
    
    private ArrayList <Weapon> weapons;
    private ArrayList <Shield> shields;
    
    public Player (char _number, float _intelligence, float _strength){
        number= _number;
        name = "Player #" + number;
        intelligence=_intelligence;
        strength=_strength;
        health=INITIAL_HEALTH;
        weapons= new ArrayList <>();
        shields= new ArrayList <>();
    }
    
    //Este método realiza las tareas asociadas a la resurrección. Debe hacer que las listas
    //de armas y escudos sean listas vacías, que el nivel de salud sea el determinado para el inicio del
    //juego y el número consecutivo de impactos cero.
    public void resurrect(){
        health = INITIAL_HEALTH;
        resetHits();
        weapons.clear();
        shields.clear();
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public int getNumber(){
        return number;
    }
    
    public void setPos(int _row, int _col){
        row=_row;
        col=_col;
    }
    
    public boolean dead(){
        return (health <= 0);
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
    public float attack(){
        return strength + sumWeapons();
    }
    
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
        health += extraHealth;
    }
    
    public String toString(){
        return "Estado del jugador:\nNombre: " + name + "\nNúmero: " + number + "\nInteligencia: " + intelligence +
                "\nFuerza: " + strength + "\nSalud: " + health + "\nFila: " +
                row + "\nColumna: " + col + "\nGolpes Consecutivos: " + consecutiveHits;
    }
    
    private void receiveWeapon(Weapon w){
        for(Weapon wi: weapons){
            boolean discard = wi.discard();
            if(discard){
                weapons.remove(wi);
            }
        }
        int size = weapons.size();
        if(size<MAX_WEAPONS){
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s){
        for(Shield si: shields){
            boolean discard = si.discard();
            if(discard){
                shields.remove(si);
            }
        }
        int size = shields.size();
        if(size<MAX_SHIELDS){
            shields.add(s);
        }
    }
    
    private Weapon newWeapon(){
        Weapon arma = new Weapon(Dice.weaponPower(), Dice.usesLeft());
        return arma;
    }
    
    private Shield newShield(){
        Shield escudo = new Shield(Dice.shieldPower(), Dice.usesLeft());
        return escudo;
    }
    
    private float sumWeapons(){
        float total=0;
        for(int i=0; i<weapons.size(); i++){
            total+=weapons.get(i).attack();
        }
        return total;
    }
    
    private float sumShields(){
        float total=0;
        for(int i=0; i<shields.size(); i++){
            total+=shields.get(i).protect();
        }
        return total;
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();
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
    
    private void gotWounded(){
        health--;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
}
