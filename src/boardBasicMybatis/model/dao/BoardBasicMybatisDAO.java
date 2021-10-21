package boardBasicMybatis.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardBasicMybatis.model.dto.BoardBasicMybatisDTO;
import sqlmap.MybatisManager;

public class BoardBasicMybatisDAO {

	String table_name = "boardBasic";
	
	public List<BoardBasicMybatisDTO> getSelectAll(){
		Map<String, String> map = new HashMap<>();
		map.put("table_name", table_name);
		SqlSession session = MybatisManager.getInstance().openSession();
		
		//namespace의 이름
		List<BoardBasicMybatisDTO> list = session.selectList("boardBasicMybatis.getSelectAll",map);
		
		return list;
	}

	public BoardBasicMybatisDTO getSelectOne(int no){
		Map<String, String> map = new HashMap<>();
		map.put("no", no+"");
		map.put("table_name", table_name);
		SqlSession session = MybatisManager.getInstance().openSession();
		
		//namespace의 이름
		BoardBasicMybatisDTO dto = session.selectOne("boardBasicMybatis.getSelectOne",map);
		
		return dto;
	}
	
	public int getMaxNum(){
		Map<String, String> map = new HashMap<>();
		map.put("table_name", table_name);
		SqlSession session = MybatisManager.getInstance().openSession();
		
		int result = session.selectOne("boardBasicMybatis.getMaxNum", map);
		
		return result;
	}

	public int getMaxRef(){
		Map<String, String> map = new HashMap<>();
		map.put("table_name", table_name);
		SqlSession session = MybatisManager.getInstance().openSession();
		
		int result = session.selectOne("boardBasicMybatis.getMaxNum", map);
		
		session.close();
		
		return result;
	}
	
	public int setInsert(BoardBasicMybatisDTO dto) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		map.put("no", 0);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		
		int resultNo = (int)map.get("no");
		int result = session.insert("boardBasicMybatis.setInsert", map);
		
		session.commit();
		session.close();
		
		return result;
	}
	
	
	public int setUpdate(BoardBasicMybatisDTO dto) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		
		
		SqlSession session = MybatisManager.getInstance().openSession();
		
		int result = session.update("boardBasicMybatis.setUpdate", map);
		
		session.commit();
		session.close();
		
		return result;
	}

	public int setDelete(BoardBasicMybatisDTO dto) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		
		
		SqlSession session = MybatisManager.getInstance().openSession();
		
		int result = session.delete("boardBasicMybatis.setDelete", map);
		
		session.commit();
		session.close();
		
		return result;
	}
	
}
