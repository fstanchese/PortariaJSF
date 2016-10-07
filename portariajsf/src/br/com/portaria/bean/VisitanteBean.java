package br.com.portaria.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.CorDAO;
import br.com.portaria.dao.MarcaDAO;
import br.com.portaria.dao.UnidadeDAO;
import br.com.portaria.dao.VisitanteDAO;
import br.com.portaria.model.Cor;
import br.com.portaria.model.Marca;
import br.com.portaria.model.Modelo;
import br.com.portaria.model.Unidade;
import br.com.portaria.model.Visitante;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VisitanteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long marcaId;
	private String acao;
	private Visitante visitanteCadastro;
	private VisitanteDAO dao = new VisitanteDAO();
	private List<Visitante> visitantes;
	private List<Visitante> visitantesFiltrados;
	private List<Marca> marcas;
	private List<Modelo> modelos;
	private List<Cor> cores;
	private List<Unidade> unidades;

	public void salvar() {
		try {
			this.visitanteCadastro.setEntrada(new Date(System.currentTimeMillis()));
			dao.salvar(this.visitanteCadastro);
			this.visitanteCadastro = new Visitante();
			FacesUtil.adicionarMsgInfo("Visitante cadastrado com sucesso !");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Visitante : " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.visitanteCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Visitante : " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.visitanteCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Visitante : " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.visitantes = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar solicitação : " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			CorDAO corDAO = new CorDAO();
			UnidadeDAO unidadeDAO = new UnidadeDAO();
			MarcaDAO marcaDAO = new MarcaDAO();
			
			this.cores = corDAO.listar();
			this.unidades = unidadeDAO.listar();
			this.marcas = marcaDAO.listar();
		
			if (this.id != null) {
				this.visitanteCadastro = dao.buscaPorId(this.id);
				if (this.marcaId == null) {
					this.marcaId = visitanteCadastro.getModelo().getMarca().getId();
				}
				this.modelos = marcaDAO.listarModelos(marcaId);		
			} else {
				this.modelos = marcaDAO.listarModelos(marcaId);		
				this.visitanteCadastro = new Visitante();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Visitante : " + e.getMessage());
		}
	}
	
	public void limpar() {
		this.visitanteCadastro = new Visitante();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Visitante getVisitanteCadastro() {
		return visitanteCadastro;
	}

	public void setVisitanteCadastro(Visitante visitanteCadastro) {
		this.visitanteCadastro = visitanteCadastro;
	}

	public List<Visitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(List<Visitante> visitantes) {
		this.visitantes = visitantes;
	}

	public List<Visitante> getVisitantesFiltrados() {
		return visitantesFiltrados;
	}

	public void setVisitantesFiltrados(List<Visitante> visitantesFiltrados) {
		this.visitantesFiltrados = visitantesFiltrados;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Cor> getCores() {
		return cores;
	}

	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
}
