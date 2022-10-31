package com.sample.domain.boardReply.controller;

import com.sample.domain.boardReply.dto.CreateBoardReplyDTO;
import com.sample.domain.boardReply.dto.UpdateBoardReplyDTO;
import com.sample.domain.boardReply.service.BoardReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardReply")
@Api(tags = "게시판 덧글", description = "게시판 덧글 관련 APIs")
public class BoardReplyController {
	
	private final BoardReplyService boardReplyService;
	
	/* 등록 */
	@PostMapping()
	@ApiOperation(value = "게시판 덧글 등록")
	public Long insertBoardReply(@RequestBody CreateBoardReplyDTO createBoardReplyDTO) {
		return boardReplyService.insertBoardReply(createBoardReplyDTO);
	}

	/* 수정 */
	@PatchMapping()
	@ApiOperation(value = "게시판 덧글 수정")
	public Long updateBoardReply(@RequestBody UpdateBoardReplyDTO updateBoardReplyDTO) {
		return boardReplyService.updateBoardReply(updateBoardReplyDTO);
	}

	/* 삭제 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "게시판 덧글 삭제")
	public void removeBoardReply(@PathVariable("id") Long id) {
		boardReplyService.removeBoardReply(id);
	}
	

}
