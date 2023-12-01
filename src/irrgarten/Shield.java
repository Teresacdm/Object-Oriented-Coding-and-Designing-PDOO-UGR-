/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */

// Esta clase representa los escudos que utiliza el jugador cuando se defiende de un ataque de un monstruo.
public class Shield extends CombatElement{
    
    //Añade un constructor y como parámetros un valor para cada uno de esos atributos.
    
    public Shield(float _protection, int _uses){
        super(_protection, _uses);
    }
    
   
    //Añade el método public String toString() en Java 
    //que devuelva un representación en forma del cadena de caracteres del estado interno del objeto. Así
    //devolverá “S[3.0, 4]” para un escudo que proporciona un nivel de protección de 3 unidades y que
    //aún puede ser usado 4 veces.
    
    public String toString(){
        return ("S" + super.toString());
    }
    
}
