package rva.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity // Znaci da ce Hibernate napraviti tabelu destinacija 
public class Aranzman {

	@Id // Primarni kljuc tabele 
	@SequenceGenerator(name = "aranzman_seq", sequenceName = "aranzman_seq", 
	allocationSize = 1) // pravi se sekvenca, svaki novi objekat dobija sledeci broj, i povecava se za jedan
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator  = "aranzman_seq")// isto kao prethodno oba to rade
	private long id;
	private double ukupnaCena;
	private boolean placeno;
	private Date datumRealizacije;
	
	// VISE ARANZMANA moze pripadati JEDNOM HOTELU
	@ManyToOne
	@JoinColumn(name = "hotel")
	private Hotel hotel;
	
	// VISE ARANZMANA moze pripadati JEDNOJ TURISTICKOJ AGENCIJI
	@ManyToOne
	@JoinColumn(name = "agencija")
	private TuristickaAgencija agencija;
	
	
	
	
	//KONSTRUKTOR
	
	public Aranzman() {
		
	}
	
	public Aranzman(double ukupnaCena, boolean placeno, Date datumRealizacije) {
		super();
	
		this.ukupnaCena = ukupnaCena;
		this.placeno = placeno;
		this.datumRealizacije = datumRealizacije;
	}
	
	public Aranzman(double ukupnaCena, boolean placeno, Date datumRealizacije, Hotel hotel) {
		super();
	
		this.ukupnaCena = ukupnaCena;
		this.placeno = placeno;
		this.datumRealizacije = datumRealizacije;
		this.hotel = hotel;
	}
	
	public Aranzman(double ukupnaCena, boolean placeno, Date datumRealizacije, TuristickaAgencija agencija) {
		super();
	
		this.ukupnaCena = ukupnaCena;
		this.placeno = placeno;
		this.datumRealizacije = datumRealizacije;
		this.agencija = agencija;
	}
	
	
	//GETERI I SETERI
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	public boolean isPlaceno() {
		return placeno;
	}
	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}
	public Date getDatumRealizacije() {
		return datumRealizacije;
	}
	public void setDatumRealizacije(Date datumRealizacije) {
		this.datumRealizacije = datumRealizacije;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	public TuristickaAgencija getAgencija() {
		return agencija;
	}
	
	public void setAgencija(TuristickaAgencija agencija) {
	    this.agencija = agencija;
	}

	public void setHotel(Hotel hotel) {
	    this.hotel = hotel;
	}
}
