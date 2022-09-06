package dao;

//import com.mysql.jdbc.Statement;
import java.sql.Statement;
import java.util.List;
import modelo.Cliente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
//import javax.resource.cci.ResultSet;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    @Override
    public void registrar(Cliente cli) throws Exception {
        try {
            String sql = "insert into cliente"
                    + " (NOMCLIE,APECLIE,DOCCLIE,EMACLIE,CELCLIE,ESTCLIE)"
                    + " values (?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getNomclie());
            ps.setString(2, cli.getApeclie());
            ps.setString(3, cli.getDocclie());
            ps.setString(4, cli.getEmaclie());
            ps.setString(5, cli.getCelclie());
            ps.setString(6, cli.getEstclie());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar ClienteImpl: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Cliente cli) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "update cliente set apeclie=?,celclie=?,docclie=?,emaclie=?,estclie=?,nomclie=? WHERE IDCLI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getApeclie());
            ps.setString(2, cli.getCelclie());
            ps.setString(3, cli.getDocclie());
            ps.setString(4, cli.getEmaclie());
            ps.setString(5, cli.getEstclie());
            ps.setString(6, cli.getNomclie());
            ps.setInt(7, cli.getIdcli());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar Cliente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Cliente cli) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            String sql = "update cliente set ESTCLIE=? where IDCLI=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1,cli.getEstclie());
            ps.setInt(2,cli.getIdcli());
            ps.executeUpdate();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error en Eliminar Cliente: " + e.getMessage());
        }
    }

    @Override
    public List listar() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Cliente> listado = null;
        Cliente cli;
        String sql = "select * from cliente ";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            cli = new Cliente();
            cli.setIdcli(rs.getInt("IDCLI"));
            cli.setNomclie(rs.getString("NOMCLIE"));
            cli.setApeclie(rs.getString("APECLIE"));
            cli.setCelclie(rs.getString("CELCLIE"));
            cli.setDocclie(rs.getString("DOCCLIE"));
            cli.setEmaclie(rs.getString("EMACLIE"));
            cli.setEstclie(rs.getString("ESTCLIE"));
            listado.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar Cliente " + e.getMessage());
        }
        return listado;
    }
    
}
