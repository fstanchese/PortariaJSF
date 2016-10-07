package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.UnidadeTipoDAO;
import br.com.portaria.model.UnidadeTipo;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UnidadeTipoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private UnidadeTipo unidadeTipoCadastro;
	private UnidadeTipoDAO dao = new UnidadeTipoDAO();
	private List<UnidadeTipo> unidadeTipos;
	private List<UnidadeTipo> unidadeTiposFiltrados;

	public void salvar() {
		try {
			dao.salvar(this.unidadeTipoCadastro);
			this.unidadeTipoCadastro = new UnidadeTipo();
			FacesUtil.adicionarMsgInfo("Tipo de Unidade cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar Tipo de Unidade: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.unidadeTipoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar Tipo de Unidade: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.unidadeTipoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir Tipo de Unidade: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.unidadeTipos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.unidadeTipoCadastro = dao.buscaPorId(this.id);
			} else {
				this.unidadeTipoCadastro = new UnidadeTipo();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Tipo de Unidade: " + e.getMessage());
		}
	}

	public void limpar() {
		this.unidadeTipoCadastro = new UnidadeTipo();
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

	public UnidadeTipo getUnidadeTipoCadastro() {
		return unidadeTipoCadastro;
	}

	public void setUnidadeTipoCadastro(UnidadeTipo unidadeTipoCadastro) {
		this.unidadeTipoCadastro = unidadeTipoCadastro;
	}

	public List<UnidadeTipo> getUnidadeTipos() {
		return unidadeTipos;
	}

	public void setUnidadeTipos(List<UnidadeTipo> unidadeTipos) {
		this.unidadeTipos = unidadeTipos;
	}

	public List<UnidadeTipo> getUnidadeTiposFiltrados() {
		return unidadeTiposFiltrados;
	}

	public void setUnidadeTiposFiltrados(List<UnidadeTipo> unidadeTiposFiltrados) {
		this.unidadeTiposFiltrados = unidadeTiposFiltrados;
	}	
}
