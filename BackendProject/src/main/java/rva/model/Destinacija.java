package rva.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Destinacija {

	@Id
	@SequenceGenerator(name = "destinacija_seq", sequenceName = "destinacija_seq", 
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator  = "destinacija_seq")
	private long id;
	private String mesto;
	private String drzava;
	private String opis;
	
	@OneToMany(mappedBy = "destinacija")
	private List<Hotel> hotel;
	
	//KONSTRUKTOR
	public Destinacija(long id, String mesto, String drzava, String opis) {
		super();
		this.id = id;
		this.mesto = mesto;
		this.drzava = drzava;
		this.opis = opis;
	}
	
	
	//GETERI I SETERI
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
}
