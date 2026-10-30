<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src="/js/page-change.js"></script>
    <style>
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: beige;
        }
        tr:nth-child(even){
            background-color: azure;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <h1>EMP 리스트</h1>

        <label for="salgrade-select">급여등급</label>
        <select id="salgrade-select" v-model="salgrade" @change="getEmpList">
            <option value="">전체</option>
            <option v-for="item in salgradeList" :value="item.grade">{{item.grade}}등급</option>
        </select>

        <hr>

        <table>
            <thead>
                <tr>
                    <th>
                        사원번호
                    </th>
                    <th>
                        이름
                    </th>
                    <th>
                        부서명
                    </th>
                    <th>
                        직급
                    </th>
                    <th>
                        고용일
                    </th>
                    <th>
                        급여
                    </th>
                    <th>
                        급여등급
                    </th>
                    <th>
                        삭제
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in list">
                    <td>
                        {{item.empno}}
                    </td>
                    <td>
                        <a href="javascript:;" @click="viewEmployee(item.empno)">
                            {{item.ename}}
                        </a>
                    </td>
                    <td>
                        {{item.dname}}
                    </td>
                    <td>
                        {{item.job}}
                    </td>
                    <td>
                        {{item.hiredate}}
                    </td>
                    <td>
                        {{item.sal}}
                    </td>
                    <td>
                        {{item.grade}}
                    </td>
                    <td>
                        <button @click="removeEmp(item.empno)">삭제</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                list: [],
                salgrade:"",
                salgradeList: [],
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            getEmpList : function () {
                let self = this;
                let param = {
                    salgrade: self.salgrade
                };
                $.ajax({
                    url: "http://localhost:8080/emp/list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        if(data.result == "success"){
                            self.list = data.list;
                        }
                    }
                });
            },
            removeEmp: function(empNo){
                let self = this;

                if(!confirm("삭제하시겠습니까?")){
                    return;
                }

                let param = {
                    empNo
                };
                $.ajax({
                    url: "http://localhost:8080/emp/remove.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        if(data.result == "success"){
                            alert("삭제 성공");
                            self.getEmpList();
                        }
                    }
                });
            },viewEmployee: function(empNo){
                location.href = "/emp/view.do?empNo=" + empNo;



            }, loadSalgradeSelect : function(){
                let self = this;
                let param = {};
                $.ajax({
                    url: "http://localhost:8080/emp/get-salgrade-list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        self.salgradeList = data.salgradeList;
                    }
                });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.getEmpList();
            self.loadSalgradeSelect();
        }
    });

    app.mount('#app');
</script>