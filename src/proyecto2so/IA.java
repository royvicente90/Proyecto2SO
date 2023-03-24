/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so;
import proyecto2so.Serie;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import proyecto2so.Interfaz;
/**
 *
 * @author Roy
 */
public class IA extends Thread {
    private Queue<Serie> nivel1;
    private Queue<Serie> nivel2;
    private Queue<Serie> nivel3;
    private Queue<Serie> colaRefuerzo;
    private Random random;
    int dia;
    int velocidad = 1000 * (10 + 4) * dia;

    public IA() {
        this.nivel1 = new LinkedList<>();
        this.nivel2 = new LinkedList<>();
        this.nivel3 = new LinkedList<>();
        this.colaRefuerzo = new LinkedList<>();
        this.random = new Random();
    }

    public void encolar(Serie serie) {
        switch (serie.getPrioridad()) {
            case 1:
                nivel1.add(serie);
                break;
            case 2:
                nivel2.add(serie);
                break;
            case 3:
                nivel3.add(serie);
                break;
            default:
                System.out.println("Prioridad inválida");
                break;
        }
    }

    public void desencolar(Serie serie) {
        switch (serie.getPrioridad()) {
            case 1:
                nivel1.remove(serie);
                break;
            case 2:
                nivel2.remove(serie);
                break;
            case 3:
                nivel3.remove(serie);
                break;
            default:
                System.out.println("Prioridad inválida");
                break;
        }
    }

    public void procesar() {
        Serie serie1 = new Serie();
        Serie serie2 = new Serie();
        int aleatorio = random.nextInt(100);

        // Seleccionar serie de nivel 1 si hay alguna, sino seleccionar una de nivel 2 o 3
        if (!nivel1.isEmpty()) {
            serie1 = nivel1.poll();
        } else {
            if (!nivel2.isEmpty() && aleatorio < 50) {
                serie1 = nivel2.poll();
            } else if (!nivel3.isEmpty() && aleatorio < 80) {
                serie1 = nivel3.poll();
            }
        }

        // Seleccionar serie de nivel 2 o 3 que no sea la misma que la primera
        if (serie1 != null) {
            if (!nivel2.isEmpty() && serie1.getPrioridad() != 2) {
                serie2 = nivel2.poll();
            } else if (!nivel3.isEmpty() && serie1.getPrioridad() != 3) {
                serie2 = nivel3.poll();
            }
        }
    }
}
    