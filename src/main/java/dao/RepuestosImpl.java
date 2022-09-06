package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Repuestos;

public class RepuestosImpl extends Conexion implements ICRUD<Repuestos> {

    @Override
    public void registrar(Repuestos rep) throws Exception {
        try {
            String sql = "insert into repuestos"
                    + " (nomrep, marrep, modrep,stock, precomrep, prevenrep,estrep)"
                    + " values (?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, rep.getNOMREP());
            ps.setString(2, rep.getMARREP());
            ps.setString(3, rep.getMODREP());
            ps.setInt(4, rep.getSTOCK());
            ps.setDouble(5, rep.getPRECOMREP());
            ps.setDouble(6, rep.getPREVENREP());
            ps.setString(7, rep.getESTREP());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar RepuestosImpl: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Repuestos rep) throws Exception {
        String sql = "update repuestos set nomrep=?, marrep=?, modrep=?, estrep=?,stock=?, precomrep=?, prevenrep=? WHERE IDREP=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, rep.getNOMREP());
            ps.setString(2, rep.getMARREP());
            ps.setString(3, rep.getMODREP());
            ps.setString(4, rep.getESTREP());
            ps.setInt(5, rep.getSTOCK());
            ps.setDouble(6, rep.getPRECOMREP());
            ps.setDouble(7, rep.getPREVENREP());
            ps.setInt(8, rep.getIDREP());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar RepuestosImpl: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Repuestos rep) throws Exception {
        String sql = "update repuestos set ESTREP=? where IDREP=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, rep.getESTREP());
            ps.setInt(2, rep.getIDREP());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en Eliminar RepuestosImpl: " + e.getMessage());
        }
    }

    @Override
    public List<Repuestos> listar() throws Exception {
        List<Repuestos> listado = null;
        Repuestos rep;
        String sql = "select * from REPUESTOS where ESTREP='A' order by IDREP desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rep = new Repuestos();
                rep.setIDREP(rs.getInt("IDREP"));
                rep.setNOMREP(rs.getString("NOMREP"));
                rep.setMARREP(rs.getString("MARREP"));
                rep.setMODREP(rs.getString("MODREP"));
                rep.setSTOCK(rs.getInt("STOCK"));
                rep.setPRECOMREP(rs.getDouble("PRECOMREP"));
                rep.setPREVENREP(rs.getDouble("PREVENREP"));
                listado.add(rep);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar RepuestosImpl " + e.getMessage());
        }
        return listado;
    }
    
    
    public List<Repuestos> listarInactivo() throws Exception {
        List<Repuestos> listado = null;
        Repuestos rep;
        String sql = "select * from REPUESTOS where ESTREP='I' order by IDREP desc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rep = new Repuestos();
                rep.setIDREP(rs.getInt("IDREP"));
                rep.setNOMREP(rs.getString("NOMREP"));
                rep.setMARREP(rs.getString("MARREP"));
                rep.setMODREP(rs.getString("MODREP"));
                rep.setSTOCK(rs.getInt("STOCK"));
                rep.setPRECOMREP(rs.getDouble("PRECOMREP"));
                rep.setPREVENREP(rs.getDouble("PREVENREP"));
                listado.add(rep);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar RepuestosImpl " + e.getMessage());
        }
        return listado;
    }
    
    
    
    public List<Repuestos> listarTodo() throws Exception {
        List<Repuestos> listado = null;
        Repuestos rep;
        String sql = "select * from REPUESTOS order by IDREP asc";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rep = new Repuestos();
                rep.setIDREP(rs.getInt("IDREP"));
                rep.setNOMREP(rs.getString("NOMREP"));
                rep.setMARREP(rs.getString("MARREP"));
                rep.setMODREP(rs.getString("MODREP"));
                rep.setSTOCK(rs.getInt("STOCK"));
                rep.setPRECOMREP(rs.getDouble("PRECOMREP"));
                rep.setPREVENREP(rs.getDouble("PREVENREP"));
                listado.add(rep);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar RepuestosImpl " + e.getMessage());
        }
        return listado;
    }
    
    public boolean existe(Repuestos modelo, List<Repuestos> listaModelo) {
        for (Repuestos rep : listaModelo) {
            if (modelo.getNOMREP().equals(rep.getNOMREP())) {
                return true;
            }
        }
        return false;
    }

    
}
