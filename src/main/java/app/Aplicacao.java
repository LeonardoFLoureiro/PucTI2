package app;
import static spark.Spark.*;

import service.UsuarioService;

public class Aplicacao {
private static UsuarioService usuarioService = new UsuarioService();
	
    public static void main(String[] args) {
        port(6789);

        post("/usuario", (request, response) -> usuarioService.add(request, response));

        get("/produto/:id", (request, response) -> usuarioService.get(request, response));

        get("/produto/update/:id", (request, response) -> usuarioService.update(request, response));

        get("/produto/delete/:id", (request, response) -> usuarioService.remove(request, response));

               
    }

}
