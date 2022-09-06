package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;
import modelo.Motos;
//import modelo.Cliente;
//import modelo.Empleado;
import modelo.Compra;
import modelo.CompraDetalle;

public class CompraImpl extends Conexion implements ICRUD<Compra> {

    DateFormat formato = new SimpleDateFormat("dd-MM-YYYY");

    @Override
    public void registrar(Compra com) throws Exception {
        try {
            String sql = "insert into COMPRA"
                    + " (FECCOM,FOR_PAG,IDPRO,IDEMP)"
                    + " values (?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(com.getFECCOM()));
            ps.setString(2, com.getFOR_PAG());
            ps.setInt(3, com.getIDPRO());
            ps.setInt(4, 3);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar CompraImpl: " + e.getMessage());
        }

    }

    @Override
    public void modificar(Compra com) throws Exception {
        String sql = "update COMPRA set FECCOM=?,FORPAGCOM=? WHERE IDPRO=?, IDEMP=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
    ps.setString(1, formato.format(com.getFOR_PAG()));
            ps.setString(2, com.getFOR_PAG());
            ps.setInt(3, com.getIDPRO());
            ps.setInt(4, com.getIDEMP());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar CompraImpl: " + e.getMessage());
        }

    }

    @Override
    public void eliminar(Compra generic) throws Exception {

    }

    public void filtrarProveedor(Proveedor pro) throws Exception {
        try {
            String sql = "select * from Proveedores where RUCPRO =" + pro.getRUCPRO();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pro.setIDPRO(rs.getInt("IDPRO"));
                pro.setNOMPRO(rs.getString("NOMPRO"));
                pro.setRUCPRO(rs.getString("RUCPRO"));
                pro.setDIRPRO(rs.getString("DIRPRO"));
                pro.setPROPRO(rs.getString("PROPRO"));
                pro.setCELPRO(rs.getString("CELPRO")); 

            }
        } catch (Exception e) {
        }

    }

    @Override
    public List<Compra> listar() throws Exception {

        List<Compra> listado = null;
        Compra com;
        String sql = "select * from Compra";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
//            Cliente cliente = new Cliente();
//            Empleado empleado = new Empleado();
                com = new Compra();
                com.setIDCOM(rs.getInt("IDCOM"));
                com.setFECCOM(rs.getDate("FECCOM"));
                com.setFOR_PAG(rs.getString("FOR_PAG"));
                com.setIDPRO(rs.getInt("IDPRO"));
                com.setIDEMP(rs.getInt("IDEMP"));
                listado.add(com);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar CompraImpl " + e.getMessage());
        }
        return listado;

    }

    public void mostrarDatos(Motos mot) throws Exception {
        try {
            String sql = "Select * from MOTOS where MODMOT =?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, mot.getMODMOT());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mot.setIDMOT(rs.getInt("IDMOT"));
                mot.setCATMOT(rs.getString("CATMOT"));
                mot.setMARMOT(rs.getString("MARMOT"));
                mot.setMODMOT(rs.getString("MODMOT"));
                mot.setCOLMOT(rs.getString("COLMOT"));
                mot.setGARMOT(rs.getString("GArMOT"));
                mot.setSTOCK(rs.getInt("STOCK"));
                mot.setPRECOMMOT(rs.getDouble("PRECOMMOT"));
                mot.setPREVENMOT(rs.getDouble("PREVENMOT"));
                ;
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en mostrar datos" + e.getMessage());
        }
    }

    public List<String> autocompletar(String consulta) throws Exception {

        Motos mot = new Motos();
        List<String> listado = new ArrayList<>();
        String sql = "Select * from MOTOS WHERE MODMOT LIKE ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                mot.setMODMOT(rs.getString("MODMOT"));
                listado.add(rs.getString("MODMOT"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en autocompletar Producto " + e.getMessage());
        }
        return listado;

    }

    public int ventasMaximas() throws Exception {
        int manuel = 0;
        String sql = "SELECT MAX(IDCOM) FROM COMPRA";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                manuel = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error en Compras Maximas" + e.getMessage());
        }
        return manuel;

    }

    public void registrarVentaDetalle(CompraDetalle Comdet) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "INSERT INTO COMPRA_DETALLE"
                + "(CANTCOMDET,IDCOM,IDMOT,IDREP)"
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Comdet.getCANTCOMDET());
            ps.setInt(2, Comdet.getIDCOM());
            ps.setInt(3, Comdet.getIDMOT());
            ps.setInt(4, 2);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar CompraDetalleImpl " + e.getMessage());
        }
    }
}
