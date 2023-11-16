/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package irrgarten;

/**
 *
 * @author usuario
 */

//Esta clase representa las armas que utiliza el jugador en los ataques durante los combates.

public class Weapon {
    private float power;
    private int uses;
    
    //Añade un constructor y como parámetros un valor para cada uno de esos atributos.
    
    public Weapon(float _power, int _uses){
        power=_power;
        uses=_uses;
    }
    //Añade un método de instancia público sin parámetros llamado attack que devuelva un número en
    //coma flotante representando la intensidad del ataque del jugador. Si el arma aún tiene usos
    //disponibles (uses > 0), se decrementa ese valor y se devuelve el valor de power. En otro caso el
    //método devuelve 0.
   
    public float attack(){
        if (uses>0){
            uses--;
            return power;
        }
        else{
            return 0;
        }
    }
    
    //Añade el método public String toString() en Java
    //que devuelva un representación en forma del cadena de caracteres del estado interno del objeto. Así
    //devolverá “W[2.0, 5]” para un arma con una potencia de disparo de 2 unidades y que aún puede ser
    //usada 5 veces.
    
    public String toString(){
        return "W["+power+","+uses+"]";
    }
    
    //boolean discard(): este método produce el resultado delegando en el método discardElement del
    //dado pasando al mismo la cantidad de usos restantes como parámetro. Este método implementa la
    //decisión de si un arma o escudo debe ser descartado.
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
