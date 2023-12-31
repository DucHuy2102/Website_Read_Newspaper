<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="authUser" scope="session" type="com.messi.king.messinews.model.bean.Users"/>
<jsp:useBean id="allPCategories" scope="request"
             type="java.util.List<com.messi.king.messinews.model.bean.ParentCategories>"/>
<jsp:useBean id="allCategories" scope="request" type="java.util.List<com.messi.king.messinews.model.bean.Categories>"/>

<nav>
    <form id="actionFrom" action="" method="post">
        <div class="collapse navbar-collapse d-flex justify-content-between px-4 py-3">
            <div class="d-flex align-items-center" style="width: 50%">
                <div class="pl-3">
                    <a name="logo" href="${pageContext.request.contextPath}/" style="text-decoration: none">
                        <img src="${pageContext.request.contextPath}/photos/logos/LogoNews.png" alt="Logo Newspaper"
                             style="width: 90px">
                    </a>
                </div>
                <div class="pl-1 pt-3">
                    <a href="${pageContext.request.contextPath}/" style="text-decoration: none"
                       class="d-flex flex-column align-items-center">
                        <h2 style="line-height: 5px;color: black"><b>Newspaper</b></h2>
                        <%--                        display time--%>
                        <div id="date" style="margin-top: 10px; font-weight: bold">
                            <%--                            <%= (new java.util.Date()).toLocaleString()%>--%>
                            <script>
                                var now = new Date();
                                var date = now.toLocaleDateString();
                                document.getElementById('date').innerHTML = date;
                            </script>
                        </div>
                    </a>
                </div>

                <div class="input-group pl-5" style="width: 80%; height: 53px">
                    <div class="input-group-prepend">
                        <span class="input-group-text bgColorGray"
                              style="border-top-left-radius: 20px;border-bottom-left-radius: 20px; border-style: none">
                            <button id="iconSearch"
                                    onclick="iconSearch('${pageContext.request.contextPath}/Home/Search?key=')"
                                    formmethod="get" style="border-style: none">
                                <i class="fa fa-search" aria-hidden="true" style=" color: grey"></i>
                            </button>
                        </span>
                    </div>
                    <input id="search" name="key"
                           onkeypress="searchInfo('${pageContext.request.contextPath}/Home/Search?key=',event)"
                           type="text" class="form-control bgColorGray h-100"
                           placeholder="Bạn muốn tìm bài báo nào?"
                           style="border-top-right-radius: 20px;border-bottom-right-radius: 20px; border-style: none; outline: none">
                </div>
            </div>
            <div class="d-flex">
                <div class="dropdown-menu-right">
                    <a class="btn bgColorGray" href="#" role="button" data-toggle="dropdown"
                       aria-expanded="false" style="border-radius: 40px">
                        <div class="d-flex justify-items-center align-items-center">
                            <div>
                                <c:choose>
                                    <c:when test="${authUser.role >= 1 && authUser.role<=4}">
                                        <img class="imageIcon"
                                             src="${pageContext.request.contextPath}/photos/userAvatar/${authUser.id}/avatar.png"
                                             alt="">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="imageIcon"
                                             src="${pageContext.request.contextPath}/photos/userAvatar/defaultAvatar.jpg">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>
                                <i class="fa fa-bars pl-3 pr-1" aria-hidden="true"></i>
                            </div>
                        </div>
                    </a>
                    <div class="dropdown-menu">
                        <c:choose>
                            <c:when test="${authUser.role == 1}">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Profile">Thông
                                    tin cá nhân</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Password">Thay
                                    đổi mật khẩu</a>
                                <button class="dropdown-item"
                                        formaction="${pageContext.request.contextPath}/Account/Logout">Đăng xuất
                                </button>
                            </c:when>

                            <c:when test="${authUser.role == 2}">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Profile">Thông
                                    tin cá nhân</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Password">Thay
                                    đổi mật khẩu</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Writer/List">Quản lý
                                    các
                                    bài viết</a>
                                <button class="dropdown-item"
                                        formaction="${pageContext.request.contextPath}/Account/Logout">Đăng xuất
                                </button>
                            </c:when>

                            <c:when test="${authUser.role == 3}">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Profile">Thông
                                    tin cá nhân</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Password">Thay
                                    đổi mật khẩu</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Editor/ArticleList">Danh
                                    sách các bài báo</a>
                                <button class="dropdown-item"
                                        formaction="${pageContext.request.contextPath}/Account/Logout">Đăng xuất
                                </button>
                            </c:when>

                            <c:when test="${authUser.role == 4}">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Profile">Thông
                                    tin cá nhân</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Password">Thay
                                    đổi mật khẩu</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/Admin/Component/List">Quản lý
                                    thông tin bài báo</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/Users/List">Quản
                                    lý nhân sự</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/Admin/Articles/List">Quản lý các bài
                                    báo</a>
                                <button class="dropdown-item"
                                        formaction="${pageContext.request.contextPath}/Account/Logout">Đăng xuất
                                </button>
                            </c:when>

                            <c:otherwise>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Login">Đăng
                                    nhập</a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Register">Đăng
                                    ký</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>


        <!--    10 danh mục ở đầu-->
        <div class="d-flex justify-content-center w-100">
            <div class="d-flex justify-content-between "
                 style="border-style: solid; border-left: none; border-right: none; border-radius: 0; width: 70%">
                <c:forEach items="${allPCategories}" var="c">
                    <div class="dropdown">
                        <a href="${pageContext.request.contextPath}/Home/ByPCat?id=${c.id}" role="button"
                           class="dropbtn btn btn-outline-secondary">
                                ${c.name_parent_cate}
                        </a>
                        <div class="dropdown-content" style="margin-top: 2px">
                            <c:forEach items="${allCategories}" var="d">
                                <c:if test="${d.parent_cate_id == c.id}">
                                    <a href="${pageContext.request.contextPath}/Home/ByCat?id=${d.id}">${d.name_category}</a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <br>
        <script>
            document.getElementById('PCat').onmouseover = function () {
                document.getElementById('PCat').visible = 'visible'
            }

            function iconSearch(url) {
                document.getElementById('actionFrom').action = url + document.getElementById('search').value.replaceAll(' ', '+')
            }

            function searchInfo(url, e) {
                if (e.keyCode == 13) {
                    document.getElementById('actionFrom').action = url + document.getElementById('search').value.replaceAll(' ', '+')
                }
            }
        </script>
    </form>
</nav>