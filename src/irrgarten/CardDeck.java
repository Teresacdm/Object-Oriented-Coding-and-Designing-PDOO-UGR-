/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author usuario
 */
public abstract class CardDeck<T>{
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    protected abstract void addCards(); 
    
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
    public T nextCard(){
        //Si no hay cartas en la colección, se llama al método addCards (para que la baraja tenga
        //contenido) y se baraja.
        if(cardDeck.size()<=0){
            addCards();
            Collections.shuffle(cardDeck);
        }
        T output = cardDeck.get(0);
        cardDeck.remove(0);
        return output;
    }
}
