package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.portaria.dao.AndarDAO;
import br.com.portaria.model.Andar;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AndarBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String acao;
	private Andar andarCadastro;
	private AndarDAO dao = new AndarDAO();
	private List<Andar> andares;
	private List<Andar> andaresFiltrados;

	public void salvar() {
		try {
			
			dao.salvar(this.andarCadastro);
			this.andarCadastro = new Andar();
			FacesUtil.adicionarMsgInfo("Andar cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Andar: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			dao.editar(this.andarCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Andar: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.andarCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Andar: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.andares = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			if (this.id != null) {
				this.andarCadastro = dao.buscaPorId(this.id);
			} else {
				this.andarCadastro = new Andar();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Andar: " + e.getMessage());
		}
	}

	public void limpar() {
		this.andarCadastro = new Andar();
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

	public Andar getAndarCadastro() {
		return andarCadastro;
	}

	public void setAndarCadastro(Andar andarCadastro) {
		this.andarCadastro = andarCadastro;
	}

	public List<Andar> getAndares() {
		return andares;
	}

	public void setAndares(List<Andar> andares) {
		this.andares = andares;
	}

	public List<Andar> getAndaresFiltrados() {
		return andaresFiltrados;
	}

	public void setAndaresFiltrados(List<Andar> andaresFiltrados) {
		this.andaresFiltrados = andaresFiltrados;
	}	
}
