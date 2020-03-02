package br.senai.sp.jandira.odonto.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_dentista")
public class Dentista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres")
	private String nome;
	
	
	private String cro;
	private String email;
	private String telefone;
	
	@ManyToMany
	@JoinTable( name = "tbl_dentista_especialidade", 
				joinColumns = @JoinColumn(name ="dentista_codigo", referencedColumnName = "codigo"),
				inverseJoinColumns = @JoinColumn(name = "especialidade_codigo", referencedColumnName = "codigo"))
	private List<Especialidade> especialidades;

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCro() {
		return cro;
	}

	public void setCro(String cro) {
		this.cro = cro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Dentista ["+ 
				"codigo=" + codigo + 
				", nome=" + nome + 
				", cro=" + cro + 
				", email=" + email + 
				", telefone=" + telefone + "]";
	}

}
