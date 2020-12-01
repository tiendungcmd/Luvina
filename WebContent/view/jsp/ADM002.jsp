<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>
	<!-- Begin vung header -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- End vung header -->

	<!-- Begin vung dieu kien tim kiem -->
	<form action="./ListUserController" method="get" name="mainform">
		<input name="action" value="search" style="display: none;">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="name" value="${fullName }" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /> <c:out
									value="${name}"></c:out></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px">
								<!-- <p style="color: red;">${list}</p> --> <!--  --> <select
								name="group_id">
									<option value="0">全て</option>
									<c:forEach items="${lstGroup}" var="list">
										<c:if test="${group_id == list.group_id }">
											<option value="${list.group_id}" selected>${list.group_name}</option>
										</c:if>
										<c:if test="${group_id != list.group_id }">
											<option value="${list.group_id}">${list.group_name}</option>
										</c:if>
									</c:forEach>
							</select>
							</td>
							<td align="left"><input class="btn" type="submit"
								name="search" value="検索" /> <input class="btn" type="button"
								name="add" value="新規追加" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</input>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<c:if test="${lstUser.size()!=0 }">
		<input name="action" value="sort" style="display: none;">
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
			width="80%">
			<tr class="tr2">
				<th align="center" width="20px">ID</th>
				<th align="left">氏名 
				<a href="${pageContext.request.contextPath }/ListUserController?action=sort%sorType=code_level&fullName=${fullName}
				&groupId=${groupId}&sortByFullName=${sortByFullName}&sortByCodeLevel=${sortByCodeLevel}&sortByEndDate=${sortByEndDate}" name ="sortFullName">▲▽</a>
				<!-- <a href="ListUserController?action=sort&name=${sortFullName }" name="sortFullName" value="sortFullName">▲▽</a> -->
				</th>
				<th align="left">生年月日</th>
				<th align="left">グループ</th>
				<th align="left">メールアドレス</th>
				<th align="left" width="70px">電話番号</th>
				<th align="left">日本語能力 
				<a href="ListUserController?action=sort" name="sortByCodeLevel">▲▽</a>
				</th>
				<th align="left">失効日 <a href="ListUserController?action=sort"
					name="sortByEndDate">△▼</a>
				</th>
				<th align="left">点数</th>
			</tr>
			<c:forEach items="${lstUser}" var="listUser">
				<tr>
					<td align="right"><a href="ADM005.html">${listUser.user_id }</a></td>
					<td>${listUser.full_name }</td>
					<td align="center">${listUser.birthday }</td>
					<td>${listUser.group_name }</td>
					<td>${listUser.email }</td>
					<td>${listUser.tel }</td>
					<td>${listUser.name_level }</td>
					<td align="center">${listUser.end_date }</td>
					<td align="right">${listUser.total }</td>
				</tr>
			</c:forEach>
		</table>
		<!-- Begin vung paging -->
		<table>
			<tr>
				<td class="lbl_paging"><a href="#" name="paging">1</a> &nbsp; <a
					href="#">2</a> &nbsp; <a href="#">3</a> &nbsp; <a href="#">>></a></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${lstUser.size()==0 }">
		<p style="color: red;">${ERR}</p>
	</c:if>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- End vung footer -->

</body>

</html>