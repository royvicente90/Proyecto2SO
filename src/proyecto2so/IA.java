/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import proyecto2so.Serie;
import proyecto2so.Interfaz;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
/**
 *
 * @author Roy
 */
public abstract class IA extends SwingWorker<Void, Void> {
    private Queue<Serie> nivel1;
    private Queue<Serie> nivel2;
    private Queue<Serie> nivel3;
    private Queue<Serie> colaRefuerzo;
    private Queue<Serie> ganadores;
    private Random random;
    int dia;
    private Semaphore mutex = new Semaphore(1);
    int contadorDias = 0;

    public IA() {
        this.nivel1 = new LinkedList<>();
        this.nivel2 = new LinkedList<>();
        this.nivel3 = new LinkedList<>();
        this.colaRefuerzo = new LinkedList<>();
        this.ganadores = new LinkedList<>();
        this.random = new Random();

    }
    
    public void setDia(int dia) {
        this.dia = dia;
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
    
    public void encolarRefuerzo(Serie serie) {
        colaRefuerzo.add(serie);
    }
    
    public void desencolarRefuerzo(Serie serie) {
        colaRefuerzo.remove(serie);
    }
    
    public void archivo() {
        ganadores = this.ganadores;
        String mensaje = "";
        for (Serie serie : ganadores) {
            mensaje += serie.getNombre() + "\n";
        }
        TXT txt = new TXT(mensaje);
        Thread hilo = new Thread(txt);
        hilo.start();
    }
    
    //Actualizador de tablas
    public void actualizarNiveles() {
        nivel1 = this.nivel1;
        nivel2 = this.nivel2;
        nivel3 = this.nivel3;
        colaRefuerzo = this.colaRefuerzo;
        ganadores = this.ganadores;
        String mensaje1 = "";
        String mensaje2 = "";
        String mensaje3 = "";
        String mensajeRef = "";
        String mensajeWin = "";
        
        if (!nivel1.isEmpty()){
        for (Serie serie : nivel1) {
            mensaje1 += serie.getNombre() + "\n";
        }}
        if (!nivel2.isEmpty()){
        for (Serie serie : nivel2) {
            mensaje2 += serie.getNombre() + "\n";
        }}
        if (!nivel3.isEmpty()){
        for (Serie serie : nivel3) {
            mensaje3 += serie.getNombre() + "\n";
        }}
        if (!colaRefuerzo.isEmpty()){
        for (Serie serie : colaRefuerzo) {
            mensajeRef += serie.getNombre() + "\n";
        }}
        if (!ganadores.isEmpty()){
        for (Serie serie : ganadores) {
            mensajeWin += serie.getNombre() + "\n";
        }}
        
        Interfaz.Nivel1.setText(mensaje1);
        Interfaz.Nivel2.setText(mensaje2);
        Interfaz.Nivel3.setText(mensaje3);
        Interfaz.colaDeRefuerzo.setText(mensajeRef);
        Interfaz.Ganadores.setText(mensajeWin);
    }
    
    //Actualizador de contadores
    public void actualizarContadores() {
        nivel1 = this.nivel1;
        nivel2 = this.nivel2;
        nivel3 = this.nivel3;
        colaRefuerzo = this.colaRefuerzo;
        
        if (!nivel2.isEmpty()){
        for (Serie serie : nivel2) {
           if (serie.getContador() < 8) {
            serie.sumContador(); 
           } else {
               serie.restContador();
               serie.setPrioridad(1);
               encolar(serie);
               nivel2.remove(serie);
           }
        }}
        if (!nivel3.isEmpty()){
        for (Serie serie : nivel3) {
            if (serie.getContador() < 8) {
            serie.sumContador(); 
           } else {
               serie.restContador();
               serie.setPrioridad(2);
               encolar(serie);
               nivel3.remove(serie);
           }
        }}
        /*
        if (!colaRefuerzo.isEmpty()){
        for (Serie serie : colaRefuerzo) {
            mensajeRef += serie.getNombre() + "\n";
        }}*/
    }

    public Serie seleccionar() {
        nivel1 = this.nivel1;
        nivel2 = this.nivel2;
        nivel3 = this.nivel3;
        colaRefuerzo = this.colaRefuerzo;
        ganadores = this.ganadores;
        Serie serie = new Serie();
        
        //Seleccionador de pelea
        
        if (!nivel1.isEmpty()) {
            serie = nivel1.poll();
        }
        else if (!colaRefuerzo.isEmpty()){
            serie = colaRefuerzo.poll();
        }
        else if (!nivel2.isEmpty() && nivel1.isEmpty()) {
            serie = nivel2.poll();
        }
        else {
            serie = nivel3.poll();
            }
        return serie;
    }
        
    //Funcion Pelea
    @Override
    protected Void doInBackground() throws Exception {
        nivel1 = this.nivel1;
        nivel2 = this.nivel2;
        nivel3 = this.nivel3;
        colaRefuerzo = this.colaRefuerzo;
        ganadores = this.ganadores;
        int velocidad = dia * (1000 * (10 + 4));
        try {
            while (true) {
        
        Serie serie1 = seleccionar();
        Serie serie2 = seleccionar();
        //actualizarContadores();
        actualizarNiveles();
        
        
        int aleatorio = random.nextInt(100);
        //pelea
        if (serie1 != null && serie2 != null) {
            System.out.print(serie1.nombre + " - VS - " + serie2.nombre + '\n');
            Interfaz.Peleando.setText(serie1.nombre + " - VS - " + serie2.nombre);
            
            serie1.restContador();
            serie2.restContador();
            
            //Thread.sleep(velocidad);
            Thread.sleep(1000);
            
            if (serie1.isCalidad() && !serie2.isCalidad()){
                ganadores.add(serie1);
                //serie2.setPrioridad(3);
                //encolar(serie2);
                System.out.print("Ganador " + serie1.getNombre() + "\n");
                //System.out.print("Perdedor " + serie2.getNombre() + "\n");
            } else if (!serie1.isCalidad() && serie2.isCalidad()) {
                ganadores.add(serie2);
                //serie1.setPrioridad(3);
                //encolar(serie1);
                System.out.print("Ganador " + serie2.getNombre() + "\n");
                //System.out.print("Perdedor " + serie1.getNombre() + "\n");
            } else {
            
            int pGanador = random.nextInt(100);
            
            if (pGanador <= 40) {
                
                        //tenemos ganador
                        if (pGanador <= 20) {
                            ganadores.add(serie1);
                            serie2.setPrioridad(3);
                            encolar(serie2);
                            System.out.print("Ganador " + serie1.getNombre() + "\n");
                            //System.out.print("Perdedor " + serie2.getNombre() + "\n");
                        } else {
                            ganadores.add(serie2);
                            serie1.setPrioridad(3);
                            encolar(serie1);
                            System.out.print("Ganador " + serie2.getNombre() + "\n");
                            //System.out.print("Perdedor " + serie1.getNombre() + "\n");
                        }
                    } else {if (pGanador > 40 && pGanador <= 67) {
                            serie1.setPrioridad(3);
                            serie2.setPrioridad(3);
                            encolar(serie1);
                            encolar(serie2);

                        } else {
                            colaRefuerzo.add(serie1);
                            colaRefuerzo.add(serie2);
                    }
                        
                    }}
            //Final del dia
            if (contadorDias % 2 == 0) {
                if (random.nextInt(100) <= 70) {
                    Serie serie = new Serie();
                    serie.crearSerie();
                    System.out.print("Creado: " + serie.getNombre() + "\n");
                    encolar(serie);
                }
            }
            contadorDias++;
           
            //terminar programa
            if (nivel1.isEmpty()&& nivel2.isEmpty() && nivel3.isEmpty()) {
                System.out.print("Terminado!" + '\n');
                Interfaz.Peleando.setText("Terminado!");
                archivo();
                        break;
            } else {
            }
            }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    }    