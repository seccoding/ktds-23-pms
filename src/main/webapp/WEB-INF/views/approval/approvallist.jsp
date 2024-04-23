<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결재목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <style>
        .title {
            margin-bottom: 3rem;
        }
        input[type="checkbox"] {
            display: flex;
        }
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border-bottom: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
        .search-area {
            display: flex;
            justify-content: space-between;
            margin-bottom: 1.5rem;
        }
        .search-keyword {
            display: flex;
        }
        .search-keyword > * {
            padding-right: 0.825rem;
        }
        /* 상태 뱃지 */
        .badge {
            display: inline-block;
            padding: 0.52em 0.593em;
            font-size: 0.8125em;
            font-weight: 500;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: 0.25rem;
        }
        .badge:empty {
            display: none;
        }
        .btn .badge {
            position: relative;
            top: -1px;
        }
        .bg-label-danger {
            background-color: #ffe0db !important;
            color: #ff3e1d !important;
            font-weight: bold;
        }
        .bg-success {
            background-color: #e8fadf !important;
            color: #71dd37 !important;
            font-weight: bold
        }
        .bg-label-warning {
            background-color: #fff2d6 !important;
            color: #ffab00 !important;
            font-weight: bold;
        }
        .bg-label-info {
            background-color: #d7f5fc !important;
            color: #03c3ec !important;
            font-weight: bold;
        }
        /* 페이지 */
        nav {
            display: block;
        }
        ol, ul, dl {
            margin-top: 0;
            margin-bottom: 1rem;
        }
        .pagination {
            display: flex;
            padding-left: 0;
            list-style: none;
        }
        .page-item.active .page-link, 
        .page-item.active .page-link:hover, 
        .page-item.active .page-link:focus, 
        .pagination li.active > a:not(.page-link), 
        .pagination li.active > a:not(.page-link):hover, 
        .pagination li.active > a:not(.page-link):focus {
            border-color: #696cff;
            background-color: #696cff;
            color: #fff;
            box-shadow: 0 0.125rem 0.25rem rgba(105, 108, 255, 0.4);
        }
        .page-item.first .page-link, 
        .page-item.last .page-link, 
        .page-item.next .page-link, 
        .page-item.prev .page-link, 
        .page-item.previous .page-link {
            padding-top: 0.5rem;
            padding-bottom: 0.5rem;
        }
        .page-link, .page-link > a {
            border-radius: 0.375rem;
            line-height: 1;
            text-align: center;
            min-width: calc(2.1875rem + 0px);
        }
        .page-link {
            position: relative;
            display: block;
            color: #697a8d;
            background-color: #f0f2f4;
            border: 0px solid #d9dee3;
            transition: color 0.15s ease-in-out, 
                        background-color 0.15s ease-in-out, 
                        border-color 0.15s ease-in-out, 
                        box-shadow 0.15s ease-in-out;
        }
        .page-item.active .page-link {
            margin: 0 0.1rem 0 0.3rem;
        }
        .page-link:hover {
            z-index: 2;
            color: #697a8d;
            background-color: #e1e4e8;
            border-color: rgba(67, 89, 113, 0.3);
        }
        .page-link:focus {
            z-index: 3;
            color: #697a8d;
            background-color: #e1e4e8;
            outline: 0;
            box-shadow: none;
        }
        .page-item:not(:first-child) .page-link {
            margin-left: 0.1875rem;
        }
        .page-item.active .page-link {
            z-index: 3;
            color: #fff;
            background-color: rgba(105, 108, 255, 0.08);
            border-color: rgba(105, 108, 255, 0.08);
        }
        .page-item.disabled .page-link {
            color: #a1acb8;
            pointer-events: none;
            background-color: #f7f8f9;
            border-color: rgba(67, 89, 113, 0.3);
        }
        .page-link {
        padding: 0.625rem 0.5125rem;
        }
        .page-item .page-link {
        border-radius: 0.25rem;
        }
    </style>
