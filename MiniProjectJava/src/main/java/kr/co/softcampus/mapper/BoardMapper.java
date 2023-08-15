package kr.co.softcampus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softcampus.beans.ContentBean;

public interface BoardMapper {
	
	@SelectKey(statement = "select content_seq.nextval from dual", keyProperty = "content_idx", before = true, resultType = int.class) 
	// dual = 가상의 테이블 
	
	// nextval => seq + 1 먼저 selectKey가 실행되어 content_idx에 들어가고 밑에 쿼리문 실행
	
	
	@Insert("insert into content_table(content_idx, content_subject, content_text, content_file, content_writer_idx, " + 
			 "content_board_idx, content_date) " +
			"values (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, #{content_writer_idx}, " + 
			"#{content_board_idx}, sysdate)")
	
	void addContentInfo(ContentBean writeContentBean);

	@Select("select board_info_name "
			+ "from board_info_table "
			+ "where board_info_idx = #{board_info_idx}")
	
	String getBoardInfoName(int board_info_idx); // 게시판 이름 갖고 오기
	
	
	@Select("select a1.content_idx, a1.content_subject, a2.user_name as content_writer_name, "
			+ "to_char(a1.content_date, 'YYYY-MM-DD')as content_date "
			+ "from content_table a1, user_table a2 "
			+ "where a1.content_writer_idx = a2.user_idx "
			+ "        and a1.content_board_idx = #{board_info_idx} "
			+ "        order by a1.content_idx desc")
	
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);
	
	@Select("select a2.user_name as content_writer_name, "
			+ "        to_char(a1.content_date, 'YYYY-MM-DD') as content_date, "
			+ "        a1.content_subject, a1.content_text, a1.content_file, a1.content_writer_idx "
			+ "from content_table a1, user_table a2 "
			+ "where a1.content_writer_idx = a2.user_idx "
			+ "    and content_idx = #{content_idx}")
	
	ContentBean getContentInfo(int content_idx);
	
	@Update("update content_table " +
			"set content_subject = #{content_subject}, content_text = #{content_text}, " +
			"content_file = #{content_file, jdbcType=VARCHAR} " +
			"where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean); 
	
	
	@Delete("delete from content_table where content_idx = #{content_idx}")
	
	void deleteContentInfo(int content_idx);
	
	
	
	@Select("select count(*) from content_table where content_board_idx = #{content_board_idx}")
	
	int getContentCnt(int content_board_idx);
	
	
	
}
// content_file, jdbcType=VARCHAR => null을 허용한다.


