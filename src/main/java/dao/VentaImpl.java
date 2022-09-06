package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Motos;
//import modelo.Cliente;
//import modelo.Empleado;
import modelo.Venta;
import modelo.VentaDetalle;

public class VentaImpl extends Conexion implements ICRUD<Venta> {

    DateFormat formato = new SimpleDateFormat("YYYY-MM-dd");

    @Override
    public void registrar(Venta ven) throws Exception {
        try {
            String sql = "insert into VENTA"
                    + " (FECVEN,FOR_PAG,IDCLI,IDEMP)"
                    + " values (?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(ven.getFECVEN()));
            ps.setString(2, ven.getFOR_PAG());
            ps.setInt(3, ven.getIDCLI());
            ps.setInt(4, 3);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar Ventaimpl: " + e.getMessage());
        }

    }

    @Override
    public void modificar(Venta ven) throws Exception {
        String sql = "update venta set FECVEN=?,FORPAGVEN=? WHERE IDCLI=?, IDEMP=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
    ps.setString(1, formato.format(ven.getFECVEN()));
            ps.setString(2, ven.getFOR_PAG());
            ps.setInt(3, ven.getIDCLI());
            ps.setInt(4, ven.getIDEMP());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Modificar Cliente: " + e.getMessage());
        }

    }

    @Override
    public void eliminar(Venta generic) throws Exception {

    }

    public void filtrarCliente(Cliente cli) throws Exception {
        try {
            String sql = "select * from CLIENTE where DOCCLIE =" + cli.getDocclie();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cli.setIdcli(rs.getInt("IDCLI"));
                cli.setNomclie(rs.getString("NOMCLIE"));
                cli.setApeclie(rs.getString("APECLIE"));
                cli.setCelclie(rs.getString("CELCLIE"));
                cli.setDocclie(rs.getString("DOCCLIE"));
                cli.setEmaclie(rs.getString("EMACLIE"));
                cli.setEstclie(rs.getString("ESTCLIE"));

            }
        } catch (Exception e) {
        }

    }

    @Override
    public List<Venta> listar() throws Exception {

        List<Venta> listado = null;
        Venta ven;
        String sql = "select * from Venta";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
//            Cliente cliente = new Cliente();
//            Empleado empleado = new Empleado();
                ven = new Venta();
                ven.setIDVEN(rs.getInt("IDVEN"));
                ven.setFECVEN(rs.getDate("FECVEN"));
                ven.setFOR_PAG(rs.getString("FOR_PAG"));
                ven.setIDCLI(rs.getInt("IDCLI"));
                ven.setIDEMP(rs.getInt("IDEMP"));
                listado.add(ven);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar VentaImpl " + e.getMessage());
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
        String sql = "SELECT MAX(IDVEN) FROM VENTA";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                manuel = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error en ventas Maximas" + e.getMessage());
        }
        return manuel;

    }

    public void registrarVentaDetalle(VentaDetalle Vendet) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "INSERT INTO VENTA_DETALLE"
                + "(CANTVENDET,IDVEN,IDMOT,IDREP)"
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Vendet.getCANTVENDET());
            ps.setInt(2, Vendet.getIDVENT());
            ps.setInt(3, Vendet.getIDMOT());
            ps.setInt(4, 2);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar VentasDetalle " + e.getMessage());
        }
    }
}
