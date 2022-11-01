package com.sample.domain.board.repository;

import com.sample.domain.board.entity.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

//    @Query(value = "select count(r) from BoardReply r" +
//                    " where r.board.id = :id")
//    int getReplyCount(@Param("id") String id);
//
//    int countBoardReplyEntitiesByBoard_id(String id);

    @Query("select r.id from BoardReply r where r.board.id in :boardIdList")
    List<Long> findBoardReplyList(@Param("boardIdList") List<Long> boardIdList);

    @Modifying(clearAutomatically = true) //벌크연산 후 영속성 컨텍스트 초기화
    @Query("delete from BoardReply r where r.id in :boardReplyIdList")
    void deleteByIdBulk(@Param("boardReplyIdList") List<Long> boardReplyIdList);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
