package rva.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity // kaze Hibernate-u kreiraj tabelu TURISTICKA AGENCIJA
public class TuristickaAgencija {
	@Id // ANOTACIJA ZA ID PRIMARNI KLJUC
	@SequenceGenerator(name = "agencija_seq", sequenceName = "agencija_seq", 
	allocationSize = 1) // KREIRANJE SEKVENCE ZA ID
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator  = "agencija_seq")
	
	// KOLONE U TABELI
	private long id;
	private String naziv;
	private String adresa;
	private String kontakt;
	
	// JEDNA TURISTICKA AGENCIJA MOZE IMATI VISE ARANZMANA
	@OneToMany(mappedBy = "agencija", cascade = CascadeType.ALL)
	private List<Aranzman> aranzman;
	
	/*
	 mappedBy = "agencija"
	 - veza je desfinisana u klasi ARANZMAN private TuristickaAgencija agencija;
	 - znaci strani kljuc je u tabeli ARANZMAN
	 */

	
	//KONTRUKTOR
	public TuristickaAgencija() {
		
	}
	
	public TuristickaAgencija(String naziv, String adresa, String kontakt) {
		super();
		
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
