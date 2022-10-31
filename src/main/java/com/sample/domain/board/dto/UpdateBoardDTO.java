package com.sample.domain.board.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper=false)
public class UpdateBoardDTO {
	
	private Long id;
	private String content;
	private String title;
	private Long updatedBy;
	
}
