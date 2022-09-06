package controlador;

import dao.ProveedorImpl;
import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import modelo.Proveedor;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "ProveedorC")
@SessionScoped
public class ProveedorC implements Serializable {

    private Proveedor pro;
    private ProveedorImpl dao;
    private List<Proveedor> listadoPro;

    public ProveedorC() {
        pro = new Proveedor();
        dao = new ProveedorImpl();
    }
 public void registrar() throws Exception {
        try {
            pro.setESTPRO("A");
            dao.registrar(pro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
        limpiar();
        listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            pro.setESTPRO("A");
            dao.modificar(pro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
        limpiar();
        listar();
        
        } catch (Exception e) {
            System.out.println("Error en modificarC" + e.getMessage());
        }
    }
    
    public void eliminar(Proveedor pro) throws Exception{
        try {
            pro.setESTPRO("I");
            dao.eliminar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();
            
        } catch (Exception e) {
            System.out.println("Error en eliminarC" +e.getMessage());
        }
    }
    
    public void limpiar() {
        pro = new Proveedor();
    }

    public void listar() {
        try {
            listadoPro = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public Proveedor getPro() {
        return pro;
    }

    public void setPro(Proveedor pro) {
        this.pro = pro;
    }

    public ProveedorImpl getDao() {
        return dao;
    }

    public void setDao(ProveedorImpl dao) {
        this.dao = dao;
    }

    public List<Proveedor> getListadoPro() {
        return listadoPro;
    }

    public void setListadoPro(List<Proveedor> listadoPro) {
        this.listadoPro = listadoPro;
    }
    
    
   
   
}
