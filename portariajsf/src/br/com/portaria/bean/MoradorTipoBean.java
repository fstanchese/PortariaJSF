package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.MoradorTipoDAO;
import br.com.portaria.model.MoradorTipo;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MoradorTipoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private MoradorTipo moradorTipoCadastro;
	private MoradorTipoDAO dao = new MoradorTipoDAO();
	private List<MoradorTipo> moradorTipos;
	private List<MoradorTipo> moradorTiposFiltrados;

	public void salvar() {
		try {
			dao.salvar(this.moradorTipoCadastro);
			this.moradorTipoCadastro = new MoradorTipo();
			FacesUtil.adicionarMsgInfo("Tipo de Morador cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Tipo de Morador: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.moradorTipoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Tipo de Morador: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.moradorTipoCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Tipo de Morador: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.moradorTipos = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.moradorTipoCadastro = dao.buscaPorId(this.id);
			} else {
				this.moradorTipoCadastro = new MoradorTipo();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Tipo de Morador: " + e.getMessage());
		}
	}

	public void limpar() {
		this.moradorTipoCadastro = new MoradorTipo();
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

	public MoradorTipo getMoradorTipoCadastro() {
		return moradorTipoCadastro;
	}

	public void setMoradorTipoCadastro(MoradorTipo moradorTipoCadastro) {
		this.moradorTipoCadastro = moradorTipoCadastro;
	}

	public List<MoradorTipo> getMoradorTipos() {
		return moradorTipos;
	}

	public void setMoradorTipos(List<MoradorTipo> moradorTipos) {
		this.moradorTipos = moradorTipos;
	}

	public List<MoradorTipo> getMoradorTiposFiltrados() {
		return moradorTiposFiltrados;
	}

	public void setMoradorTiposFiltrados(List<MoradorTipo> moradorTiposFiltrados) {
		this.moradorTiposFiltrados = moradorTiposFiltrados;
	}	
}
