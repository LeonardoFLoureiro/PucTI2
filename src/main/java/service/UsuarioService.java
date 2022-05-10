package service;

import dao.UsuariosDao;

import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {
	private UsuariosDao usuariodao = new UsuariosDao();
	public UsuarioService() {
		usuariodao.conectar();
	}
	
	public Object add(Request request, Response response) {
		String cpf = request.queryParams("cpf");
		String primeironome = request.queryParams("primeironome");
		String segundonome = request.queryParams("segundonome");
		String senha =  request.queryParams("senha");
		String datanascimento =  request.queryParams("datanascimento");
		int confiabilidade = Integer.parseInt(request.queryParams("confiabilidade"));
		

		Usuario usuario = new Usuario(cpf, primeironome, segundonome, senha, datanascimento, confiabilidade);
       usuariodao.inserirUsuario(usuario);
       
		
		response.status(201); // 201 Created
		return cpf;
	}
	
	
	public Object get(Request request, Response response) {
		String cpf = request.params("cpf");
		
		Usuario usuario = (Usuario) usuariodao.getUsuario(cpf);
		
		if (usuario != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "USUARIO\n" + 
            		"\t CPF =" + usuario.getCpf() + "\n" +
            		"\t NOME =" + usuario.getPrimeiroNome() + " " + usuario.getSegundoNome() + "\n" +
            		"\t SENHA =" + usuario.getSenha() + "\n" +
            		"\t Data de Nascimento = " + usuario.getDataNascimento() + "\n" +
            		"\t Confiabilidade" + usuario.getConfiabilidade() + "\n" 
            		;
        } else {
            response.status(404); // 404 Not found
            return "Usuario " + cpf + " não encontrado.";
        }

	}
	public Object update(Request request, Response response) {
        String cpf = request.params("cpf");
        
		Usuario usuario = (Usuario) usuariodao.getUsuario(cpf);

        if (usuario != null) {
        	usuario.setCpf(request.queryParams("cpf"));
        	usuario.setPrimeiroNome(request.queryParams("primeironome"));
        	usuario.setSegundoNome(request.queryParams("segundo nome"));
        	usuario.setSenha(request.queryParams("senha"));
        	usuario.setDataNascimento(request.queryParams("datanascimento"));
        	usuario.setConfiabilidade(Integer.parseInt(request.queryParams("confiabilidade")));

        	usuariodao.atualizarUsuario(usuario);
        	
            return cpf;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        String cpf = request.params("cpf");

        Usuario usuario = (Usuario) usuariodao.getUsuario(cpf);

        if (usuario != null) {

            usuariodao.excluirUsuario(cpf);

            response.status(200); // success
        	return cpf;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }
	}
	
	
	
	
	
}
