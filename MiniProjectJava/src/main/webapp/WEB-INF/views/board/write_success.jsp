<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var='root' value="${pageContext.request.contextPath }/"></c:set>
    <script>
	alert('글작성 성공!')
	location.href = "${root}board/read?board_info_idx=${writeContentBean.content_board_idx}&content_idx=${writeContentBean.content_idx}"
</script>