package com.sample.domain.board.dto;

import lombok.Data;

@Data
public class PreviousNextListDTO {

	private Long id;
	private String title;

	public PreviousNextListDTO(Long id, String title) {
		this.id = id;
		this.title = title;
	}
}
