/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so;

/**
 *
 * @author Roy
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TXT implements Runnable {
    private String mensaje;
    
    public TXT(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ganadores.txt"));
            writer.write(mensaje);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
