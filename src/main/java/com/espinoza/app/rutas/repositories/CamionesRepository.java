package com.espinoza.app.rutas.repositories;

import com.espinoza.app.rutas.models.Camion;
import com.espinoza.app.rutas.models.enums.Marcas;
import com.espinoza.app.rutas.models.enums.Tipos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionesRepository implements IRepository<Camion>{

    //ATRIBUTOS
    private Connection conn;

    //CONSTRUCTOR
    public CamionesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Camion> listar() throws SQLException {
        List<Camion> camiones = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CAMIONES")){
            while (rs.next()){
                Camion b = this.getCamion(rs);
                camiones.add(b);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return camiones;
    }

    @Override
    public Camion getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Camion camion) throws SQLException {
        String sql = "";
        if (camion.getId() != null && camion.getId() > 0) {
            sql = "Update camiones set matricula=?, tipoCamion=?, " +
                    "modelo=?, marca=?, capacidad=?, " +
                    "kilometraje=?, disponibilidad=? " +
                    "where id_camion=?";
        } else {
            sql = "insert into camiones(id_camion, matricula, " +
                    "tipoCamion, modelo, marca, capacidad, " +
                    "kilometraje, disponibilidad )" +
                    "values (-1, ?, ?, ?, ?, ?, ?, ?)"; //los signos de interrogacion es por cada columnna de la tabla de BD
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            if (camion.getId() != null && camion.getId() > 0) {
                stmt.setString(1, camion.getMatricula());
                stmt.setString(2, camion.getTipoCamion().name());
                stmt.setInt(3, camion.getModelo());
                stmt.setString(4, camion.getMarca().name());
                stmt.setInt(5, camion.getCapacidad());
                stmt.setDouble(6, camion.getKilometraje());
                stmt.setLong(7, camion.getDisponibilidad() ? 1 : 0);
                stmt.setLong(8, camion.getId());
            } else {
                stmt.setString(1, camion.getMatricula());
                stmt.setString(2, camion.getTipoCamion().name());
                stmt.setInt(3, camion.getModelo());
                stmt.setString(4, camion.getMarca().name());
                stmt.setInt(5, camion.getCapacidad());
                stmt.setDouble(6, camion.getKilometraje());
                stmt.setLong(7, camion.getDisponibilidad() ? 1 : 0);
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    //mapear/transformar un renglon /fila/registro/ row en un objeto de tipo camion

    private Camion getCamion(ResultSet rs) throws SQLException {
        Camion b = new Camion();
        b.setId(rs.getLong("ID_CAMION"));
        b.setMatricula(rs.getString("MATRICULA"));
        ////
        String tipoCami = rs.getString("TIPO_CAMION");
        Tipos tipoCamion = Tipos.valueOf(tipoCami.toUpperCase());
        b.setTipoCamion(tipoCamion);
        //////
        b.setModelo(rs.getInt("MODELO"));
        ////
        String marcaCami = rs.getString("MARCA");
        Marcas marca = Marcas.valueOf(marcaCami.toUpperCase());
        b.setMarca(marca);
        //////
        b.setCapacidad(rs.getInt("CAPACIDAD"));
        b.setKilometraje(rs.getDouble("KILOMETRAJE"));
        b.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return b;
    }
}
