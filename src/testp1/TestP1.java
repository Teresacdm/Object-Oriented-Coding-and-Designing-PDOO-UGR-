
package testp1;


/**
 *
 * @author usuario
 */
public class TestP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Weapon w = new Weapon(30, 0);
        Shield s = new Shield(20, 3);
        Dice d = new Dice();
        //GameSate gs = new GameState("Laberinto", "Marisol", "Monstruo", "");
        System.out.println("Attack: " + w.attack());
        System.out.println(w.toString());
        System.out.println(w.discard());
        System.out.println("Protect: " + s.protect());
        System.out.println(s.toString());
        System.out.println(s.discard());
        System.out.println(Directions.DOWN);
        System.out.println(Orientation.HORIZONTAL);
        System.out.println(GameCharacter.MONSTER);
        System.out.println("Posicion Aleatoria: " + d.randomPos(5));
        System.out.println("Quien empieza: " + d.whoStarts(8));
        System.out.println("Inteligencia aleatoria: " + d.randomIntelligence());
        System.out.println("Fuerza aleatoria: " + d.randomStrength());
        System.out.println("Resurrección: " + d.resurrectPlayer());
        System.out.println("Recompensa de armas: " + d.weaponsReward());
        System.out.println("Recompensa de escudos: " + d.shieldsReward());
        System.out.println("Recompensa de salud: " + d.healthReward());
        System.out.println("Potencia del arma: " + d.weaponPower());
        System.out.println("Potencia del esucudo: " + d.shieldPower());
        System.out.println("Usos restantes: " + d.usesLeft());
        System.out.println("Intensidad: " + d.intensity(3.9f));
        
    }
    
}
