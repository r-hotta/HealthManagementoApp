package com.example.healthmanagementoapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Input {
	long id;

	//	private int id2;
	@Size(min = 0, max = 20, message = "1～20文字以内にしてください")
	@NotBlank(message = "*名前を入力してください")
	private String name;
	
	private String seibetu;
	
	@Positive(message = "*０以上の値を入力してください")
	
	private int age;

	@Positive(message = "*０以上の値を入力してください")
	private int sinntyou;

	@Positive(message = "*０以上の値を入力してください")
	private int taijuu;

	@Positive(message = "*０以上の値を入力してください")
	private int ketuatuue;

	@Positive(message = "*０以上の値を入力してください")
	private int ketuatusita;
	
	private String memo;
	
	private String type;

	
	public Input() {
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
