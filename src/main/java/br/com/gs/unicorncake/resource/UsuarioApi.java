package br.com.gs.unicorncake.resource;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.gs.unicorncake.beans.Usuario;
import br.com.gs.unicorncake.bo.CadastrarUsuario;
import br.com.gs.unicorncake.conexao.UsuarioDao;
import br.com.gs.unicorncake.dto.LogarDTO;
import br.com.gs.unicorncake.dto.MedicamentoDTO;
import br.com.gs.unicorncake.dto.UsuarioDTO;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioApi {
	private RepositorioUsuario repositorioUsuario;
	private CadastrarUsuario cadastrarUsuario;

	public UsuarioApi() {

		repositorioUsuario = new UsuarioDao();
		cadastrarUsuario = new CadastrarUsuario(repositorioUsuario);
	}

	/*
	 * Para testar o get coloque essa url:
	 * 
	 * http://localhost:8080/usuarios/111.111.111.11
	 * 
	 * tem que colocar um cpf existente :) caso esse nao exista cria primeiro um no
	 * post.
	 */
	@GET
	@Path("{cpf}")
	public Response obterUsuarioPorCpf(@PathParam("cpf") String cpf) throws ErroInfraestruturaException {
		try {
			Usuario usuario = this.repositorioUsuario.buscarPorCPF(cpf);
			if (usuario != null) {
				return Response.ok(usuario).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * Para testar o post coloque esse json:
	 * {"nome":"x","email":"xxx","cpf":"111.111.111.13", "tpSangue" : "O+", "idade"
	 * : 18, "genero" : "f", "peso" : 60, "altura" : 1.70, "senha" : "x"}
	 * 
	 * na url coloca: http://localhost:8080/usuarios/
	 * 
	 */
	@POST
	public Response cadastrarNovoUsuario(UsuarioDTO usuario)
			throws ErroInfraestruturaException, URISyntaxException, DadosInvalidosException {
		try {

			this.cadastrarUsuario.cadastrar(usuario.getNome(), usuario.getEmail(), usuario.getCpf(),
					usuario.getTpSangue(), usuario.getIdade(), usuario.getGenero(), usuario.getPeso(),
					usuario.getAltura(), usuario.getSenha());
			return Response.created(new URI("usuarios/" + usuario.getCpf())).build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}


	/*
	 * Para testar coloque essa url:
	 * 
	 * http://localhost:8080/usuarios/logar
	 * 
	 * json:
	 * {"cpf":"111.111.111.13", "senha" : "x"}
	 */
	@POST
	@Path("logar")
	public Response logarUsuario(LogarDTO logar)
			throws ErroInfraestruturaException, URISyntaxException, DadosInvalidosException {
		try {

			this.cadastrarUsuario.logar(logar.getCpf(), logar.getSenha());
			return Response.created(new URI("usuarios/" + logar.getCpf())).build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
	
	/*
	 * Para testar o put coloque esse json:
	 * {"nome":"x","email":"xxx","cpf":"111.111.111.13", "tpSangue" : "O+", "idade"
	 * : 18, "genero" : "f", "peso" : 60, "altura" : 1.70, "senha" : "x"}
	 * 
	 * na url coloca: http://localhost:8080/usuarios/1
	 * 
	 */
	@PUT
	@Path("{idUsuario}")
	public Response atualizarUsuario(@PathParam("idUsuario") int idUsuario, UsuarioDTO usuario)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			this.cadastrarUsuario.atualizar(idUsuario, usuario.getNome(), usuario.getEmail(), usuario.getCpf(),
					usuario.getTpSangue(), usuario.getIdade(), usuario.getGenero(), usuario.getPeso(),
					usuario.getAltura(), usuario.getSenha());
			return Response.ok("Usuario atualizado com sucesso").build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

}