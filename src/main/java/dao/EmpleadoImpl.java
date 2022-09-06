
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoImpl extends Conexion implements ICRUD<Empleado>{

    @Override
    public void registrar(Empleado emp) throws Exception {
        try {
            String sql = "insert into empleado"
                    + " (nomemp,apeemp,emailemp,celemp,fehcregemp,docemp,estemp)"
                    + " values (?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, emp.getNOMEMP());
            ps.setString(2, emp.getAPEEMP());
            ps.setString(3, emp.getEMAILEMP());
            ps.setString(4, emp.getCELEMP());
            ps.setString(5, emp.getFEHCREGEMP());
            ps.setString(6, emp.getDOCEMP());
            ps.setString(7, emp.getESTEMP());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar empleado GAAAA: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Empleado emp) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "update empleado set nomemp=?,apeemp=?,emailemp=?,celemp=?,fehcregemp=?,docemp=?,estemp=? WHERE IDEMP=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, emp.getNOMEMP());
            ps.setString(2, emp.getAPEEMP());
            ps.setString(3, emp.getEMAILEMP());
            ps.setString(4, emp.getCELEMP());
            ps.setString(5, emp.getFEHCREGEMP());
            ps.setString(6, emp.getDOCEMP());
            ps.setString(7, emp.getESTEMP());
            ps.setInt(8, emp.getIDEMP());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar Empleado: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Empleado emp) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        try {
            String sql = "update empleado set ESTEMP=? where IDEMP=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1,emp.getESTEMP());
            ps.setInt(2,emp.getIDEMP());
            ps.executeUpdate();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error en Eliminar Empleado: " + e.getMessage());
        }
    }

    @Override
    public List listar() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Empleado> listado = null;
        Empleado emp;
        String sql = "select * from empleado ";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            emp = new Empleado();
            emp.setIDEMP(rs.getInt("IDEMP"));
            emp.setNOMEMP(rs.getString("NOMEMP"));
            emp.setAPEEMP(rs.getString("APEEMP"));
            emp.setEMAILEMP(rs.getString("EMAILEMP"));
            emp.setCELEMP(rs.getString("CELEMP"));
            emp.setFEHCREGEMP(rs.getString("FEHCREGEMP"));
            emp.setDOCEMP(rs.getString("DOCEMP"));
            emp.setESTEMP(rs.getString("ESTEMP"));
            listado.add(emp);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar empleado " + e.getMessage());
        }
        return listado;
    }
    
}
