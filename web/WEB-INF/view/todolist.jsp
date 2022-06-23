<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ToDoList</title>
</head>
<body>
    <header><h1>DOPEY PROJECT</h1></header>
    <main>
        <h3>Idle</h3>
        <table id="idle">
            <c:forEach items="${list}" var="todo">
                <c:if test="${todo.status eq 0}">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.description}</td>
                        <td>${todo.status}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        <h3>In Progress</h3>
        <table id="in_progress">
            <c:forEach items="${list}" var="todo">
                <c:if test="${todo.status eq 1}">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.description}</td>
                        <td>${todo.status}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

        <h3>Complete</h3>
        <table id="complete">
            <c:forEach items="${list}" var="todo">
                <c:if test="${todo.status eq 2}">
                    <tr>
                        <td>${todo.title}</td>
                        <td>${todo.description}</td>
                        <td>${todo.status}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>

    </main>
</body>
</html>
