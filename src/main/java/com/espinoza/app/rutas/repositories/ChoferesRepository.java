package com.espinoza.app.rutas.repositories;

import com.espinoza.app.rutas.models.Chofer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoferesRepository implements IRepository<Chofer>{

    //atributos
    private Connection conn;

    //constructor
    public ChoferesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Chofer> listar() throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *  FROM CHOFERES")) {
            while (rs.next()){
                Chofer a = this.getChofer(rs);
                choferes.add(a);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return choferes;
    }

    @Override
    public Chofer getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Chofer chofer) throws SQLException {
        String sql = "";
        if (chofer.getId() != null && chofer.getId() > 0) {
            sql = "Update choferes set nombre=?, ap_paterno=?, " +
                    "ap_materno=?, licencia=?, telefono=?, " +
                    "fecha_nacimiento=?, disponibilidad=? " +
                    "where id_chofer=?";
        } else {
            sql = "insert into choferes(id_chofer, nombre, " +
                    "ap_paterno, ap_materno, licencia, telefono, " +
                    "fecha_nacimiento, disponibilidad )" +
                    "values (-1, ?, ?, ?, ?, ?, ?, ?)"; //los signos de interrogacion es por cada columnna de la tabla de BD
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if (chofer.getId() != null && chofer.getId() > 0) {
                stmt.setString(1, chofer.getNombre());
                stmt.setString(2, chofer.getApPaterno());
                stmt.setString(3, chofer.getApMaterno());
                stmt.setString(4, chofer.getLicencia());
                stmt.setString(5, chofer.getTelefono());
                stmt.setDate(6, Date .valueOf(chofer.getFechaNacimiento()));
                stmt.setLong(7, chofer.getDisponibilidad() ? 1 : 0);
                stmt.setLong(8, chofer.getId());
            } else {
                stmt.setString(1, chofer.getNombre());
                stmt.setString(2, chofer.getApPaterno());
                stmt.setString(3, chofer.getApMaterno());
                stmt.setString(4, chofer.getLicencia());
                stmt.setString(5, chofer.getTelefono());
                stmt.setDate(6, Date .valueOf(chofer.getFechaNacimiento()));
                stmt.setLong(7, chofer.getDisponibilidad() ? 1 : 0);
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    //mapear/transformar un renglon /fila/registro/ row en un objeto de tipo chofer

    private Chofer getChofer(ResultSet rs) throws SQLException {
        Chofer a = new Chofer();
        a.setId(rs.getLong("ID_CHOFER"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApPaterno(rs.getString("AP_PATERNO"));
        a.setApMaterno(rs.getString("AP_Materno"));
        a.setLicencia(rs.getString("Licencia"));
        a.setTelefono(rs.getString("TELEFONO"));
        a.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        a.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return a;
    }
}
