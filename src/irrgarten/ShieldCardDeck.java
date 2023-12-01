/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author usuario
 */
public class ShieldCardDeck extends CardDeck<Shield>{
    private static final int MAX_CARDS=10;
    
    public ShieldCardDeck(){
        super();
    }
    
    @Override
    protected void addCards(){
        for (int i=0; i<MAX_CARDS; i++){
            Shield escudo = new Shield(Dice.shieldPower(), Dice.usesLeft());
            addCard(escudo);
        }
    }
}
