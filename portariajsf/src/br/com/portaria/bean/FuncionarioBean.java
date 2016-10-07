package br.com.portaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.portaria.dao.FuncaoDAO;
import br.com.portaria.dao.FuncionarioDAO;
import br.com.portaria.model.Funcao;
import br.com.portaria.model.Funcionario;
import br.com.portaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionarioCadastro;
	private List<Funcionario> funcionarios;
	private List<Funcionario> funcionariosFiltrados;
	private FuncionarioDAO dao = new FuncionarioDAO();
	private Long id;
	private String acao;
	private List<Funcao> funcoes;

	public void salvar() {
		try {
			String senha = DigestUtils.md5Hex(funcionarioCadastro.getSenha());
			funcionarioCadastro.setSenha(senha);
			dao.salvar(this.funcionarioCadastro);
			this.funcionarioCadastro = new Funcionario();
			FacesUtil.adicionarMsgInfo("Funcionário cadastrado com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar cadastrar um Funcionário: " + e.getMessage());
		}
	}

	public void editar() {
		try {
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			dao.editar(this.funcionarioCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar um Funcionário: " + e.getMessage());
		}
	}

	public void excluir() {
		try {
			dao.excluir(this.funcionarioCadastro);
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar excluir um Funcionário: " + e.getMessage());
		}
	}

	public void carregarLista() {
		try {
			this.funcionarios = dao.listar();
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao filtrar a solicitação: " + e.getMessage());
		}
	}
	
	public void carregarDados() {
		try {
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			this.funcoes = funcaoDAO.listar();
			if (this.id != null) {
				this.funcionarioCadastro = dao.buscaPorId(this.id);
			} else {
				this.funcionarioCadastro = new Funcionario();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter um Funcionário: " + e.getMessage());
		}
	}

	public void limpar() {
		this.funcionarioCadastro = new Funcionario();
	}

	public Funcionario getFuncionarioCadastro() {
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
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

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	
}
