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

public class Weapon extends CombatElement{
    
    //Añade un constructor y como parámetros un valor para cada uno de esos atributos.
    //Llámamos al constructor de la clase CombatElement
    
    public Weapon(float _power, int _uses){
        super(_power, _uses);
    }
    
    
    //Añade el método public String toString() en Java
    //que devuelva un representación en forma del cadena de caracteres del estado interno del objeto. Así
    //devolverá “W[2.0, 5]” para un arma con una potencia de disparo de 2 unidades y que aún puede ser
    //usada 5 veces.
    
    public String toString(){
        return ("W" + super.toString());
    }
}
