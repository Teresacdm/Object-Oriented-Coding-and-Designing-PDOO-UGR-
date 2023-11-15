/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import irrgarten.controller.Controller;
import irrgarten.UI.TextUI;


/**
 *
 * @author usuario
 */
public class Irrgarten {
    public static void main(String[] args) {
        TextUI textUI=new TextUI();
        Game game = new Game(1);
        Controller controller = new Controller(game, textUI);
        controller.play();
    }
}
