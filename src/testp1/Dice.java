/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;

import java.util.Random;

/**
 *
 * @author usuario
 */
public class Dice {
    private static final int MAX_USES = 5; //(número máximo de usos de armas y escudos)
    private static final float MAX_INTELLIGENCE = 10.0f; //(valor máximo para la inteligencia de jugadores y monstruos)
    private static final float MAX_STRENGTH = 10.0f; //(valor máximo para la fuerza de jugadores y monstruos)
    private static final float RESURRECT_PROB = 0.3f; //(probabilidad de que un jugador sea resucitado en cada turno)
    private static final int WEAPONS_REWARD = 2; //(numero máximo de armas recibidas al ganar un combate)
    private static final int SHIELDS_REWARD = 3; //(numero máximo de escudos recibidos al ganar un combate)
    private static final int HEALTH_REWARD = 5; //(numero máximo de unidades de salud recibidas al ganar un combate)
    private static final float MAX_ATTACK = 3f; //(máxima potencia de las armas)
    private static final float MAX_SHIELD = 2f; //(máxima potencia de los escudos)

    private Random generator;
    
    Dice(){
        generator=new Random();
    }
    
    public int randomPos(int max){
        return generator.nextInt(max+1); //
    }
    
    public int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    public float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE;
    }
    
    public float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH;
    }
    
    public boolean resurrectPlayer(){
        double prob;
        prob=generator.nextDouble();
        if(prob<=0.3)
            return true;
        else
            return false;
    }
    
    public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1); //AÑADIR +1?
    }
    
    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    public float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    public float shieldPower(){
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    public int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    public float intensity(float competence){
        float f;
        double d;
        d=generator.nextDouble()*competence;
        f=(float)d;
        return f;
    }
    
    public boolean discardElement(int usesLeft){
        if(usesLeft==0){
            return true;
        }
        else{
            double d_usesLeft=usesLeft;
            double d_max_uses=MAX_USES;
            double porc=d_usesLeft/d_max_uses;
            porc=1/porc;
            double prob=generator.nextDouble();
            if(prob>=porc)
                return true;
            else
                return false;
        }
    }
}

