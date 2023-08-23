package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ListDAO;
import dto.ListDTO;

@Service("listService")
public class ListService {
	@Autowired
	ListDAO dao;
	
	/// List select
	// 각 독립유공자 별 존경해요, 댓글 개수
	public List<ListDTO> listLikeComment(ListDTO dto) {
		return dao.listLikeComment(dto);
	}
	
	// 각 독립유공자 별 존경해요, 댓글 개수 (sort)
	public List<ListDTO> listSort(ListDTO dto) {
		return dao.listSort(dto);
	}
	public int listSortCount() {
		return dao.listSortCount();
	}
}
