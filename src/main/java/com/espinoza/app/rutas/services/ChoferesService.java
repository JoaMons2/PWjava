package com.espinoza.app.rutas.services;

import com.espinoza.app.rutas.models.Chofer;
import com.espinoza.app.rutas.repositories.ChoferesRepository;
import com.espinoza.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChoferesService implements IService<Chofer>{

    //atributos
    private IRepository<Chofer> choferesRepo;

    public ChoferesService(Connection conn) {
        choferesRepo = new ChoferesRepository(conn);
    }

    @Override
    public List<Chofer> listar() {
        try {
            return choferesRepo.listar();
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Chofer> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Chofer chofer) {
        try {
            choferesRepo.guardar(chofer);
        }
        catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {

    }
}
