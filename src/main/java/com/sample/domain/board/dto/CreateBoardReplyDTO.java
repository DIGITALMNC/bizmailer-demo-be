package com.sample.domain.board.dto;

import lombok.Data;

@Data
public class CreateBoardReplyDTO {

	private Long id;
	private String content;
	private Long createdBy;
	private Long updatedBy;

}
