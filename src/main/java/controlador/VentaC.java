package controlador;

import dao.VentaImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import modelo.Cliente;
import modelo.Motos;
//import modelo.Motos;
import modelo.Venta;
import modelo.VentaDetalle;

@Named(value = "VentaC")
@SessionScoped
public class VentaC implements Serializable {

        private String nombre;
    private String desc;
    private int cantidad;
    private double precio;
    private int CodigoProdu;
    private double subtotal;
    private int item;
    private double total;
    private int nroVentas;
    
    private VentaImpl dao;
    private Venta venta;
    private Cliente cli;
    private Motos mot;
    private VentaDetalle vendet;

    private List<Venta> lstVenta;
    private List<Cliente> listado;
    private List<VentaDetalle> listaVB = new ArrayList<>();

//    private Motos MOT;
    public VentaC() {
        venta = new Venta();
        cli = new Cliente();
        dao = new VentaImpl();
        vendet = new VentaDetalle();
        lstVenta = new ArrayList();
        mot = new Motos();

    }

    public void ojalafuncione() {
        try {
            dao.mostrarDatos(mot);
        } catch (Exception e) {
            System.out.println("Errror en mostrarC" + e.getMessage());
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
            lstVenta = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }

    }

    @PostConstruct

    public void construir() {
        listar();
    }

    public void Filtrado() throws Exception {

        try {

            dao.filtrarCliente(cli);

        } catch (Exception e) {
            System.out.println("error al filtrar" + e.getMessage());
        }

    }

    public void registrar() throws Exception {
        try {
            venta.setIDCLI(cli.getIdcli());
            dao.registrar(venta);
            registrarDetalle();
            limpiar();
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

        vendet = new VentaDetalle();
      
        vendet.setIDMOT(CodigoProdu);
        vendet.setCANTVENDET(cantidad);
        vendet.setSubtotal(subtotal);
        System.out.println(vendet.getIDMOT());
        System.out.println(vendet.getCANTVENDET());
        listaVB.add(vendet);

        for (int i = 0; i < listaVB.size(); i++) {
            total = total + listaVB.get(i).getSubtotal();
        }
    }
    
    public void registrarDetalle() throws Exception {
        int paul = dao.ventasMaximas();
//        dao.ventasMaximas(venDet);
        for (int i = 0; i < listaVB.size(); i++) {
            vendet = new VentaDetalle();
            vendet.setCANTVENDET(listaVB.get(i).getCANTVENDET());
            vendet.setIDVENT(paul);
            vendet.setIDMOT(listaVB.get(i).getIDMOT());
            dao.registrarVentaDetalle(vendet);

        }
    }
    public void limpiar() {
        venta = new Venta();
        cli = new Cliente();
        dao = new VentaImpl();
        vendet = new VentaDetalle();
        lstVenta = new ArrayList();
    }

    public VentaImpl getDao() {
        return dao;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public void setDao(VentaImpl dao) {
        this.dao = dao;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<Venta> getLstVenta() {
        return lstVenta;
    }

    public void setLstVenta(List<Venta> lstVenta) {
        this.lstVenta = lstVenta;
    }

    public List<Cliente> getListado() {
        return listado;
    }

    public void setListado(List<Cliente> listado) {
        this.listado = listado;
    }

    public Motos getMot() {
        return mot;
    }

    public void setMot(Motos mot) {
        this.mot = mot;
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

    public int getNroVentas() {
        return nroVentas;
    }

    public void setNroVentas(int nroVentas) {
        this.nroVentas = nroVentas;
    }

    public int getCodigoProdu() {
        return CodigoProdu;
    }

    public void setCodigoProdu(int CodigoProdu) {
        this.CodigoProdu = CodigoProdu;
    }

    public VentaDetalle getVendet() {
        return vendet;
    }

    public void setVendet(VentaDetalle vendet) {
        this.vendet = vendet;
    }

    public List<VentaDetalle> getListaVB() {
        return listaVB;
    }

    public void setListaVB(List<VentaDetalle> listaVB) {
        this.listaVB = listaVB;
    }

}
