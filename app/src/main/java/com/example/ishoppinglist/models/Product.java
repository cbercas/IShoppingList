package com.example.ishoppinglist.models;

import java.io.Serializable;

/**
 * Representa un producto en la lista de compras
 * Esta clase implementa Serializable para permitir que el objeto se pase entre actividades o se almacene
 * @author Cristina
 */
public class Product implements Serializable {

    //Variables
    private int id;
    private String name;
    private String note;
    private boolean isPending;

    /**
     * Constructor por defecto.
     * Inicializa el objeto Product con valores predeterminados.
     */
    public Product() {
        super();
    }

    /**
     * Constructor con parámetros para inicializar todos los campos del producto.
     *
     * @param id    int     Identificador único del producto
     * @param name    String   Nombre del producto
     * @param note  String     Nota relacionada con el producto
     * @param isPending boolean Indica si el producto está pendiente de ser comprado
     */
    public Product (int id, String name, String note, boolean isPending) {
        this.id =id;
        this.name = name;
        this.note = note;
        this.isPending = isPending;
    }

    /**
     * Constructor sin el campo 'id' para crear un producto.
     * Este se puede usar cuando el id es auto-generado o no está disponible en el momento.
     *
     * @param name   String    Nombre del producto
     * @param note    String   Nota relacionada con el producto
     * @param isPending boolean Indica si el producto está pendiente de ser comprado
     */
    public Product (String name, String note, boolean isPending) {
        this.name = name;
        this.note = note;
        this.isPending = isPending;
    }


    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    // método toString
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", isPending=" + isPending +
                '}';
    }
}
