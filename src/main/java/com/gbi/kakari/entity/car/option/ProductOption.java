package com.gbi.kakari.entity.car.option;

import com.gbi.kakari.entity.car.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productoption")
public class ProductOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_option_id", nullable = false)
	private Integer productOptionId;

	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(name = "option_type_id", nullable = false)
	private Integer optionTypeId;

	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "option_type_id", insertable = false, updatable = false)
	private OptionType optionType;
}
