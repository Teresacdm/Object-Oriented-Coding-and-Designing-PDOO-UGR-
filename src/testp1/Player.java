/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author usuario
 */
public class Player extends Shield, Weapon {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH =10;
    private static final int HITS2LOSE = 3;
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;Monster
    private int col;
    private int consecutiveHits = 0;
    
    //ArrayList Weapon lista_armas;
    //ArrayList Shield lista_esMonstercudos;
    
    public Player (char _number, float _intelligence, float _strength){
        name = "Player #" + number;
        number=_number;
        intelligence=_intelligence;
        strength=_strength;
    }
    
    public void resurrect(){
        health = INITIAL_HEALTH;
        resetHits();
        //lista_armas.clear();
        //lista_escudos.clear()
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
        if (health <= 0)
            return true;
        else
            return false;
    }
    
    public Directions move (Directions direction, ArrayList <Directions> validMoves){
        throw new UnsupportedOperationException();
    }
    
    public float attack(){
        return strength + sumWeapons();
    }
    
    public boolean defend (float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        throw new UnsupportedOperationException();
    }
    
    public String toString(){
        return "Nombre: " + name + "\nInteligencia: " + intelligence +
                "\nFuerza: " + strength + "\nSalud: " + health + "\nFila: " +
                row + "\nColumna: " + col + "\nGolpes Consecutivosa: " + consecutiveHits;
    }
    
    public void receiveWeapon(){
        throw new UnsupportedOperationException();
    }
    
    public void receiveShield(){
        throw new UnsupportedOperationException();
    }
    
    public Weapon newWeapon(){
        arma = new Weapon(weaponPower(), usesLeft());
    }
    
    public Shield newShield(){
        escudo = new Shield(shieldPower(), usesLeft());
    }
    
    public float sumWeapons(){
        float total=0;
        for(int i=0; i<lista_armas.size(); i++){
            total+=lista_armas[i].attack();
        }
        
        return total;
    }
    
    public float sumShields(){
        float total=0;
        for(int i=0; i<lista_escudos.size(); i++){
            total+=lista_escudos[i].protect();
        }
        
        return total;
    }
    
    public float defensiveEnergy(){
        return intelligence + sumShields();
    }
    
    public boolean manageHit(float receivedAttack){
        throw new UnsupportedOperationException();
    }
    
    public void resetHits(){
        consecutiveHits=0;
    }
    
    public void gotWounded(){
        health--;
    }
    
    public void incConsecutiveHits(){
        consecutiveHits++;
    }
}
