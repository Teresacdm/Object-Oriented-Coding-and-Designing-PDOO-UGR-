/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public abstract class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect=effect;
        this.uses=uses;
    }
    
    protected float produceEffect(){
        if (uses>0){
            uses--;
            return effect;
        }
        else{
            return 0;
        }
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    public String toString(){
        return "["+effect+","+uses+"]";
    }
    
}
