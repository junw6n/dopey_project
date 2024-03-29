<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ToDoList</title>
</head>
<script>
    function setStatus(id) {
        document.getElementById("set_" + id).click();
    }
</script>
<style>
    table {
        font-size: 13px;
    }
    .list {
        width: 500px;
        border-top: 1px solid #444444;
        border-collapse: collapse;
        margin-bottom: 50px;
    }
    .list td {
        border-bottom: 1px solid #444444;
        vertical-align: middle;
    }
    td form {
        margin: 0;
    }
    thead td {
        font-weight: bold;
    }
</style>
<body>
<header><h1>DOPEY PROJECT</h1></header>
<main>

    <%-- The section of view --%>
    <h3>Idle</h3>
    <table id="idle" class="list">
        <thead>
            <tr>
                <td>title</td>
                <td>description</td>
                <td>status</td>
                <td></td>
            </tr>
        </thead>
        <c:forEach items="${list}" var="todo">
            <c:if test="${todo.status.intValue() eq 0}">
                <tr>
                    <td>${todo.title}</td>
                    <td>${todo.description}</td>
                    <td>
                        <form action="/todolist/setStatus" method="post">
                            <input type="hidden" name="id" value="${todo.id}">
                            <select name="status" onchange="setStatus(${todo.id})">
                                <option value="0" selected>idle</option>
                                <option value="1">in progress</option>
                                <option value="2">complete</option>
                            </select>
                            <input style="display: none" type="submit" value="modify" id="set_${todo.id}">
                        </form>
                    </td>
                    <td>
                        <form action="/todolist/remove" method="post">
                            <input type="hidden" name="id" value="${todo.id}">
                            <input type="submit" value="X">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <h3>In Progress</h3>
    <table id="in_progress" class="list">
        <thead>
        <tr>
            <td>title</td>
            <td>description</td>
            <td>status</td>
            <td></td>
        </tr>
        </thead>
        <c:forEach items="${list}" var="todo">
            <c:if test="${todo.status.intValue() eq 1}">
                <tr>
                    <td>${todo.title}</td>
                    <td>${todo.description}</td>
                    <td>
                        <form action="/todolist/setStatus" method="post">
                            <input type="hidden" name="id" value="${todo.id}">
                            <select name="status" onchange="setStatus(${todo.id})">
                                <option value="0">idle</option>
                                <option value="1" selected>in progress</option>
                                <option value="2">complete</option>
                            </select>
                            <input style="display: none" type="submit" value="modify" id="set_${todo.id}">
                        </form>
                    </td>
                    <td>
                        <form action="/todolist/remove" method="post">
                            <input type="hidden" name="id" value="${todo.id}">
                            <input type="submit" value="X">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <h3>Complete</h3>
    <table id="complete" class="list">
        <thead>
        <tr>
            <td>title</td>
            <td>description</td>
            <td>status</td>
            <td></td>
        </tr>
        </thead>
        <c:forEach items="${list}" var="todo">
            <c:if test="${todo.status.intValue() eq 2}">
                <tr>
                    <td>${todo.title}</td>
                    <td>${todo.description}</td>
                    <td>
                        <form action="/todolist/setStatus" method="post">
                            <input type="hidden" name="id" value="${todo.id}">
                            <select name="status" onchange="setStatus(${todo.id})">
                                <option value="0">idle</option>
                                <option value="1">in progress</option>
                                <option value="2" selected>complete</option>
                            </select>
                            <input style="display: none" type="submit" value="modify" id="set_${todo.id}">
                        </form>
                    </td>
                    <td>
                        <form action="/todolist/remove" method="post">
                            <input type="hidden" name="id" value="${todo.id}">
                            <input type="submit" value="X">
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <%-- The section of add --%>
        <form action="/todolist/add" method="post">
            <table>
                <tr>
                    <td>title:</td>
                    <td><input type="text" name="title"></td>
                </tr>
                <tr>
                    <td>description:</td>
                    <td><input type="text" name="description"></td>
                </tr>
            </table>
            <input type="submit" value="add todo">
        </form>
</main>
</body>
</html>