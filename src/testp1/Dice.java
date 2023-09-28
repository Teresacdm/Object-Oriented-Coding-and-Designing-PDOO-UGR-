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

// Esta clase tiene la responsabilidad de tomar todas las decisiones que dependen del azar en el juego.
// Es como una especie de dado, pero algo más sofisticado, ya que no proporciona simplemente un
//número del 1 al 6, sino decisiones concretas en base a una serie de probabilidades establecidas.

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

    // Añade otro atributo de clase privado llamado generator inicializado a una instancia de la clase
    //Random (existe en ambos lenguajes). Todos los números aleatorios se generarán usando métodos de
    //instancia de esta clase.
    
    private Random generator;
    
    public Dice(){
        generator=new Random();
    }
    
    //int randomPos(int max): devuelve un número de fila o columna aleatoria siendo el valor del
    //parámetro el número de filas o columnas del tablero. La fila y la columna de menor valor tienen
    //como índice el número cero.
    
    public int randomPos(int max){
        return generator.nextInt(max+1);
    }
    
    //int whoStarts(int nplayers): devuelve el índice del jugador que comenzará la partida. El parámetro
    //representa el número de jugadores en la partida. Los jugadores se numeran comenzando con el
    //número 0.
    
    public int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    //float randomIntelligence(): devuelve un valor aleatorio de inteligencia del intervalo [0,MAX_INTELLIGENCE[
    
    public float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE;
    }
    
    //float randomStrength():devuelve un valor aleatorio de fuerza del intervalo [0, MAX_STRENGTH[
    
    public float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH;
    }
    
    //boolean resurrectPlayer(): indica si un jugador muerto debe ser resucitado o no.
    
    public boolean resurrectPlayer(){
        float prob;
        prob=generator.nextFloat();
        if(prob<=RESURRECT_PROB)
            return true;
        else
            return false;
    }
    
    //int weaponsReward(): indica la cantidad de armas que recibirá el jugador por ganar el combate.
    //Será un número aleatorio desde 0 (inclusive) que nunca debe superar el máximo indicado en la
    //definición de los atributos de clase. Es decir, el número aleatorio debe estar en el intervalo cerrado
    //[0,WEAPONS_REWARD].
    
    public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD+1); //AÑADIR +1?
    }
    
    //int shieldsReward(): indica la cantidad de escudos que recibirá el jugador por ganar el combate.
    //Será un número aleatorio desde 0 (inclusive) que nunca debe superar el máximo indicado en la
    //definición de los atributos de clase.
    
    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    //int healthReward(): indica la cantidad de unidades de salud que recibirá el jugador por ganar el
    //combate. Será un número aleatorio desde 0 (inclusive) que nunca debe superar el máximo indicado
    //en la definición de los atributos de clase.
    
    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    //float weaponPower(): devuelve un valor aleatorio en el intervalo [0, MAX_ATTACK]
    
    public float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    //float shieldPower(): devuelve un valor aleatorio en el intervalo [0, MAX_SHIELD[
    
    public float shieldPower(){
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    //int usesLeft(): devuelve el número de usos que se asignará a un arma o escudo. Será un número
    //aleatorio desde 0 (inclusive) que nunca debe superar el máximo indicado en la definición de los
    //atributos de clase.
    
    public int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    //float intensity(float competence): devuelve la cantidad de competencia aplicada. Será un valor
    //aleatorio del intervalo [0, competence[
    
    public float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    //boolean discardElement(int usesLeft): este método devuelve true con una probabilidad
    //inversamente proporcional a lo cercano que esté el parámetro del número máximo de usos que
    //puede tener un arma o escudo. Como casos extremos, si el número de usos es el máximo devolverá
    //false y si es 0 devolverá true. Es decir, las armas o escudos con más usos posibles es menos
    //probable que sean descartados.
    
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

