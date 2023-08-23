package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import dto.ListDTO;

@Mapper
@Repository
public interface ListDAO {
	/// List select
	// 각 독립유공자 별 존경해요, 댓글 개수 (default)
	public List<ListDTO> listLikeComment(ListDTO dto);
	
	// 각 독립유공자 별 존경해요, 댓글 개수 (sort)
	public List<ListDTO> listSort(ListDTO dto);
	public int listSortCount();
}
