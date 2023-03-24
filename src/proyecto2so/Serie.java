/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2so;

/**
 *
 * @author Roy
 */
public class Serie {
    private String nombre;
    private int id;
    private int prioridad;
    private int duracionCapitulo;
    private int contador;
    private boolean enColaRefuerzo;
    private boolean quality;

    public Serie() {

    }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public boolean getQuality() {
        return quality;
    }

    public int getPrioridad() {
        return prioridad;
    }
    
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getDuracionCapitulo() {
        return duracionCapitulo;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public boolean isEnColaRefuerzo() {
        return enColaRefuerzo;
    }

    public void setEnColaRefuerzo(boolean enColaRefuerzo) {
        this.enColaRefuerzo = enColaRefuerzo;
    }
}