package net.codejava;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Student {
	
	private Integer id;
	
	private String nama;
	private String alamat;
	private String no_telepon;
	private Date tanggal_lahir;
	// private String noTelepon;
	// private Date tanggalLahir;
	//q: harus sama kyk di db apa engga ya?

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Integer id, String nama, String alamat, String no_telepon, Date tanggal_lahir) {
		super();
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.no_telepon = no_telepon;
		this.tanggal_lahir = tanggal_lahir;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getNo_telepon() {
		return no_telepon;
	}

	public void setNo_telepon(String no_telepon) {
		this.no_telepon = no_telepon;
	}

	public Date getTanggal_lahir() {
		return tanggal_lahir;
	}

	public void setTanggal_lahir(Date tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}

}
