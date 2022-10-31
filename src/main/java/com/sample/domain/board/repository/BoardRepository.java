package com.sample.domain.board.repository;

import com.sample.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select b from Board b" +
            " where ((b.title like concat('%', :keyword, '%')" +
            " or b.content like concat('%', :keyword, '%'))" +
            " or (:keyword is null or :keyword = ''))" +
            " order by b.createdAt desc")
    Page<Board> findAllPaging(@Param("keyword") String keyword, Pageable pageable );

//    @Query(value = "select b from BoardEntity b" +
//            " where ((b.title like concat('%', :keyword, '%')" +
//            " or b.content like concat('%', :keyword, '%'))" +
//            " or (:keyword is null or :keyword = ''))" +
//            " order by b.createdAt desc")
//    @EntityGraph(attributePaths = {"boardReplyList"})

//    Page<BoardEntity> findAllPaging(@Param("keyword") String keyword, Pageable pageable);

    Board findTopByCreatedAtBeforeOrderByCreatedAtDesc(LocalDateTime createdAt);

    Board findTopByCreatedAtAfterOrderByCreatedAtAsc(LocalDateTime createdAt);

    @Modifying(clearAutomatically = true) //벌크연산 후 영속성 컨텍스트 초기화
    @Query("delete from Board b where b.id in :boardIdList")
    void deleteByIdBulk(@Param("boardIdList") List<String> boardIdList);

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
