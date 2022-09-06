package controlador;

import dao.EmpleadoImpl;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import modelo.Empleado;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@Named(value = "EmpleadoC")
@SessionScoped
public class EmpleadoC implements Serializable {

    private Empleado emp;
    private EmpleadoImpl dao;
    private List<Empleado> listadoEMP;

    public EmpleadoC() {
        emp = new Empleado();
        dao = new EmpleadoImpl();
    }

    public void reporteEmpleado() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
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
            emp.setESTEMP("A");
            dao.registrar(emp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            emp.setESTEMP("A");
            dao.modificar(emp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en modificarC" + e.getMessage());
        }
    }

    public void eliminar(Empleado emp) throws Exception {
        try {
            emp.setESTEMP("I");
            dao.eliminar(emp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminarC" + e.getMessage());
        }
    }

    public void limpiar() {
        emp = new Empleado();
    }

    public void listar() {
        try {
            listadoEMP = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarCf " + e.getMessage());
        }
    }

    public Empleado getEmp() {
        return emp;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public EmpleadoImpl getDao() {
        return dao;
    }

    public void setDao(EmpleadoImpl dao) {
        this.dao = dao;
    }

    public List<Empleado> getListadoEmp() {
        return listadoEMP;
    }

    public void setListadoEmp(List<Empleado> listadoEmp) {
        this.listadoEMP = listadoEmp;
    }

}
