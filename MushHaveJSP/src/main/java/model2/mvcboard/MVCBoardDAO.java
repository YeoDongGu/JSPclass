package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
    public MVCBoardDAO() {
        super();
    }

    // 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM mvcboard";
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount;
    }

    // 검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
    public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
        List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
        String query = " "
                     + "SELECT * FROM ( "
                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                     + "        SELECT * FROM mvcboard ";

        if (map.get("searchWord") != null)
        {
            query += " WHERE " + map.get("searchField")
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }

        query += "        ORDER BY idx DESC "
               + "    ) Tb "
               + " ) "
               + " WHERE rNum BETWEEN ? AND ?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, map.get("start").toString());
            psmt.setString(2, map.get("end").toString());
            rs = psmt.executeQuery();

            while (rs.next()) {
                MVCBoardDTO dto = new MVCBoardDTO();

                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));

                board.add(dto);
            }
        }
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }
    
    //게시글 데이터를 받아 DB에 추가합니다(파일 업로드 지원)
    public int insertWrite(MVCBoardDTO dto) {
    	int result = 0;
    	try {
    		String query = "INSERT INTO mvcboard ( "
    					 + " idx, name, title, content, ofile, sfile, pass)"
    					 + " VALUES ( "
    					 + " seq_board_num.NEXTVAL,?,?,?,?,?,?)";
    		psmt = con.prepareStatement(query);
    		psmt.setString(1, dto.getName());
    		psmt.setString(2, dto.getTitle());
    		psmt.setString(3, dto.getContent());
    		psmt.setString(4, dto.getOfile());
    		psmt.setString(5, dto.getSfile());
    		psmt.setString(6, dto.getPass());
    		result = psmt.executeUpdate();
    		
    	}catch(Exception e) {
    		System.out.println("게시물 입력 중 예외 발생");
    		e.printStackTrace();
    	}
    	return result;
    }
    
    public MVCBoardDTO selectView(String idx) {
    	MVCBoardDTO dto = new MVCBoardDTO();
    	String query = "select * from mvcboard where idx=?";
    	try {
    		psmt = con.prepareStatement(query);
    		psmt.setString(1, idx);
    		rs = psmt.executeQuery();
    		if(rs.next()) {
    			dto.setIdx(rs.getString(1));
    			dto.setName(rs.getString(2));
    			dto.setTitle(rs.getString(3));
    			dto.setContent(rs.getString(4));
    			dto.setPostdate(rs.getDate(5));
    			dto.setOfile(rs.getString(6));
    			dto.setSfile(rs.getString(7));
    			dto.setDowncount(rs.getInt(8));
    			dto.setPass(rs.getString(9));
    			dto.setVisitcount(rs.getInt(10));
    		}
    	}catch(Exception e) {
    		System.out.println("게시물 상세보기 중 예외 발생");
    		e.printStackTrace();
    	}
    	return dto;
    }
    
    //주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다
    public void updateVisitCount(String idx) {
    	String query = "UPDATE mvcboard SET "
    				 + " visitcount=visitcount+1 "
    				 + " where idx=?";
    	try {
    		psmt = con.prepareStatement(query);
    		psmt.setString(1, idx);
    		psmt.executeQuery();
    	}catch(Exception e) {
    		System.out.println("게시물 조회수 증가 중 예외 발생");
    		e.printStackTrace();
    	}
    }
    
    public void downCountPlus(String idx) {
    	String sql  = "UPDATE mvcboard SET "
    				+ " downcount=downcount+1 "
    				+ " where idx=? ";
    	
    	try {
    		psmt = con.prepareStatement(sql);
    		psmt.setString(1, idx);
    		psmt.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}