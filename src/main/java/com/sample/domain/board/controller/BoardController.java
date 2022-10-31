package com.sample.domain.board.controller;

import com.sample.domain.board.dto.BoardDetailDTO;
import com.sample.domain.board.dto.BoardListDTO;
import com.sample.domain.board.dto.CreateBoardDTO;
import com.sample.domain.board.dto.UpdateBoardDTO;
import com.sample.domain.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = "게시판", description = "게시판 관련 APIs")
public class BoardController {
	
	private final BoardService boardService;

	/* 등록 */
	@PostMapping()
	@ApiOperation(value = "게시판 본문 등록")
	public ResponseEntity<Long> insertBoard(@RequestBody CreateBoardDTO createBoardDTO) {
		return ResponseEntity.ok(boardService.insertBoard(createBoardDTO));
	}

	/* 목록 조회 */
	@GetMapping()
	@ApiOperation(value = "게시판 목록 조회")
	public ResponseEntity<Page<BoardListDTO>> getBoardList(@RequestParam(value = "keyword", required = false) String keyword, Pageable pageable) {
		return ResponseEntity.ok(boardService.getBoardList(keyword, pageable));
	}

	/* 상세 조회 */
	@GetMapping("/{id}")
	@ApiOperation(value = "게시판 상세 조회")
	public ResponseEntity<BoardDetailDTO> getBoardDetail(@PathVariable("id") Long id) {
		return ResponseEntity.ok(boardService.getBoardDetail(id));
	}

	/* 수정 */
	@PatchMapping()
	@ApiOperation(value = "게시판 본문 수정")
	public ResponseEntity<Long> updateBoard(@RequestBody UpdateBoardDTO updateBoardDTO) {
		return ResponseEntity.ok(boardService.updateBoard(updateBoardDTO));
	}

	/* 삭제 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "게시판 본문 삭제(상세조회시)")
	public void removeBoard(@PathVariable("id") Long id) {
		boardService.removeBoard(id);
	}

	@DeleteMapping()
	@ApiOperation(value = "게시판 본문 다중삭제(목록조회시)")
	public void removeBoardList(@RequestParam("boardIdList") List<Long> boardIdList) {
		boardService.removeBoardList(boardIdList);
	}


}
