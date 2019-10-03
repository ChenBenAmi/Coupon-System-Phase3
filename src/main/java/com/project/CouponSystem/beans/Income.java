package com.project.CouponSystem.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {

	private long id;
	private String name;
	private LocalDateTime date;
	private IncomeType description;
	private double amount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	@Column
	public String getName() {
		return name;
	}

	@Column
	public LocalDateTime getDate() {
		return date;
	}

	@Enumerated(EnumType.STRING)
	public IncomeType getDescription() {
		return description;
	}

	@Column
	public double getAmount() {
		return amount;
	}

}
