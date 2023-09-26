/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package testp1;

/**
 *
 * @author usuario
 */
public class Weapon {
    private float power;
    private int uses;
    
    Weapon(float _power, int _uses){
        power=_power;
        uses=_uses;
    }
    
    public float attack(){
        if (uses>0){
            uses--;
            return power;
        }
        else{
            return 0;
        }
    }
    
    public String toString(){
        return "W["+power+","+uses+"]";
    }
    
    public boolean discard(){
        Dice d = new Dice();
        return d.discardElement(uses);
    }
}
