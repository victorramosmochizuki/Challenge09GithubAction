/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.MotosImpl;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Motos;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletContext;

@Named(value = "MotosC")
@SessionScoped
public class MotosC implements Serializable {

    private Motos mot;
    private MotosImpl dao;
    private List<Motos> listadoMot;

    public MotosC() {
        mot = new Motos();
        dao = new MotosImpl();
    }

    public void reporteMotos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        reporte Cliente = new reporte();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("reportes/Cherry.jasper");
        System.out.println("este es el root " + root);
        //String numeroinformesocial = String.valueOf(modelo.getESTPER());
        //System.out.println("La Persona es: " + numeroinformesocial);
        Cliente.getReportePdf(root, "A");
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void registrar() throws Exception {
        try {
            mot.setESTMOT("A");
            dao.registrar(mot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrar MotosCC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            mot.setESTMOT("A");
            dao.modificar(mot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en modificar MotosCC" + e.getMessage());
        }
    }

    public void eliminar(Motos mot) throws Exception {
        try {
            mot.setESTMOT("I");
            dao.eliminar(mot);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminar MotosC" + e.getMessage());
        }
    }

    public void limpiar() {
        mot = new Motos();
    }

    public void listar() {
        try {
            listadoMot = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listar MotosC " + e.getMessage());
        }
    }

    public Motos getMot() {
        return mot;
    }

    public void setMot(Motos mot) {
        this.mot = mot;
    }

    public MotosImpl getDao() {
        return dao;
    }

    public void setDao(MotosImpl dao) {
        this.dao = dao;
    }

    public List<Motos> getListadoMot() {
        return listadoMot;
    }

    public void setListadoMot(List<Motos> listadoMot) {
        this.listadoMot = listadoMot;
    }

}
