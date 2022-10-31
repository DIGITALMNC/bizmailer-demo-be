package com.sample.domain.board.dto;

import com.sample.domain.board.entity.Board;
import com.sample.domain.boardReply.dto.BoardReplyListDTO;
import com.sample.domain.boardReply.entity.BoardReply;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDetailDTO {

	private Long id;
	private String title;
	private String content;
	private int viewCount;
	private LocalDateTime createdAt;

	private PreviousNextListDTO previousList;

	private PreviousNextListDTO nextList;

	private List<BoardReplyListDTO> replyList;

	@Builder
	public BoardDetailDTO(Long id, String title, String content, int viewCount, LocalDateTime createdAt, List<BoardReply> replyList) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.createdAt = createdAt;
		this.replyList = replyList.stream()
				.map(reply -> new BoardReplyListDTO(reply))
				.collect(Collectors.toList()); //DTO로 변환
	}

	public static BoardDetailDTO toBoardDetailDTO (Board board) {
		return BoardDetailDTO.builder()
			.id(board.getId())
			.title(board.getTitle())
			.content(board.getContent())
			.viewCount(board.getViewCount())
			.createdAt(board.getCreatedAt())
			.replyList(board.getBoardReplyList())
			.build();
	}
	
	
}
