package br.com.maisViagens.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_usuuario", sequenceName = "seq_usuuario", allocationSize = 1, initialValue = 1)
public class Feedback implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuuario")
	private Long id_feedback;
	private String descricao;
    private String data;
    
	public Long getId_feedback() {
		return id_feedback;
	}
	public void setId_feedback(Long id_feedback) {
		this.id_feedback = id_feedback;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
    
}
