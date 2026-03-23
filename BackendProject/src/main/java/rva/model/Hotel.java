package rva.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Hotel {

	@Id
	@SequenceGenerator(name = "hotel_seq", sequenceName = "hotel_seq", 
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator  = "hotel_seq")
	private long id;
	private String naziv;
	private int brojZvezdica;
	private String opis;

	
	@OneToMany(mappedBy = "hotel")
	private List<Aranzman> aranzman;
	
	@ManyToOne
	@JoinColumn(name = "destinacija")
	private Destinacija destinacija;
	
	//KONSTRUKTOR
	public Hotel(long id, String naziv, int brojZvezdica, String opis, int destinacija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.brojZvezdica = brojZvezdica;
		this.opis = opis;
		
	}
	
	
	//GETERI I SETERI
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getBrojZvezdica() {
		return brojZvezdica;
	}
	public void setBrojZvezdica(int brojZvezdica) {
		this.brojZvezdica = brojZvezdica;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	
}
