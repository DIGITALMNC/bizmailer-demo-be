package com.sample.domain.board.dto;

import com.sample.domain.board.entity.Board;
import com.sample.domain.board.entity.type.BoardType;
import com.sample.domain.member.entity.Member;
import lombok.Data;

@Data
public class CreateBoardDTO {

	private String title;
	private String content; 
	private Long createdBy;
	private Long updatedBy;
	
	public static Board toBoardEntity(CreateBoardDTO createBoardDTO, Member user) {
		Board board = Board.builder()
				.title(createBoardDTO.getTitle())
				.content(createBoardDTO.getContent())
				.boardType(BoardType.NOTICE)
				.createdBy(user)
				.updatedBy(user)
				.build();
		return board;
	}
	
	
	
}
