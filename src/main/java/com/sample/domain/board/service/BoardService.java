package com.sample.domain.board.service;

import com.sample.domain.board.dto.*;
import com.sample.domain.board.entity.Board;
import com.sample.domain.board.repository.BoardRepository;
import com.sample.domain.boardReply.repository.BoardReplyRepository;
import com.sample.domain.member.entity.Member;
import com.sample.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final BoardReplyRepository boardReplyRepository;
	private final MemberService memberService;
	
	@Transactional
	public Long insertBoard(CreateBoardDTO createBoardDTO) {

		Member member = memberService.getUserNotOptional(createBoardDTO.getCreatedBy());
		Board res = boardRepository.save(createBoardDTO.toBoardEntity(createBoardDTO, member));

		return res.getId();
	}


	public Page<BoardListDTO> getBoardList(String keyword, Pageable pageable) {

		//페이징 처리
		//일대다인 boardReplyList의 경우 지연로딩+batch size 옵션 사용
		Page<Board> board = boardRepository.findAllPaging(keyword, pageable);

		Page<BoardListDTO> res = BoardListDTO.toBoardListDTO(board);

		return res;
	}
                  
	
	@Transactional
	public BoardDetailDTO getBoardDetail(Long id) {

		Board board = this.getBoardNotOptional(id);

		//조회수 증가
		board.addViewCount(board.getViewCount());

		BoardDetailDTO res = BoardDetailDTO.toBoardDetailDTO(board);

		//이전글,다음글 목록 조회
		res.setPreviousList(this.getPreviousList(board.getCreatedAt()));
		res.setNextList(this.getNextList(board.getCreatedAt()));

		return res;
	}
	
	
	@Transactional
	public Long updateBoard(UpdateBoardDTO updateBoardDTO) {

		Board found = this.getBoardNotOptional(updateBoardDTO.getId());
		Member updatedBy = memberService.getUserNotOptional(updateBoardDTO.getUpdatedBy());

		//게시물 수정
		Board changed = found.changeBoard(updateBoardDTO, updatedBy);

		return changed.getId();
	}


	@Transactional
	public void removeBoard(Long id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void removeBoardList(List<String> boardIdList) {
		//child 객체 벌크 삭제
		List<String> boardReplyIdList = boardReplyRepository.findBoardReplyList(boardIdList);
		boardReplyRepository.deleteByIdBulk(boardReplyIdList);
		//본 객체 벌크 삭제
		boardRepository.deleteByIdBulk(boardIdList);
	}


	public Board getBoardNotOptional(Long id) {
		Optional<Board> board = boardRepository.findById(id);
		return board.orElseThrow(() -> new NoSuchElementException("could not find data with id : " + id));
	}

	public PreviousNextListDTO getPreviousList(LocalDateTime createdAt) {
		PreviousNextListDTO res = null;
		Board found = boardRepository.findTopByCreatedAtBeforeOrderByCreatedAtDesc(createdAt);
		if (found != null) {
			res = new PreviousNextListDTO(found.getId(), found.getTitle());
		}
		return res;
	}

	public PreviousNextListDTO getNextList(LocalDateTime createdAt) {
		PreviousNextListDTO res = null;
		Board found = boardRepository.findTopByCreatedAtAfterOrderByCreatedAtAsc(createdAt);
		if (found != null) {
			res = new PreviousNextListDTO(found.getId(), found.getTitle());
		}
		return res;
	}
	
}
