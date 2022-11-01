package com.sample.domain.board.entity;

import com.sample.common.base.BaseEntity;
import com.sample.domain.board.dto.UpdateBoardDTO;
import com.sample.domain.board.entity.type.BoardType;
import com.sample.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

//	@Id
//	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
//	@GeneratedValue(generator = "system-uuid")
//	private String id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private BoardType boardType;

	private String title;

	private String content;

	private int viewCount = 0;

	@OneToMany(mappedBy = "board", orphanRemoval = true)
	@OrderBy("createdAt desc")
//	@BatchSize(size = 100)
	private List<BoardReply> boardReplyList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY) //unidirectional
	@JoinColumn(name = "created_by", referencedColumnName = "id")
	private Member createdBy;

	@ManyToOne(fetch = FetchType.LAZY) //unidirectional
	@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private Member updatedBy;


	@Builder
	public Board(Long id, BoardType boardType, String title, String content, int viewCount, LocalDateTime createdAt, LocalDateTime updatedAt, Member createdBy, Member updatedBy) {
		super(createdAt, updatedAt);
		this.id = id;
		this.boardType = boardType;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}


	/* 게시물 수정 */
	public Board changeBoard(UpdateBoardDTO updateBoardDTO, Member updatedBy) {
		this.title = updateBoardDTO.getTitle();
		this.content = updateBoardDTO.getContent();
		this.updatedBy = updatedBy;
		return this;
	}

	/* 조회수 증가 */
	public void addViewCount(int count) {
		this.viewCount = count + 1;
	}

}
