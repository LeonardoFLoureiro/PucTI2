package service;

import dao.LocalizacaoPostosDao;

import model.LocalizacaoPostos;
import spark.Request;
import spark.Response;

public class LocalizacaoPostosService {
	private LocalizacaoPostosDao locusdao = new LocalizacaoPostosDao();
	public LocalizacaoPostosService() {
		locusdao.conectar();
	}
	
	public Object add(Request request, Response response) {
		int posto = Integer.parseInt(request.queryParams("posto"));
		String cidade = request.queryParams("cidade");
		String estado = request.queryParams("estado");
		String bairro =  request.queryParams("bairro");
		String rua =  request.queryParams("rua");
		int numero = Integer.parseInt(request.queryParams("numero"));
		
		int id_localizacao = locusdao.getMaxId() + 1;
		
		LocalizacaoPostos locus = new LocalizacaoPostos(posto,id_localizacao,cidade,estado,bairro,rua,numero);
       locusdao.inserirLocalizacaoPostos(locus);
       
		
		response.status(201); // 201 Created
		return id_localizacao;
	}
	
	
	public Object get(Request request, Response response) {
		int id_localizacao = Integer.parseInt(request.params(":id"));
		
		LocalizacaoPostos locus = (LocalizacaoPostos) locusdao.getLocalizacaoPostos(id_localizacao);
		
		if (locus != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "LocalizacaoPostos\n" + 
            		"\t posto =" + locus.getposto() + "\n" +
            		"\t id_localizacao =" + locus.getid_localizacao() +"\n" +
            		"\t cidade =" + locus.getcidade() + "\n" +
            		"\t estado = " + locus.getestado() + "\n" +
            		"\t bairro = " + locus.getbairro() + "\n" +
            		"\t rua = " + locus.getrua() + "\n" +
            		"\t numero" + locus.getnumero() + "\n" 
            		;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao " + id_localizacao + " não encontrado.";
        }

	}
	public Object update(Request request, Response response) {
		int id_localizacao = Integer.parseInt(request.params(":id"));
        
		LocalizacaoPostos locus = (LocalizacaoPostos) locusdao.getLocalizacaoPostos(id_localizacao);

        if (locus != null) {
        	locus.setposto(Integer.parseInt(request.queryParams("posto")));
        	locus.setcidade(request.queryParams("cidade"));
        	locus.setestado(request.queryParams("estado"));
        	locus.setbairro(request.queryParams("bairro"));
        	locus.setrua(request.queryParams("rua"));
        	locus.setnumero(Integer.parseInt(request.queryParams("numero")));

        	locusdao.atualizarLocalizacaoPostos(locus);
        	
            return id_localizacao;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
		int id_localizacao = Integer.parseInt(request.params(":id"));

        LocalizacaoPostos locus = (LocalizacaoPostos) locusdao.getLocalizacaoPostos(id_localizacao);

        if (locus != null) {

            locusdao.excluirLocalizacaoPostos(id_localizacao);

            response.status(200); // success
        	return id_localizacao;
        } else {
            response.status(404); // 404 Not found
            return "Localizacao não encontrado.";
        }
	}
	
	
	
	
	
}
