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
public class FuzzyPlayer extends Player{
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions dir=super.move(direction, validMoves);
        return Dice.nextStep(dir, validMoves, getIntelligence());
    }
    
    @Override
    public float attack(){
        return sumWeapons() + Dice.intensity(getStrength());
    }
    
    @Override
    protected float defensiveEnergy(){
        return sumShields() + Dice.intensity(getIntelligence());
    }
    
    public String toString(){
        return "\nFuzzy:" + super.toString();
    }
}
