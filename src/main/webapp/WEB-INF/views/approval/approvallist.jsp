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
        table {
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border-bottom: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
        tr > td:nth-child(5) {
            text-align: left;
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
        .search-data {
            display: flex;
            justify-content: space-between;
            padding-bottom: 1rem;
        }
        input[type="date"] {
            height: 2.2rem;
            border-radius: 0.5rem;
            width: 10rem;
            border: 1px solid var(--secondary);
        }
        input[type="date"]:focus {
            outline: none;
        }
        .btn-width {
            width: 6rem;
        }
    </style>
<script type="text/javascript" src=/js/approval/approvallist.js></script>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>결재 목록조회</h2>
        </div>
        <div>
            <div>
                총 ${apprList.apprCnt} 건
            </div>
            <c:if test="${!searchApproval.searchAuth && apprList.apprCnt ne 0
                            && searchApproval.searchStatus eq 'progresslist'}">
                <div style="text-align: end;">
                    <button type="button" id="btn-appr-write" class="btn-width">기안서 작성</button>
                </div>
            </c:if>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="prdt-check-all" 
                                    data-target-class="target-appr-id"/>
                            <label for="prdt-check-all"></label>
                        </th>
                        <th>작성일자</th>
                        <th>종류</th>
                        <th>문서번호</th>
                        <th>문서제목</th>
                        <th>기안자</th>
                        <th>결재상태</th>
                        <c:if test="${searchApproval.searchStatus eq 'completelist'}">
                            <th>비품변경상태</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty apprList.apprList}">
                            <c:forEach items="${apprList.apprList}" var="approval">
                            <tr>
                                <td>
                                    <input type="checkbox" id="${approval.apprId}" class="target-appr-id" name="targetApprId" value="${approval.apprId}">
                                    <label for="${approval.apprId}"></label>
                                </td>
                                <td>${approval.dmdDt}</td>
                                <td>
                                    <c:if test="${approval.apprCtgr eq '902'}">
                                        <span class="badge bg-label-info">비품변경</span>
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
                                <c:if test="${searchApproval.searchStatus eq 'completelist'}">
                                    <td>
                                        <c:choose>
                                            <c:when test="${approval.apprSts eq '802'}">
                                                <c:if test="${approval.rntlSts eq '1101'}">
                                                    <span class="badge bg-label-warning">비품반납대기</span>
                                                </c:if>
                                                <c:if test="${approval.rntlSts eq '1102'}">
                                                    <span class="badge bg-label-warning">비품반납완료</span>
                                                </c:if>
                                                <c:if test="${approval.rntlSts eq '1103'}">
                                                    <span class="badge bg-success">비품변경완료</span>
                                                </c:if>
                                            </c:when>
                                            <c:when test="${approval.apprSts eq '803'}">
                                                <span class="badge bg-label-danger">비품변경불가</span>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </c:if>
                            </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
							<tr>
								<td colspan="8">
									<!-- <a href="/approval/write"> -->
										기안서가 존재하지 않습니다.
									<!-- </a> -->
								</td>
							</tr>
						</c:otherwise>
                    </c:choose>	
                </tbody>
            </table>
        </div>
        <nav aria-label="Page navigation">
            <form id="search-form">
                <div class="search-data">
                    <div class="search-date">
                        <input type="date" name="searchDate" value="${searchApproval.searchDate}">
                        <button type="button" id="btn-search-date">날짜검색</button>
                    </div>
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
                </div>
				<ul class="pagination">
                    <c:if test="${searchApproval.hasPrevGroup}">
                        <li class="page-item first">
                            <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
                        </li>
                        <li class="page-item prev">
                            <a class="page-link" href="javascript:search(${searchApproval.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
                        </li>
                    </c:if>
                    <c:forEach begin="${searchApproval.groupStartPageNo}" end="${searchApproval.groupEndPageNo}" step="1" var="p">
                        <li class="${searchApproval.pageNo eq p ? 'active' : ''} page-item">
                            <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${searchApproval.hasNextGroup}">
                        <li class="page-item next">
                            <a class="page-link" href="javascript:search(${searchApproval.nextGroupStartPageNo});"><img src="/images/chevron-right.svg"/></a>
                        </li>
                        <li class="page-item last">
                            <a class="page-link" href="javascript:search(${searchApproval.groupCount - 1});"><img src="/images/chevron-double-right.svg"/></a>
                        </li>
                    </c:if>
                </ul>
            </form>
        </nav>
    </div>
</body>
</html>