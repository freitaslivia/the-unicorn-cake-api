package br.com.gs.unicorncake.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.gs.unicorncake.beans.Alergia;
import br.com.gs.unicorncake.bo.CadastrarAlergia;
import br.com.gs.unicorncake.conexao.AlergiaDao;
import br.com.gs.unicorncake.conexao.UsuarioDao;
import br.com.gs.unicorncake.dto.AlergiaDTO;
import br.com.gs.unicorncake.dto.ConsultaDTO;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioAlergia;
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

@Path("alergias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlergiaApi {
	private RepositorioAlergia repositorioAlergia;
	private RepositorioUsuario repositorioUsuario;
	private CadastrarAlergia cadastrarAlergia;

	public AlergiaApi() {
		repositorioUsuario = new UsuarioDao();
		repositorioAlergia = new AlergiaDao();
		cadastrarAlergia = new CadastrarAlergia(repositorioAlergia, repositorioUsuario);
	}
	
	/*
	 * Para testar o post coloque esse json:
	 * {"cpf":"111.111.111.13", "tipo":"x", "descricaoGrau":"x"}
	 * na url coloca: http://localhost:8080/alergias/
	 * 
	 */
	@POST
	public Response cadastrarAlergia(AlergiaDTO alergia)
			throws ErroInfraestruturaException, URISyntaxException, DadosInvalidosException {
		try {

			this.cadastrarAlergia.cadastrar(alergia.getCpf(), alergia.getTipo(), alergia.getDescricaoGrau());
			return Response.created(new URI("alergias/" + alergia.getCpf())).build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/alergias/id/1
	 * 
	 */
	@GET
	@Path("id/{idAlergia}")
	public Response obterAlergiaPorID(@PathParam("idAlergia") int idAlergia) throws ErroInfraestruturaException {
		try {
			Alergia alergia = this.cadastrarAlergia.buscarPorIdAlergia(idAlergia);
			if (alergia != null) {
				return Response.ok(alergia).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Alergia não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/alergias/1
	 * 
	 */
	@DELETE
	@Path("{idAlergia}")
	public Response deletarAlergiaPorID(@PathParam("idAlergia") int idAlergia)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			int retorno = this.cadastrarAlergia.deletar(idAlergia);
			if (retorno > 0) {
				return Response.ok(retorno + " alergia(s) deletado(s)").build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("alergia não deletado").build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/alergias/111.111.111.11
	 * 
	 */
	@GET
	@Path("{cpf}")
	public Response obterAlergiaPorCPF(@PathParam("cpf") String cpf) throws ErroInfraestruturaException {
		try {
			List<Alergia> alergia = this.cadastrarAlergia.buscarPorCPF(cpf);
			if (alergia != null) {
				return Response.ok(alergia).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Alergia não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * Para testar o put coloque esse json:
	 * {"cpf":"111.111.111.13", "tipo":"x", "descricaoGrau":"x"}
	 * na url coloca: http://localhost:8080/alergias/
	 * 
	 */
	@PUT
	@Path("{idAlergia}")
	public Response atualizarAlergia(@PathParam("idAlergia") int idAlergia, AlergiaDTO alergia)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			this.cadastrarAlergia.atualizar(alergia.getCpf(), idAlergia, alergia.getTipo(), alergia.getDescricaoGrau());
			;
			;
			return Response.ok("Alergia atualizado com sucesso").build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
}