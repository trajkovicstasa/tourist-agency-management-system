package rva.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
	//KOLONE U TABELI 
	private long id;
	// KOLONE U TABELI 
	private String mesto;
	private String drzava;
	private String opis;
	
	// JEDNA DESTINACIJA MOZE IMATI VISE HOTELA 
	@OneToMany(mappedBy = "destinacija", cascade = CascadeType.ALL)
	/*
	 mappedBy = "destinacija"
	 - veza nije fizicki zapisana u tabeli DESTINACIJA, vec se strani kljuc
	 nalazi u tabeli HOTEL u polju private Destinacija destinacija
	 cascade = CascadeType.ALL 
	 - znaci da se operacije NAD DESTINACIJOM prenose na HOTELE
	 - npr. Ako se obrise destinacija, brisu se i hoteli vezani za tu destinaicju
	 */
	private List<Hotel> hotel; // LISTA HOTELA KOJA PRIPADA TOJ DESTINACIJI
	
	//KONSTRUKTOR
	
	// PRAZAN KONSTRUKTOR
	// - Obavezan je za JPA/Hibernate jer ga koristi kada iz baze pravi JAVA objekat
	public Destinacija() {
		
	}
	
	// OBICAN KONSTRUKTOR
	// - Koristim ga kad rucno pravim novu destinaciju 
	public Destinacija(String mesto, String drzava, String opis) {
		super();
	
		this.mesto = mesto;
		this.drzava = drzava;
		this.opis = opis;
	}
	
	
	//GETERI I SETERI
	//- sluze da se pristupi privatnim poljima 
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
