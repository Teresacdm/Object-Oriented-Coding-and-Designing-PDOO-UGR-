/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter(String name, float intelligence, float strength, float health){
        this.name=name;
        this.intelligence=intelligence;
        this.strength=strength;
        this.health=health;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        this(other.name, other.intelligence, other.strength, other.health);
        this.row=other.row;
        this.col=other.col;
    }
    
    public boolean dead(){
        if (health<=0){
            return true;
        }
        else
            return false;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    protected float getIntelligence(){
        return intelligence;
    }
    
    protected float getStrength(){
        return strength;
    }
    
    protected float getHealth(){
        return health;
    }
    
    protected void setHealth(float health){
        this.health=health;
    }
    
    public void setPos(int row, int col){
        this.row=row; 
        this.col=col;
    }
    
    public String toString(){
        return "Nombre: " + name + ", Inteligencia: " + intelligence +
                ", Fuerza: " + strength + ", Salud: " + health + ", Fila: " +
                row + ", Columna: " + col;
    }
    
    protected void gotWounded(){
        health--;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);
    
}
