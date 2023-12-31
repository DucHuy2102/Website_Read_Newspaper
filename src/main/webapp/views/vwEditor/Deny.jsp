<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="article" scope="request" type="com.messi.king.messinews.model.bean.Articles"/>
<jsp:useBean id="allPCategories" scope="request"
             type="java.util.List<com.messi.king.messinews.model.bean.ParentCategories>"/>
<jsp:useBean id="allCategories" scope="request" type="java.util.List<com.messi.king.messinews.model.bean.Categories>"/>

<jsp:useBean id="authUser" scope="session" type="com.messi.king.messinews.model.bean.Users"/>

<m:main>
    <jsp:body>
        <form action="" method="post">
            <div class="d-flex justify-content-center bgColorGray">
                <!--    left-->
                <div class="bgColorGray" style="width: 15%">
                </div>

                <!--    center-->
                <div style="width: 70%; background-color: white" class="m-4 p-3">
                    <h4>Xử lý từ chối bài viết</h4>
                    <hr>
                    <br>
                    <h5>Đang xử lý bài: ${article.title}</h5> <br>
                    <div style=" width: 100%">
                        <h5 id="titleTuChoi"></h5>
                        <label for="txtTuChoi"><b>Lý do từ chối</b></label>
                        <textarea id="txtTuChoi" name="reason" class="bgColorGray w-100 p-3" rows="4"
                                  style="border-radius: 10px; border-style: none">
                        </textarea>
                        <br>
                        <br>
                        <div align="end">
                            <c:if test="${authUser.role == 3}">
                                <a href="${pageContext.request.contextPath}/Editor/ArticleList"
                                   class="btn btn-secondary mr-2">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                    Hủy bỏ
                                </a>
                            </c:if>
                            <c:if test="${authUser.role == 4}">
                                <a href="${pageContext.request.contextPath}/Admin/Articles/List"
                                   class="btn btn-secondary mr-2">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                    Hủy bỏ
                                </a>
                            </c:if>
                            <button class="btn btn-success" type="submit" formaction="${pageContext.request.contextPath}/Editor/Deny?id=${article.id}">
                                <i class="fa fa-check" aria-hidden="true"></i>
                                Hoàn thành
                            </button>
                        </div>
                    </div>
                </div>

                <!--    right-->
                <div style="width: 15%" class="d-flex align-items-end flex-column bgColorGray">
                </div>
            </div>
        </form>
    </jsp:body>
</m:main>
