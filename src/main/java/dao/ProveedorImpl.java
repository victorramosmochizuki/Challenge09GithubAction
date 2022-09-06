package dao;

//import com.mysql.jdbc.Statement;
import java.sql.Statement;
import java.util.List;
import modelo.Proveedor;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
//import javax.resource.cci.ResultSet;

public class ProveedorImpl extends Conexion implements ICRUD<Proveedor> {

    @Override
    public void registrar(Proveedor pro) throws Exception {
        try {
            String sql = "insert into proveedores"
                    + " (NOMPRO,RUCPRO,DIRPRO,PROPRO,CELPRO,ESTPRO)"
                    + " values (?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNOMPRO());
            ps.setString(2, pro.getRUCPRO());
            ps.setString(3, pro.getDIRPRO());
            ps.setString(4, pro.getPROPRO());
            ps.setString(5, pro.getCELPRO());
            ps.setString(6, pro.getESTPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar ProveedorImpl: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Proveedor pro) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "update proveedores set NOMPRO=?,RUCPRO=?,DIRPRO=?,PROPRO=?,CELPRO=?,ESTPRO=? WHERE IDPRO=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNOMPRO());
            ps.setString(2, pro.getRUCPRO());
            ps.setString(3, pro.getDIRPRO());
            ps.setString(4, pro.getPROPRO());
            ps.setString(5, pro.getCELPRO());
            ps.setString(6, pro.getESTPRO());
            ps.setInt(7, pro.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar ProveedorImpl: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Proveedor pro) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            String sql = "update proveedores set ESTPRO=? where IDPRO=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1,pro.getESTPRO());
            ps.setInt(2,pro.getIDPRO());
            ps.executeUpdate();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error en Eliminar ProveedorImpl " + e.getMessage());
        }
    }

    @Override
    public List listar() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Proveedor> listado = null;
        Proveedor pro;
        String sql = "select * from proveedores where ESTPRO='A' order by IDPRO desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            pro = new Proveedor();
            pro.setIDPRO(rs.getInt("IDPRO"));
            pro.setNOMPRO(rs.getString("NOMPRO"));
            pro.setRUCPRO(rs.getString("RUCPRO"));
            pro.setDIRPRO(rs.getString("DIRPRO"));
            pro.setPROPRO(rs.getString("PROPRO"));
            pro.setCELPRO(rs.getString("CELPRO"));            
            listado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar ProveedorImpl " + e.getMessage());
        }
        return listado;
    }
    
    public boolean existe(Proveedor modelo, List<Proveedor> listaModelo) {
        for (Proveedor pro : listaModelo) {
            if (modelo.getRUCPRO().equals(pro.getRUCPRO())) {
                return true;
            }
        }
        return false;
    }
    
}
