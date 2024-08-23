package com.espinoza.app.rutas.controllers;

import com.espinoza.app.rutas.models.Camion;
import com.espinoza.app.rutas.models.enums.Marcas;
import com.espinoza.app.rutas.models.enums.Tipos;
import com.espinoza.app.rutas.repositories.CamionesRepository;
import com.espinoza.app.rutas.utils.ConexionBD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/camiones/alta")
public class AltaCamionServlet extends HttpServlet {

    private CamionesRepository camionesRepository;

    @Override
    public void init() throws ServletException {
        Connection conn = ConexionBD.getInstance();
        camionesRepository = new CamionesRepository(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/altaCamion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String tipoCamionStr = request.getParameter("tipoCamion");
        String modeloStr = request.getParameter("modelo");
        String marcaStr = request.getParameter("marca");
        String capacidadStr = request.getParameter("capacidad");
        String kilometrajeStr = request.getParameter("kilometraje");
        String disponibilidadStr = request.getParameter("disponibilidad");

        // Manejo de errores
        boolean hasErrors = false;

        // Validación de matrícula
        if (matricula == null || matricula.isBlank()) {
            request.setAttribute("errorMatricula", "La matrícula es requerida!");
            hasErrors = true;
        }

        // Conversión y validación de tipoCamion (Enumerado)
        Tipos tipoCamion = null;
        if (tipoCamionStr != null && !tipoCamionStr.isBlank()) {
            try {
                tipoCamion = Tipos.valueOf(tipoCamionStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorTipoCamion", "El tipo de camión es inválido!");
                hasErrors = true;
            }
        } else {
            request.setAttribute("errorTipoCamion", "El tipo de camión es requerido!");
            hasErrors = true;
        }

        // Conversión y validación de modelo (Integer)
        Integer modelo = null;
        if (modeloStr != null && !modeloStr.isBlank()) {
            try {
                modelo = Integer.valueOf(modeloStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorModelo", "El modelo es inválido!");
                hasErrors = true;
            }
        } else {
            request.setAttribute("errorModelo", "El modelo es requerido!");
            hasErrors = true;
        }

        // Conversión y validación de marca (Enumerado)
        Marcas marca = null;
        if (marcaStr != null && !marcaStr.isBlank()) {
            try {
                marca = Marcas.valueOf(marcaStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMarca", "La marca es inválida!");
                hasErrors = true;
            }
        } else {
            request.setAttribute("errorMarca", "La marca es requerida!");
            hasErrors = true;
        }

        // Conversión y validación de capacidad (Integer)
        Integer capacidad = null;
        if (capacidadStr != null && !capacidadStr.isBlank()) {
            try {
                capacidad = Integer.valueOf(capacidadStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorCapacidad", "La capacidad es inválida!");
                hasErrors = true;
            }
        } else {
            request.setAttribute("errorCapacidad", "La capacidad es requerida!");
            hasErrors = true;
        }

        // Conversión y validación de kilometraje (Double)
        Double kilometraje = null;
        if (kilometrajeStr != null && !kilometrajeStr.isBlank()) {
            try {
                kilometraje = Double.valueOf(kilometrajeStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorKilometraje", "El kilometraje es inválido!");
                hasErrors = true;
            }
        } else {
            request.setAttribute("errorKilometraje", "El kilometraje es requerido!");
            hasErrors = true;
        }

        // Conversión de disponibilidad (Boolean)
        Boolean disponibilidad = "on".equals(disponibilidadStr);

        // Si hay errores, reenvía al formulario con los mensajes de error
        if (hasErrors) {
            request.getRequestDispatcher("/altaCamion.jsp").forward(request, response);
            return;
        }

        // Crear el objeto Camion
        Camion camion = new Camion();
        camion.setMatricula(matricula);
        camion.setTipoCamion(tipoCamion);
        camion.setModelo(modelo);
        camion.setMarca(marca);
        camion.setCapacidad(capacidad);
        camion.setKilometraje(kilometraje);
        camion.setDisponibilidad(disponibilidad);

        // Guardar el objeto Camion en la base de datos
        try {
            camionesRepository.guardar(camion);
            response.sendRedirect("listaCamiones.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error al guardar el camión", e);
        }
    }
}

