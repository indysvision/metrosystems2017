package com.example.demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Pontaj {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date dataPontajStart;
	private Date dataPontajEnd;
	private String dayOfWeek;
	private long diffMinutes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataPontajStart() {
		return dataPontajStart;
	}
	public void setDataPontajStart(Date dataPontajStart) {
		this.dataPontajStart = dataPontajStart;
	}
	public Date getDataPontajEnd() {
		return dataPontajEnd;
	}
	public void setDataPontajEnd(Date dataPontajEnd) {
		this.dataPontajEnd = dataPontajEnd;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public long getDiffMinutes() {
		return diffMinutes;
	}
	public void setDiffMinutes(long diffMinutes) {
		this.diffMinutes = diffMinutes;
	}
	public Pontaj() {
	}
	public Pontaj(Date dataPontajStart, Date dataPontajEnd, String dayOfWeek, long diffMinutes) {
		this.dataPontajStart = dataPontajStart;
		this.dataPontajEnd = dataPontajEnd;
		this.dayOfWeek = dayOfWeek;
		this.diffMinutes = diffMinutes;
	}
	@Override
	public String toString() {
		return "Pontaj [dataPontajStart=" + dataPontajStart + ", dataPontajEnd=" + dataPontajEnd + ", dayOfWeek="
				+ dayOfWeek + ", diffMinutes=" + diffMinutes + "]";
	}


}
