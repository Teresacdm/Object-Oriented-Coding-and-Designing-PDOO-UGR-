/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;
//package Dice;
//import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Monster extends Dice{
    private static final int INITIAL_HEALTH =5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster(String _name, float _intelligence, float _strength){ //se puede poder el mismo nombre que las variables privadas
        name=_name;
        intelligence = _intelligence;
        strength=_strength;
    }
    
    public boolean dead(){
        if (health<=0)
            return true;
        else
            return false;
    }
    
    public float attack(){
        return intensity(strength);
    }
    
    
    public boolean defend (float receivedAttack){
        throw new UnsupportedOperationException();
    }

    public void setPos(int _row, int _col){
        row=_row; //mejor utilizar this?
        col=_col;char[][] tabla_estados;
    }
    
    public String toString(){
        return "Nombre: " + name + "\nInteligencia: " + intelligence +
                "\nFuerza: " + strength + "\nSalud: " + health + "\nFila: " +
                row + "\nColumna: " + col;
    }
    
    private void gotWounded(){
        health--;
    }
    
}
