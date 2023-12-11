/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
//import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Monster extends LabyrinthCharacter{
    private static final int INITIAL_HEALTH =5;
    
    public Monster(String _name, float _intelligence, float _strength){ 
        super(_name, _intelligence, _strength, INITIAL_HEALTH);
    }
    
    @Override
    public float attack(){
        return Dice.intensity(getStrength());
    }
    
    @Override
    public boolean defend (float receivedAttack){
        boolean isDead = dead();
        if(!isDead){
            float defensiveEnergy = Dice.intensity(getIntelligence());
            if(defensiveEnergy<receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }   
    
    public String toString(){
        return "\n" + super.toString();
    }
    
    
    
}
