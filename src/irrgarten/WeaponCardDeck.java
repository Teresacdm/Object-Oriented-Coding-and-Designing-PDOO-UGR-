/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class WeaponCardDeck extends CardDeck<Weapon>{
    private static final int MAX_CARDS=10;
    
    public WeaponCardDeck(){
        super();
    }
    
    @Override
    protected void addCards(){
        for (int i=0; i<MAX_CARDS; i++){
            Weapon arma = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            addCard(arma);
        }
    }
}
