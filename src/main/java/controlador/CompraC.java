package controlador;

import dao.CompraImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import modelo.Proveedor;
import modelo.Motos;
//import modelo.Motos;
import modelo.Compra;
import modelo.CompraDetalle;

@Named(value = "CompraC")
@SessionScoped
public class CompraC implements Serializable {

        private String nombre;
    private String desc;
    private int cantidad;
    private double precio;
    private int CodigoProdu;
    private double subtotal;
    private int item;
    private double total;
    private int nroCompras;
    
    private CompraImpl dao;
    private Compra compra;
    private Proveedor pro;
    private Motos mot;
    private CompraDetalle comdet;

    private List<Compra> lstCompra;
    private List<Proveedor> listado;
    private List<CompraDetalle> listaVB = new ArrayList<>();

//    private Motos MOT;
    public CompraC() {
        compra = new Compra();
        pro = new Proveedor();
        dao = new CompraImpl();
        comdet = new CompraDetalle();
        lstCompra = new ArrayList();
        mot = new Motos();

    }

    public void ojalafuncione() {
        try {
            dao.mostrarDatos(mot);
        } catch (Exception e) {
            System.out.println("Errror en mostrarC + CompraC" + e.getMessage());
        }
    }

//    public void what() throws Exception {
//
//        try {
//
//            dao.mostrarDatos(cli);
//
//        } catch (Exception e) {
//            System.out.println("error al filtrar" + e.getMessage());
//        }
//
//    }
    
    
    public List<String> autocompletePrueba(String query) throws Exception {
        try {
            return dao.autocompletar(query);
        } catch (Exception e) {
            System.out.println("Erro please" + e.getMessage());
            throw e;
        }
    }

    public void listar() {

        try {
            lstCompra = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarC + CompraC" + e.getMessage());
        }

    }

    @PostConstruct

    public void construir() {
        listar();
    }

    public void Filtrado() throws Exception {

        try {

            dao.filtrarProveedor(pro);

        } catch (Exception e) {
            System.out.println("error al filtrar" + e.getMessage());
        }

    }

    public void registrar() throws Exception {
        try {
            compra.setIDPRO(pro.getIDPRO());
            dao.registrar(compra);
            registrarDetalle();
            System.out.println(compra.getFECCOM());
        } catch (Exception e) {
            System.out.println("Error en registrar VenatC");
        }
    }
    
    
    public void manuel() {
        total = 0.0;
        item = item + 1;
        nombre = mot.getMODMOT();
        cantidad = mot.getCANMOT();
        precio = mot.getPREVENMOT();
        CodigoProdu = mot.getIDMOT();
        subtotal = cantidad * precio;

        comdet = new CompraDetalle();
      
        comdet.setIDMOT(CodigoProdu);
        comdet.setCANTCOMDET(cantidad);
        comdet.setSubtotal(subtotal);
        System.out.println(comdet.getIDMOT());
        System.out.println(comdet.getCANTCOMDET());
        listaVB.add(comdet);

        for (int i = 0; i < listaVB.size(); i++) {
            total = total + listaVB.get(i).getSubtotal();
        }
    }
    
    public void registrarDetalle() throws Exception {
        int paul = dao.ventasMaximas();
//        dao.ventasMaximas(venDet);
        for (int i = 0; i < listaVB.size(); i++) {
            comdet = new CompraDetalle();
            comdet.setCANTCOMDET(listaVB.get(i).getCANTCOMDET());
            comdet.setIDCOM(paul);
            comdet.setIDMOT(listaVB.get(i).getIDMOT());
            dao.registrarVentaDetalle(comdet);

        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCodigoProdu() {
        return CodigoProdu;
    }

    public void setCodigoProdu(int CodigoProdu) {
        this.CodigoProdu = CodigoProdu;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getNroCompras() {
        return nroCompras;
    }

    public void setNroCompras(int nroCompras) {
        this.nroCompras = nroCompras;
    }

    public CompraImpl getDao() {
        return dao;
    }

    public void setDao(CompraImpl dao) {
        this.dao = dao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Proveedor getPro() {
        return pro;
    }

    public void setPro(Proveedor pro) {
        this.pro = pro;
    }

    public Motos getMot() {
        return mot;
    }

    public void setMot(Motos mot) {
        this.mot = mot;
    }

    public CompraDetalle getComdet() {
        return comdet;
    }

    public void setComdet(CompraDetalle comdet) {
        this.comdet = comdet;
    }

    public List<Compra> getLstCompra() {
        return lstCompra;
    }

    public void setLstCompra(List<Compra> lstCompra) {
        this.lstCompra = lstCompra;
    }

    public List<Proveedor> getListado() {
        return listado;
    }

    public void setListado(List<Proveedor> listado) {
        this.listado = listado;
    }

    public List<CompraDetalle> getListaVB() {
        return listaVB;
    }

    public void setListaVB(List<CompraDetalle> listaVB) {
        this.listaVB = listaVB;
    }
    

}
