package com.sample.domain.board.service;

import com.sample.domain.board.entity.Board;
import com.sample.domain.board.repository.BoardRepository;
import com.sample.domain.board.service.BoardService;
import com.sample.domain.board.dto.CreateBoardReplyDTO;
import com.sample.domain.board.dto.UpdateBoardReplyDTO;
import com.sample.domain.board.entity.BoardReply;
import com.sample.domain.board.repository.BoardReplyRepository;
import com.sample.domain.member.entity.Member;
import com.sample.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardReplyService {

	private final BoardRepository boardRepository;
	private final BoardReplyRepository boardReplyRepository;
	private final BoardService boardService;
	private final MemberService memberService;
	
	@Transactional
	public Long insertBoardReply(CreateBoardReplyDTO createBoardReplyDTO) {

		Member user = memberService.getUserNotOptional(createBoardReplyDTO.getCreatedBy());
		Board board = boardService.getBoardNotOptional(createBoardReplyDTO.getId());

		BoardReply reply = BoardReply.builder()
				.board(board)
				.content(createBoardReplyDTO.getContent())
				.createdBy(user)
				.updatedBy(user)
				.build();
		boardReplyRepository.save(reply);

		return reply.getId();
	}

	@Transactional
	public Long updateBoardReply(UpdateBoardReplyDTO updateBoardReplyDTO) {

		BoardReply found = this.getBoardReplyNotOptional(updateBoardReplyDTO.getId());
		Member updatedBy = memberService.getUserNotOptional(updateBoardReplyDTO.getUpdatedBy());

		//덧글 수정
		BoardReply changed = found.changeBoardReply(updateBoardReplyDTO, updatedBy);

		return changed.getId();
	}

	public BoardReply getBoardReplyNotOptional(Long id) {
		Optional<BoardReply> reply = boardReplyRepository.findById(id);
		return reply.orElseThrow(() -> new NoSuchElementException("could not find data with boardReplyId : " + id));
	}

	@Transactional
	public void removeBoardReply(Long id) {
		boardReplyRepository.deleteById(id);
	}



	

	
}
