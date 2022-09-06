package modelo;

import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Data;

public class Venta {

    private int IDCLI;
    private int IDVEN;
    private int IDEMP;
    private String FOR_PAG;
    private Date FECVEN = GregorianCalendar.getInstance().getTime();
    private double Subtotal;
    private double Prcio;

    public int getIDCLI() {
        return IDCLI;
    }

    public void setIDCLI(int IDCLI) {
        this.IDCLI = IDCLI;
    }

    public int getIDVEN() {
        return IDVEN;
    }

    public void setIDVEN(int IDVEN) {
        this.IDVEN = IDVEN;
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

    public Date getFECVEN() {
        return FECVEN;
    }

    public void setFECVEN(Date FECVEN) {
        this.FECVEN = FECVEN;
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
