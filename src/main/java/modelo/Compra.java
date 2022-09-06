package modelo;

import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Data;

public class Compra {

    private int IDPRO;
    private int IDCOM;
    private int IDEMP;
    private String FOR_PAG;
  private Date FECCOM = GregorianCalendar.getInstance().getTime();
    private double Subtotal;
    private double Prcio;

    public int getIDPRO() {
        return IDPRO;
    }

    public void setIDPRO(int IDPRO) {
        this.IDPRO = IDPRO;
    }

    public int getIDCOM() {
        return IDCOM;
    }

    public void setIDCOM(int IDCOM) {
        this.IDCOM = IDCOM;
    }

    public int getIDEMP() {
        return IDEMP;
    }

    public void setIDEMP(int IDEMP) {
        this.IDEMP = IDEMP;
    }

    public String getFOR_PAG() {
        return FOR_PAG;
    }

    public void setFOR_PAG(String FOR_PAG) {
        this.FOR_PAG = FOR_PAG;
    }

    public Date getFECCOM() {
        return FECCOM;
    }

    public void setFECCOM(Date FECCOM) {
        this.FECCOM = FECCOM;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public double getPrcio() {
        return Prcio;
    }

    public void setPrcio(double Prcio) {
        this.Prcio = Prcio;
    }

   
}
//@Data
//public class Venta {
//    
//    int  IDCLI,IDVEN, IDEMP;
//    String  FOR_PAG;
//    Date FECVEN;
//    
//    Cliente cliente;
//    Empleado empleado;
//}
