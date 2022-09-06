/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.RepuestosImpl;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Repuestos;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletContext;

@Named(value = "RepuestosC")
@SessionScoped
public class RepuestosC implements Serializable{
    private Repuestos rep;
    private RepuestosImpl dao;
    private List<Repuestos> listadoRep;

    public RepuestosC() {
        rep = new Repuestos();
        dao = new RepuestosImpl();
    }
        public void reporterepuesto() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
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
            if (!dao.existe(rep, listadoRep)) {
                rep.setNOMREP(CamelCase(rep.getNOMREP()));
                rep.setMARREP(CamelCase(rep.getMARREP()));

                rep.setESTREP("A");
                dao.registrar(rep);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
                limpiar();
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "RUC existente"));

            }

        } catch (Exception e) {
            System.out.println("Error en registrar RepuestosCC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            rep.setESTREP("A");
            dao.modificar(rep);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        limpiar();
        listar();
        
        } catch (Exception e) {
            System.out.println("Error en modificar RepuestosCC" + e.getMessage());
        }
    }
    
    public void eliminar(Repuestos rep) throws Exception{
        try {
            rep.setESTREP("I");
            dao.eliminar(rep);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
            
        } catch (Exception e) {
            System.out.println("Error en eliminar RepuestosC" +e.getMessage());
        }
    }
    
    public void limpiar() {
        rep = new Repuestos();
    }

    public void listar() {
        try {
            listadoRep = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listar RepuestosC " + e.getMessage());
        }
    }

    public Repuestos getRep() {
        return rep;
    }

    public void setRep(Repuestos rep) {
        this.rep = rep;
    }

    public RepuestosImpl getDao() {
        return dao;
    }

    public void setDao(RepuestosImpl dao) {
        this.dao = dao;
    }

    public List<Repuestos> getListadoRep() {
        return listadoRep;
    }

    public void setListadoRep(List<Repuestos> listadoRep) {
        this.listadoRep = listadoRep;
    }
    
    public String CamelCase(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            } else if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        return st;
    }

}
