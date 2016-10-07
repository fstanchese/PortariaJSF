package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.AndarDAO;
import br.com.portaria.dao.BlocoDAO;
import br.com.portaria.dao.MoradorTipoDAO;
import br.com.portaria.dao.UnidadeDAO;
import br.com.portaria.dao.UnidadeTipoDAO;
import br.com.portaria.model.Andar;
import br.com.portaria.model.Bloco;
import br.com.portaria.model.MoradorTipo;
import br.com.portaria.model.Unidade;
import br.com.portaria.model.UnidadeTipo;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UnidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String acao;
	private Unidade unidadeCadastro;
	private List<Unidade> unidades;
	private List<Unidade> unidadesFiltrados;
	private UnidadeDAO dao = new UnidadeDAO();
	private List<UnidadeTipo> unidadeTipos;
	private List<MoradorTipo> moradorTipos;
	private List<Bloco> blocos;
	private List<Andar> andares;
	
	public List<UnidadeTipo> getUnidadeTipos() {
		return unidadeTipos;
	}

	public void setUnidadeTipos(List<UnidadeTipo> unidadeTipos) {
		this.unidadeTipos = unidadeTipos;
	}

	public List<MoradorTipo> getMoradorTipos() {
		return moradorTipos;
	}

	public void setMoradorTipos(List<MoradorTipo> moradorTipos) {
		this.moradorTipos = moradorTipos;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public List<Andar> getAndares() {
		return andares;
	}

	public void setAndares(List<Andar> andares) {
		this.andares = andares;
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

	public Unidade getUnidadeCadastro() {
		return unidadeCadastro;
	}

	public void setUnidadeCadastro(Unidade unidadeCadastro) {
		this.unidadeCadastro = unidadeCadastro;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	public List<Unidade> getUnidadesFiltrados() {
		return unidadesFiltrados;
	}

	public void setUnidadesFiltrados(List<Unidade> unidadesFiltrados) {
		this.unidadesFiltrados = unidadesFiltrados;
	}
	
	public void limpar() {
		this.unidadeCadastro = new Unidade();
	}
	
	public void carregarLista() {
		try {
			this.unidades = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}

	public void carregarDados() {
		try {
			UnidadeTipoDAO daoU = new UnidadeTipoDAO();
			MoradorTipoDAO daoM = new MoradorTipoDAO();
			BlocoDAO blocoDAO = new BlocoDAO();
			AndarDAO andarDAO = new AndarDAO();

			this.unidadeTipos = daoU.listar();
			this.moradorTipos = daoM.listar();
			this.blocos = blocoDAO.listar();
			this.andares = andarDAO.listar();

			if (this.id != null) {
				this.unidadeCadastro = dao.buscaPorId(this.id);
			} else {
				this.unidadeCadastro = new Unidade();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter uma Unidade: " + e.getMessage());
		}
	}

	public void salvar() {
		try {
			dao.salvar(this.unidadeCadastro);
			this.unidadeCadastro = new Unidade();
			FacesUtil.adicionarMsgInfo("Unidade cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar uma Unidade: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.unidadeCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar uma Unidade: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.unidadeCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir uma Unidade: " + e.getMessage());
		}
	}

}