<script type="text/javascript" src=/js/approval/approvallist.js></script>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>결재 목록조회</h2>
        </div>
        <div class="search-area">
            <div class="search-date">
                <input type="text">
                <button>날짜검색</button>
            </div>
        </div>
        <div>
            <div>
                총 ${apprList.apprCnt} 건
            </div>
            <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '302'}">
                <div style="text-align: end;">
                    <button class="btn-appr-write">기안서 작성</button>
                </div>
            </c:if>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="prdt-check-all" data-target-class="target-appr-id"/>
                            <!-- <label for="prdt-check-all"></label>
                            <label for="prdt-check-all"></label> -->
                        </th>
                        <th>작성일자</th>
                        <th>종류</th>
                        <th>문서번호</th>
                        <th>문서제목</th>
                        <th>기안자</th>
                        <th>결재상태</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty apprList.apprList}">
                            <c:forEach items="${apprList.apprList}" var="approval">
                            <tr>
                                <td>
                                    <input type="checkbox" class="target-appr-id" name="targetApprId" value="${approval.apprId}">
                                    <!-- <label for="prdt-check"></label>
									<label for="prdt-check"></label> -->
                                </td>
                                <td>${approval.dmdDt}</td>
                                <td>
                                    <c:if test="${approval.apprCtgr eq '902'}">
                                        <span>비품변경</span>
                                    </c:if>
                                </td>
                                <td>${approval.apprId}</td>
                                <td>
                                    <a href="/approval/view?apprId=${approval.apprId}">
                                        ${approval.apprTtl}
                                    </a>
                                </td>
                                <td>${approval.employeeVO.empName}</td>
                                <td>									
									<c:if test="${approval.apprSts eq '801'}">
										<span class="badge bg-label-warning">결재대기</span>
									</c:if>
									<c:if test="${approval.apprSts eq '802'}">
                                        <span class="badge bg-success">결재승인</span>
									</c:if>
									<c:if test="${approval.apprSts eq '803'}">
										<span class="badge bg-label-danger">결재반려</span>
									</c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
							<tr>
								<td>
									<a href="/approval/write">
										기안서가 존재하지 않습니다.
									</a>
								</td>
							</tr>
						</c:otherwise>
                    </c:choose>	
                </tbody>
            </table>
        </div>
        <nav aria-label="Page navigation">
            <form id="search-form">
                <div class="search-keyword">
                    <div class="search-category">
                        <input type="hidden" id="page-no" name="pageNo" value="0"/>
                        <select id="list-size" name="listSize">
                            <option value="5" ${searchApproval.listSize eq 5 ? 'selected' : ''}>5개</option>
                            <option value="10" ${searchApproval.listSize eq 10 ? 'selected' : ''}>10개</option>
                        </select>
                        <select name="searchType" id="search-type">
                            <option value="title" ${searchApproval.searchType eq 'title' ? 'selected' : ''}>제목</option>
                            <option value="dmdId" ${searchApproval.searchType eq 'dmdId' ? 'selected' : ''}>사원번호</option>
                        </select>
                    </div>
                    <div class="search-text">
                        <input type="text" name="searchKeyword" value="${searchApproval.searchKeyword}"/>
                        <button type="button" id="search-btn">검색</button>
                        <button type="button" id="cancel-search-btn">초기화</button>
                    </div>
                </div>
                <ul class="pagination">
                    <c:if test="${searchApproval.hasPrevGroup}">
                        <li class="page-item first">
                            <a class="page-link" href="javascript:search(0);">처음</a>
                        </li>
                        <li class="page-item prev">
                            <a class="page-link" href="javascript:search(${searchApproval.prevGroupStartPageNo});">이전</a>
                        </li>
                    </c:if>
                    <c:forEach begin="${searchApproval.groupStartPageNo}" end="${searchApproval.groupEndPageNo}" step="1" var="p">
                        <li class="${searchApproval.pageNo eq p ? 'active' : ''}">
                            <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${searchApproval.hasNextGroup}">
                        <li class="page-item next">
                            <a class="page-link" href="javascript:search(${searchApproval.nextGroupStartPageNo});">다음</a>
                        </li>
                        <li class="page-item last">
                            <a class="page-link" href="javascript:search(${searchApproval.groupCount - 1});">마지막</a>
                        </li>
                    </c:if>
                </ul>
            </form>
        </nav>
    </div>
</body>
</html>