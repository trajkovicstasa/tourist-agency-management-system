package rva.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Aranzman {

	@Id
	@SequenceGenerator(name = "aranzman_seq", sequenceName = "aranzman_seq", 
	allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator  = "aranzman_seq")
	private long id;
	private double ukupnaCena;
	private boolean placeno;
	private Date datumRealizacije;
	
	@ManyToOne
	@JoinColumn(name = "hotel")
	private Hotel hotel;
	
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
	
	
}
