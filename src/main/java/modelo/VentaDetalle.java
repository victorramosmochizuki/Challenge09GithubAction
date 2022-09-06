/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class VentaDetalle {

    private int CANTVENDET;
    private int IDVENT;
    private int IDMOT;
    private int IDREP;
    private int item;
    private double subtotal;


    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getCANTVENDET() {
        return CANTVENDET;
    }

    public void setCANTVENDET(int CANTVENDET) {
        this.CANTVENDET = CANTVENDET;
    }

    public int getIDVENT() {
        return IDVENT;
    }

    public void setIDVENT(int IDVENT) {
        this.IDVENT = IDVENT;
    }

    public int getIDMOT() {
        return IDMOT;
    }

    public void setIDMOT(int IDMOT) {
        this.IDMOT = IDMOT;
    }

    public int getIDREP() {
        return IDREP;
    }

    public void setIDREP(int IDREP) {
        this.IDREP = IDREP;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
