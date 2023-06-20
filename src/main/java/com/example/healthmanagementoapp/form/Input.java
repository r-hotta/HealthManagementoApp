package com.example.healthmanagementoapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Input {
	long id;

	//	private int id2;
	@Size(min = 1, max = 20, message = "1～20文字以内にしてください")
	private String name;
	
	@NotBlank(message = "年齢を入力してください")
	private String age;

	@NotBlank(message = "身長を入力してください")
	private String sinntyou;

	@NotBlank(message = "体重を入力してください")
	private String taijuu;

	@NotBlank(message = "血圧（上）を入力してください")
	private String ketuatuue;

	@NotBlank(message = "血圧（下）を入力してください")
	private String ketuatusita;
	
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
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinntyou() {
		return sinntyou;
	}

	public void setSinntyou(String sinntyou) {
		this.sinntyou = sinntyou;
	}

	public String getTaijuu() {
		return taijuu;
	}

	public void setTaijuu(String taijuu) {
		this.taijuu = taijuu;
	}

	public String getKetuatuue() {
		return ketuatuue;
	}

	public void setKetuatuue(String ketuatuue) {
		this.ketuatuue = ketuatuue;
	}

	public String getKetuatusita() {
		return ketuatusita;
	}

	public void setKetuatusita(String ketuatusita) {
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
