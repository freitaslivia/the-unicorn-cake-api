package br.com.gs.unicorncake.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.gs.unicorncake.beans.Consulta;
import br.com.gs.unicorncake.beans.Exame;
import br.com.gs.unicorncake.beans.Medicamento;
import br.com.gs.unicorncake.bo.CadastrarExame;
import br.com.gs.unicorncake.conexao.ExameDao;
import br.com.gs.unicorncake.conexao.UsuarioDao;
import br.com.gs.unicorncake.dto.ExameDTO;
import br.com.gs.unicorncake.dto.MedicamentoDTO;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioExame;
import br.com.gs.unicorncake.repositorio.RepositorioUsuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("exames")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExameApi {
	private RepositorioExame repositorioExame;
	private RepositorioUsuario repositorioUsuario;
	private CadastrarExame cadastrarExame;

	public ExameApi() {
		repositorioUsuario = new UsuarioDao();
		repositorioExame = new ExameDao();
		cadastrarExame = new CadastrarExame(repositorioExame, repositorioUsuario);
	}


	/*
	 * Para testar o post coloque esse json:
	 * {"cpf" : "111.111.111.13", "nome": "x", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
	 * na url coloca: http://localhost:8080/exames/
	 * 
	 */
	@POST
	public Response cadastrarExame(ExameDTO exame)
			throws ErroInfraestruturaException, URISyntaxException, DadosInvalidosException {
		try {

			this.cadastrarExame.cadastrar(exame.getCpf(), exame.getNome(), exame.getLugar(), exame.getDataHoraInicio());
			return Response.created(new URI("exames/" + exame.getCpf())).build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	
	/*
	 * na url coloca: http://localhost:8080/exames/id/1
	 * 
	 */
	@GET
	@Path("id/{idExame}")
	public Response obterExamePorID(@PathParam("idExame") int idExame) throws ErroInfraestruturaException {
		try {
			Exame exame = this.cadastrarExame.buscarPorIdExame(idExame);
			if (exame != null) {
				return Response.ok(exame).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/exames/1
	 * 
	 */
	@DELETE
	@Path("{idExame}")
	public Response deletarExamePorID(@PathParam("idExame") int idExame)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			int retorno = this.cadastrarExame.deletar(idExame);
			if (retorno > 0) {
				return Response.ok(retorno + " exame(s) deletado(s)").build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("exame não deletado").build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/exames/111.111.111.11
	 * 
	 */
	@GET
	@Path("{cpf}")
	public Response obterExamePorCPF(@PathParam("cpf") String cpf) throws ErroInfraestruturaException {
		try {
			List<Exame> exame = this.cadastrarExame.buscarPorCPF(cpf);
			if (exame != null) {
				return Response.ok(exame).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Exame não encontrado").build();
			}
		}  catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * Para testar o put coloque esse json:
	 * {"cpf" : "111.111.111.13", "nome": "x", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
	 * na url coloca: http://localhost:8080/exames/
	 * 
	 */
	@PUT
	@Path("{idExame}")
	public Response atualizarExame(@PathParam("idExame") int idExame, ExameDTO exame)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			this.cadastrarExame.atualizar(exame.getCpf(), idExame, exame.getNome(), exame.getLugar(),
					exame.getDataHoraInicio());
			;
			return Response.ok("Exame atualizado com sucesso").build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
}
