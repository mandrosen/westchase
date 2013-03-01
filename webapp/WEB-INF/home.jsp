<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="messages">
<h1>Messages</h1>
<%--
<p class="current"><span class="date">Sept. 26 - </span>New office software released: ENJOY!.</p>
<p class="future"><span class="date">Sept. 29 - </span>Important message from office manager.  Please read and take note.</p>
<p class="future"><span class="date">Oct. 1 - </span>Unofficial, unpaid holiday.  Must come to work on this day.</p>
 --%>

<c:forEach items="${messages}" var="m" varStatus="stat">
	<div class="msg"><fmt:formatDate value="${m.dueDate}" pattern="MMM dd" /> - <c:out value="${m.text}" /></div>
</c:forEach>
<p class="add"><a href="<s:url action="edit-" includeParams="none" namespace="/message"/>">add</a></p>
</div>

<div class="todolist">
<h1>My TODO list</h1>
<%--
<p class="pastdo"><span class="date">DUE Sept. 15 - </span> <strong>(past due)</strong> Report to president.</p>
<p class="todo"><span class="date">Sept. 30 - </span>client request for info on account - email due to client@email.com</p>
<p class="todo"><span class="date">Oct. 3 - </span>meeting with team [conference room]</p>
<p class="todo"><span class="date">Oct. 5 - </span>special project starts, lunch meeting to discuss details</p> --%>

<c:forEach items="${todos}" var="t" varStatus="stat">
	<div class="todo"><fmt:formatDate value="${t.dueDate}" pattern="MMM dd" /> - <c:out value="${t.text}" /></div>
</c:forEach>
<p class="add"><a href="<s:url action="edit-" includeParams="none" namespace="/todo"/>">add</a></p>
<p class="edit"><a href="<s:url action="list" includeParams="none" namespace="/todo"/>">edit</a></p>
</div>