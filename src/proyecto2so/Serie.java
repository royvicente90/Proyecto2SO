/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so;
import java.util.Random;
/**
 *
 * @author Roy
 */
public class Serie {
    public String nombre;
    public int id;
    public int prioridad;
    public int duracion;
    public int contador;
    public boolean calidad;

    public Serie() {

    }

    public int getId() {
        return id;
    }
    
    public void setId() {
        Random random = new Random();
        this.id = random.nextInt(1000);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre() {
        Random random = new Random();
        String[] nombres = {"Daenerys", "Cersei", "Jon", "Arya", "Sansa"};
        int indice = random.nextInt(nombres.length);
        this.nombre = nombres[indice] + '-' + getId();
    }
    
    public boolean isCalidad() {
        return calidad;
    }
    
    public void setCalidad() {
        Random random = new Random();
        int intro = random.nextInt(100);
        int inicio = random.nextInt(100);
        int cierre = random.nextInt(100);
        int credito = random.nextInt(100);
        
        if (intro <= 75 && inicio <= 84  && cierre <= 80 && credito <= 85){
            calidad = true;
        }
    }

    public int getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    public void primerPrioridad() {
        Random random = new Random();
        if (duracion < 60) {
            this.prioridad = 3;
        } else if (duracion >= 60 && duracion <= 90) {
            this.prioridad = 2;
        } else {
            this.prioridad = 1;
        }
    }

    public int getDuracion() {
        return duracion;
    }
    
    public void setDuracion() {
        Random random = new Random();
        this.duracion = random.nextInt(100);
    }

    public int getContador() {
        return contador;
    }

    public void sumContador() {
        if (contador < 8)
        this.contador++;
    }
    
    public void restContador() {
        this.contador = 0;
    }

    public void crearSerie() {
        setId();
        setDuracion();
        setCalidad();
        setNombre();
        primerPrioridad();
    }
}