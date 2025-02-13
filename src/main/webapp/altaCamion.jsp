<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*"%>

<%
Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta Camion</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
    integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
    crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Choferes<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/choferes/listar">Lista Choferes</a></li>
                            <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Chofer</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Camiones<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/camiones/listar">Lista Camiones</a></li>
                            <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camion</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Rutas<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Ruta</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-6">
                <h2>Formulario alta Camion</h2>
            </div>
            <div class="col-6">
                <a href="<%=request.getContextPath()%>/camiones/alta" class="btn btn-success">Alta Camion</a>
            </div>
        </div>
        <br>
        <% if(errores != null && errores.size()>0){ %>
            <ul class="alert alert-danger mx-5 px-5">
                <% for(String error: errores.values()) { %>
                    <li><%=error%></li>
                    <% } %>
            </ul>
            <% } %>

            <div class="row">
                <form action="<%=request.getContextPath()%>/camiones/alta" method="post">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="">Matricula</label>
                            <input type="text" name="matricula" id="" class="form-control" value="${param.matricula}">
                            <%
                            if(errores != null && errores.containsKey("matricula")){
                                out.println("<span class='text-danger'>"+ errores.get("matricula") + "</span>");
                            }
                            %>
                        </div>


                        <div class="form-group">
                            <label for="">Tipo</label>
                            <input type="text" name="tipoCamion" id="" class="form-control" value="${param.tipoCamion}">
                            <%
                            if(errores != null && errores.containsKey("tipoCamion")){
                                out.println("<span class='text-danger'>"+ errores.get("tipoCamion") + "</span>");
                            }
                            %>
                        </div>


                        <div class="form-group">
                            <label for="">Modelo</label>
                            <input type="text" name="modelo" id="" class="form-control" value="${param.modelo}">
                            <%
                            if(errores != null && errores.containsKey("modelo")){
                                out.println("<span class='text-danger'>" + errores.get("modelo") + "</span>");
                            }
                            %>
                        </div>


                        <div class="form-group">
                            <label for="">Marca</label>
                            <input type="text" name="marca" id="" class="form-control" value="${param.marca}">
                            <%
                            if(errores != null && errores.containsKey("marca")){
                                out.println("<span class='text-danger'>"+ errores.get("marca") + "</span>");
                            }
                            %>
                        </div>


                        <div class="form-group">
                            <label for="">Capacidad</label>
                            <input type="text" name="capacidad" id="" class="form-control" value="${param.capacidad}">
                            <%
                            if(errores != null && errores.containsKey("capacidad")){
                                out.println("<span class='text-danger'>"+ errores.get("capacidad") + "</span>");
                            }
                            %>
                        </div>


                        <div class="form-group">
                            <label for="">Kilometraje</label>
                            <input type="text" name="kilometraje" id="" class="form-control" value="${param.kilometraje}">
                            <%
                            if(errores != null && errores.containsKey("kilometraje")){
                                out.println("<span class='text-danger'>"+ errores.get("kilometraje") + "</span>");
                            }
                            %>
                        </div>


                        <div class="form-group">
                            <label for="">Disponibilidad</label>
                            <input type="checkbox" name="disponibilidad" id="" class="form-check-input" value="" checked>
                        </div>


                        <div class="form-group">
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </div>
                    </div>
                </form>
            </div>
    </div>
</body>
</html>