package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.VisitanteTipoDAO;
import br.com.portaria.model.VisitanteTipo;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VisitanteTipoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private VisitanteTipo visitanteTipoCadastro;
	private VisitanteTipoDAO dao = new VisitanteTipoDAO();
	private List<VisitanteTipo> visitanteTipos;
	private List<VisitanteTipo> visitanteTiposFiltrados;

	public void salvar() {
		try {
			dao.salvar(this.visitanteTipoCadastro);
			this.visitanteTipoCadastro = new VisitanteTipo();
			FacesUtil.adicionarMsgInfo("Tipo de Visitante cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar Tipo de Visitante: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.visitanteTipoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar Tipo de Visitante: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.visitanteTipoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir Tipo de Visitante: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.visitanteTipos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.visitanteTipoCadastro = dao.buscaPorId(this.id);
			} else {
				this.visitanteTipoCadastro = new VisitanteTipo();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Tipo de Visitante: " + e.getMessage());
		}
	}

	public void limpar() {
		this.visitanteTipoCadastro = new VisitanteTipo();
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

	public VisitanteTipo getVisitanteTipoCadastro() {
		return visitanteTipoCadastro;
	}

	public void setVisitanteTipoCadastro(VisitanteTipo visitanteTipoCadastro) {
		this.visitanteTipoCadastro = visitanteTipoCadastro;
	}

	public List<VisitanteTipo> getVisitanteTipos() {
		return visitanteTipos;
	}

	public void setVisitanteTipos(List<VisitanteTipo> visitanteTipos) {
		this.visitanteTipos = visitanteTipos;
	}

	public List<VisitanteTipo> getVisitanteTiposFiltrados() {
		return visitanteTiposFiltrados;
	}

	public void setVisitanteTiposFiltrados(List<VisitanteTipo> visitanteTiposFiltrados) {
		this.visitanteTiposFiltrados = visitanteTiposFiltrados;
	}	
}
