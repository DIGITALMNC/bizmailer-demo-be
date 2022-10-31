package com.sample.domain.boardReply.dto;

import lombok.Data;

@Data
public class UpdateBoardReplyDTO {

	private Long id;
	private String content;
	private Long updatedBy;

}
