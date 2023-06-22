package com.example.healthmanagementoapp.entity;

public class Ent {
	long id;

	//	private int id2;
	private String name;
	
	private String seibetu;

	private int age;
	
	private int sinntyou;

	private int taijuu;

	private int ketuatuue;

	private int ketuatusita;
	
	private String memo;
	
	private String type;

	

	public Ent() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSeibetu() {
		return seibetu;
	}

	public void setSeibetu(String seibetu) {
		this.seibetu = seibetu;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public int getSinntyou() {
		return sinntyou;
	}

	public void setSinntyou(int sinntyou) {
		this.sinntyou = sinntyou;
	}

	public int getTaijuu() {
		return taijuu;
	}

	public void setTaijuu(int taijuu) {
		this.taijuu = taijuu;
	}

	public int getKetuatuue() {
		return ketuatuue;
	}

	public void setKetuatuue(int ketuatuue) {
		this.ketuatuue = ketuatuue;
	}

	public int getKetuatusita() {
		return ketuatusita;
	}

	public void setKetuatusita(int ketuatusita) {
		this.ketuatusita = ketuatusita;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
