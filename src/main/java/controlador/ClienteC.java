package controlador;

import dao.ClienteImpl;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import modelo.Cliente;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cli;
    private ClienteImpl dao;
    private List<Cliente> listadoCli;

    public ClienteC() {
        cli = new Cliente();
        dao = new ClienteImpl();
    }

    public void reportePersona() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
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
            cli.setEstclie("A");
            dao.registrar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            cli.setEstclie("A");
            dao.modificar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en modificarC" + e.getMessage());
        }
    }

    public void eliminar(Cliente cli) throws Exception {
        try {
            cli.setEstclie("I");
            dao.eliminar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "OK", "Eliminado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminarC" + e.getMessage());
        }
    }

    public void limpiar() {
        cli = new Cliente();
    }

    public void listar() {
        try {
            listadoCli = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getListadoCli() {
        return listadoCli;
    }

    public void setListadoCli(List<Cliente> listadoCli) {
        this.listadoCli = listadoCli;
    }

}
