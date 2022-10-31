package com.sample.domain.boardReply.dto;

import com.sample.domain.boardReply.entity.BoardReply;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardReplyListDTO {

	private Long id;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;

	public BoardReplyListDTO(BoardReply boardReplyEntity) {
		this.id = boardReplyEntity.getId();
		this.content = boardReplyEntity.getContent();
		this.createdAt = boardReplyEntity.getCreatedAt();
		this.updatedAt = boardReplyEntity.getUpdatedAt();
		this.createdBy = boardReplyEntity.getCreatedBy().getName();
		this.updatedBy = boardReplyEntity.getUpdatedBy().getName();
	}
}
