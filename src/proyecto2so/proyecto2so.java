/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so;
import javax.swing.JFrame;
import proyecto2so.Interfaz;
/**
 *
 * @author Roy
 */
public class proyecto2so {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //interfaz.setSize(900,600);
        interfaz.setLocationRelativeTo(null);
        interfaz.setVisible(true);
        }
}
