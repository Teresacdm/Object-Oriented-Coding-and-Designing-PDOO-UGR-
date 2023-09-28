/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testp1;

/**
 *
 * @author usuario
 */
public class Shield {
    private float protection;
    private int uses;
    
    Shield(float _protection, int _uses){
        protection=_protection;
        uses=_uses;
    }
    
    public float protect(){
        if (uses>0){
            uses--;
            return protection;
        }
        else{
            return 0;
        }
    }
   
    public String toString(){
        return "W["+protection+","+uses+"]";
    }
    
    public boolean discard(){
        Dice d = new Dice();
        return d.discardElement(uses);
    }
}
