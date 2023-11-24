package br.com.gs.unicorncake.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.gs.unicorncake.beans.Alergia;
import br.com.gs.unicorncake.beans.Consulta;
import br.com.gs.unicorncake.beans.Exame;
import br.com.gs.unicorncake.bo.CadastrarConsulta;
import br.com.gs.unicorncake.conexao.ConsultaDao;
import br.com.gs.unicorncake.conexao.UsuarioDao;
import br.com.gs.unicorncake.dto.ConsultaDTO;
import br.com.gs.unicorncake.dto.ExameDTO;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioConsulta;
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

@Path("consultas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaApi {
	private RepositorioConsulta repositorioConsulta;
	private RepositorioUsuario repositorioUsuario;
	private CadastrarConsulta cadastrarConsulta;

	public ConsultaApi() {
		repositorioUsuario = new UsuarioDao();
		repositorioConsulta = new ConsultaDao();
		cadastrarConsulta = new CadastrarConsulta(repositorioConsulta, repositorioUsuario);
	}


	/*
	 * Para testar o post coloque esse json:
	 * {"cpf" : "111.111.111.13", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
	 * na url coloca: http://localhost:8080/consultas/
	 * 
	 */
	@POST
	public Response cadastrarConsulta(ConsultaDTO consulta)
			throws ErroInfraestruturaException, URISyntaxException, DadosInvalidosException {
		try {

			this.cadastrarConsulta.cadastrar(consulta.getCpf(), consulta.getLugar(), consulta.getDataHoraInicio());
			return Response.created(new URI("consultas/" + consulta.getCpf())).build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/consultas/id/1
	 * 
	 */
	@GET
	@Path("id/{idConsulta}")
	public Response obterConsultaPorID(@PathParam("idConsulta") int idConsulta) throws ErroInfraestruturaException {
		try {
			Consulta consulta = this.cadastrarConsulta.buscarPorIdConsulta(idConsulta);
			if (consulta != null) {
				return Response.ok(consulta).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/consultas/1
	 * 
	 */
	@DELETE
	@Path("{idConsulta}")
	public Response deletarConsultaPorID(@PathParam("idConsulta") int idConsulta)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			int retorno = this.cadastrarConsulta.deletar(idConsulta);
			if (retorno > 0) {
				return Response.ok(retorno + " consulta(s) deletado(s)").build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("consulta não deletado").build();

		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/consultas/111.111.111.11
	 * 
	 */
	@GET
	@Path("{cpf}")
	public Response obterConsultaPorCPF(@PathParam("cpf") String cpf) throws ErroInfraestruturaException {
		try {
			List<Consulta> consulta = this.cadastrarConsulta.buscarPorCPF(cpf);
			if (consulta != null) {
				return Response.ok(consulta).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Consulta não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
	
	
	/*
	 * Para testar o put coloque esse json:
	 * {"cpf" : "111.111.111.13", "lugar" : "x", "dataHoraInicio":"2023-11-21T14:30:45"}
	 * na url coloca: http://localhost:8080/consultas/
	 * 
	 */
	@PUT
	@Path("{idConsulta}")
	public Response atualizarConsulta(@PathParam("idConsulta") int idConsulta, ConsultaDTO consulta)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			this.cadastrarConsulta.atualizar(consulta.getCpf(), idConsulta, consulta.getLugar(),
					consulta.getDataHoraInicio());
			;
			return Response.ok("Consulta atualizado com sucesso").build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

}