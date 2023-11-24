package br.com.gs.unicorncake.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.gs.unicorncake.beans.Medicamento;
import br.com.gs.unicorncake.bo.CadastrarMedicamento;
import br.com.gs.unicorncake.conexao.MedicamentoDao;
import br.com.gs.unicorncake.conexao.UsuarioDao;
import br.com.gs.unicorncake.dto.MedicamentoDTO;
import br.com.gs.unicorncake.excecoes.DadosInvalidosException;
import br.com.gs.unicorncake.excecoes.ErroInfraestruturaException;
import br.com.gs.unicorncake.repositorio.RepositorioMedicamento;
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

@Path("medicamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicamentoApi {
	private RepositorioMedicamento repositorioMedicamento;
	private RepositorioUsuario repositorioUsuario;
	private CadastrarMedicamento cadastrarMedicamento;

	public MedicamentoApi() {
		repositorioUsuario = new UsuarioDao();
		repositorioMedicamento = new MedicamentoDao();
		cadastrarMedicamento = new CadastrarMedicamento(repositorioMedicamento, repositorioUsuario);
	}

	/*
	 * Para testar o post coloque esse json:
	 * {"cpf":"111.111.111.13", "nome":"x", "dosagem":"x", "viaDeAdmin" : "oral",
	 * "dataHoraInicio" : "2023-11-21T14:30:45", "quantidadeDias" : 3}
	 * 
	 * na url coloca: http://localhost:8080/medicamentos/
	 * 
	 */
	@POST
	public Response cadastrarMedicamento(MedicamentoDTO medicamento)
			throws ErroInfraestruturaException, URISyntaxException, DadosInvalidosException {
		try {

			this.cadastrarMedicamento.cadastrar(medicamento.getCpf(), medicamento.getNome(), medicamento.getDosagem(),
					medicamento.getViaDeAdmin(), medicamento.getDataHoraInicio(), medicamento.getQuantidadeDias());
			return Response.created(new URI("medicamentos/" + medicamento.getCpf())).build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
	
	
	/*
	 * 
	 * na url coloca: http://localhost:8080/medicamentos/id/1
	 * 
	 */
	@GET
	@Path("id/{idMedicamento}")
	public Response obterMedicamentoPorID(@PathParam("idMedicamento") int idMedicamento)
			throws ErroInfraestruturaException {
		try {
			Medicamento medicamento = this.cadastrarMedicamento.buscarPorIdMedicamento(idMedicamento);
			if (medicamento != null) {
				return Response.ok(medicamento).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Medicamento não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/medicamentos/1
	 * 
	 */
	@DELETE
	@Path("{idMedicamento}")
	public Response deletarMedicamentoPorID(@PathParam("idMedicamento") int idMedicamento)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			int retorno = this.cadastrarMedicamento.deletar(idMedicamento);
			if (retorno > 0) {
				return Response.ok(retorno + " medicamento(s) deletado(s)").build();
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Medicamento não deletado").build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * na url coloca: http://localhost:8080/medicamentos/111.111.111.11
	 * 
	 */
	@GET
	@Path("{cpf}")
	public Response obterMedicamnetoPorCPF(@PathParam("cpf") String cpf) throws ErroInfraestruturaException {
		try {
			List<Medicamento> medicamento = this.cadastrarMedicamento.buscarPorCPF(cpf);
			if (medicamento != null) {
				return Response.ok(medicamento).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Medicamento não encontrado").build();
			}
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
	
	/*
	 * Para testar o put coloque esse json:
	 * {"cpf":"111.111.111.13", "nome":"x", "dosagem":"x", "viaDeAdmin" : "oral",
	 * "dataHoraInicio" : "2023-11-21T14:30:45", "quantidadeDias" : 3}
	 * 
	 * na url coloca: http://localhost:8080/medicamentos/1
	 * 
	 */
	@PUT
	@Path("{idMedicamento}")
	public Response atualizarMedicamento(@PathParam("idMedicamento") int idMedicamento, MedicamentoDTO medicamento)
			throws ErroInfraestruturaException, DadosInvalidosException {
		try {
			this.cadastrarMedicamento.atualizar(medicamento.getCpf(), idMedicamento, medicamento.getNome(),
					medicamento.getDosagem(), medicamento.getViaDeAdmin(), medicamento.getDataHoraInicio(),
					medicamento.getQuantidadeDias());
			return Response.ok("Medicamento atualizado com sucesso").build();

		} catch (DadosInvalidosException ex) {
			return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
		} catch (ErroInfraestruturaException ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

}
