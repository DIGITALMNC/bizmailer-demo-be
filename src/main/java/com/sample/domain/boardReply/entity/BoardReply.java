package com.sample.domain.boardReply.entity;

import com.sample.common.base.BaseEntity;
import com.sample.domain.board.entity.Board;
import com.sample.domain.boardReply.dto.UpdateBoardReplyDTO;
import com.sample.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "boardReply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReply extends BaseEntity {

//	@Id
//	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
//	@GeneratedValue(generator = "system-uuid")
//	private String boardReplyId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY) //bidirectional
	@JoinColumn(name = "board_id")
	private Board board;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY) //unidirectional
	@JoinColumn(name = "created_by", referencedColumnName = "id")
	private Member createdBy;

	@ManyToOne(fetch = FetchType.LAZY) //unidirectional
	@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private Member updatedBy;


	@Builder
	public BoardReply(Long id, Board board, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Member createdBy, Member updatedBy) {
		super(createdAt, updatedAt);
		this.id = id;
		this.board = board;
		this.content = content;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}



	/* 덧글 수정 */
	public BoardReply changeBoardReply(UpdateBoardReplyDTO updateBoardReplyDTO, Member updatedBy) {
		this.content = updateBoardReplyDTO.getContent();
		this.updatedBy = updatedBy;
		return this;
	}

}
