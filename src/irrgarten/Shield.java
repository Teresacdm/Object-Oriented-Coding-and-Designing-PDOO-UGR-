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
public class Shield {
    private float protection;
    private int uses;
    
    //Añade un constructor y como parámetros un valor para cada uno de esos atributos.
    
    public Shield(float _protection, int _uses){
        protection=_protection;
        uses=_uses;
    }
    
    //Añade un método de instancia público sin parámetros llamado protect que devuelva un número en
    //coma flotante representando la intensidad de la defensa del jugador. Si el escudo aún tiene usos
    //disponibles (uses > 0), se decrementa ese valor y se devuelve el valor de protection. En otro caso el
    //método devuelve 0.
    
    public float protect(){
        if (uses>0){
            uses--;
            return protection;
        }
        else{
            return 0;
        }
    }
   
    //Añade el método public String toString() en Java 
    //que devuelva un representación en forma del cadena de caracteres del estado interno del objeto. Así
    //devolverá “S[3.0, 4]” para un escudo que proporciona un nivel de protección de 3 unidades y que
    //aún puede ser usado 4 veces.
    
    public String toString(){
        return "S["+protection+","+uses+"]";
    }
    
    //boolean discard(): este método produce el resultado delegando en el método discardElement del
    //dado pasando al mismo la cantidad de usos restantes como parámetro. Este método implementa la
    //decisión de si un arma o escudo debe ser descartado.
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
