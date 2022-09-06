
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Motos;


public class MotosImpl extends Conexion implements ICRUD<Motos>{

    @Override
    public void registrar(Motos mot) throws Exception {
        try {
            String sql = "insert into motos"
                    + " (CATMOT, MARMOT, MODMOT, COLMOT, GARMOT, PRECOMMOT, PREVENMOT, STOCK, ESTMOT)"
                    + " values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, mot.getCATMOT());
            ps.setString(2, mot.getMARMOT());
            ps.setString(3, mot.getMODMOT());
            ps.setString(4, mot.getCOLMOT());
            ps.setString(5, mot.getGARMOT());
            ps.setDouble(6, mot.getPRECOMMOT());
            ps.setDouble(7, mot.getPREVENMOT());
            ps.setInt(8, mot.getSTOCK());
            ps.setString(9, mot.getESTMOT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar MotosImpl: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Motos mot) throws Exception {
        String sql = "update motos set CATMOT=?, MARMOT=?, MODMOT=?, COLMOT=?, GARMOT=?, PRECOMMOT=?, PREVENMOT=?, STOCK=?, ESTMOT=? WHERE IDMOT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, mot.getCATMOT());
            ps.setString(2, mot.getMARMOT());
            ps.setString(3, mot.getMODMOT());
            ps.setString(4, mot.getCOLMOT());
            ps.setString(5, mot.getGARMOT());
            ps.setDouble(6, mot.getPRECOMMOT());
            ps.setDouble(7, mot.getPREVENMOT());
            ps.setInt(8, mot.getSTOCK());
            ps.setString(9, mot.getESTMOT());
            ps.setInt(10, mot.getIDMOT());
 
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar MotosImpl: " + e.getMessage());
        }
    }
    

    @Override
    public void eliminar(Motos mot) throws Exception {
        String sql = "update motos set ESTMOT=? where IDMOT=?";
        try {            
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1,mot.getESTMOT());
            ps.setInt(2,mot.getIDMOT());
            ps.executeUpdate();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("Error en Eliminar MotosImpl: " + e.getMessage());
        }
    }

    @Override
    public List<Motos> listar() throws Exception {
        List<Motos> listado = null;
        Motos mot;
        String sql = "select * from MOTOS where ESTMOT='A' order by IDMOT asc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            mot = new Motos();
            // CATMOT=?, MARMOT=?, MODMOT=?, COLMOT=?, GARMOT=?, PREMOT=?, STOCK=?, ESTMOT
            mot.setIDMOT(rs.getInt("IDMOT"));
            mot.setCATMOT(rs.getString("CATMOT"));
            mot.setMARMOT(rs.getString("MARMOT"));
            mot.setMODMOT(rs.getString("MODMOT"));
            mot.setCOLMOT(rs.getString("COLMOT"));
            mot.setGARMOT(rs.getString("GARMOT"));
            mot.setPRECOMMOT(rs.getDouble("PRECOMMOT"));
            mot.setPREVENMOT(rs.getDouble("PREVENMOT"));
            mot.setSTOCK(rs.getInt("STOCK"));
            
            listado.add(mot);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar MotosImpl " + e.getMessage());
        }
        return listado;
    }
    }
    

