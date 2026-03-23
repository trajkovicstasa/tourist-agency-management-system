package rva.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class TuristickaAgencija {
	@Id
	@SequenceGenerator(name = "agencija_seq", sequenceName = "agencija_seq", 
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator  = "agencija_seq")
	private long id;
	private String naziv;
	private String adresa;
	private String kontakt;
	
	@OneToMany(mappedBy = "agencija")
	private List<Aranzman> aranzman;
	

	
	//KONTRUKTOR
	public TuristickaAgencija(long id, String naziv, String adresa, String kontakt) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.kontakt = kontakt;
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
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getKontakt() {
		return kontakt;
	}
	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
	
	
	
}
