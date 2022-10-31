package com.sample.domain.board.dto;

import com.sample.domain.board.entity.Board;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
public class BoardListDTO {

	private Long id;
	private String title;
	private LocalDateTime createdAt;

	private int replyCount;

	public BoardListDTO() {
	}

	@Builder
	public BoardListDTO(Long id, String title, LocalDateTime createdAt, int replyCount) {
		this.id = id;
		this.title = title;
		this.createdAt = createdAt;
		this.replyCount = replyCount;
	}

	public static Page<BoardListDTO> toBoardListDTO(Page<Board> board) {
		return board.map(b -> BoardListDTO.builder()
				.id(b.getId())
				.title(b.getTitle())
				.createdAt(b.getCreatedAt())
				.replyCount(b.getBoardReplyList().size())
				.build());
	}

}
