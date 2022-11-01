package com.sample.domain.board.dto;

import lombok.Data;

@Data
public class UpdateBoardReplyDTO {

	private Long id;
	private String content;
	private Long updatedBy;

}
