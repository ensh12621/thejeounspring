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
        <h1>사원추가</h1>
        <hr>

        <div>
            <label>
                사번:
                <input type="text" v-model="empNo">
            </label>
            <button @click="checkEmpNoDuplication">중복체크</button>
        </div>
        <div>
            <label>
                이름:
                <input type="text" v-model="ename">
            </label>
        </div>
        <div>
            <label>
                직급:
                <input type="text" v-model="job">
            </label>
        </div>
        <div>
            <label>
                급여:
                <input type="text" v-model="sal">
            </label>
        </div>
        <div>
            <button @click="addEmployee">추가</button>
        </div>

    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                empNo: "",
                ename:"",
                job:"",
                sal:""
            };
        },
        methods: {

            addEmployee: function (){
                let self = this;
                let param = {
                    empNo: self.empNo,
                    ename: self.ename,
                    job: self.job,
                    sal: self.sal
                };
                $.ajax({
                    url: "http://localhost:8080/emp/add.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        if(data.result == "success"){
                            alert("추가되었슴다");
                            location.href= "/emp/list.do";
                        }else{
                            alert("오류가 발생하였습니다. 다시 시도해주세요.");

                        }
                    }
                });

            },checkEmpNoDuplication: function(){
                let self = this;
                let param = {
                    empNo: self.empNo,
                };
                $.ajax({
                    url: "http://localhost:8080/emp/check-empNo-duplication.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        if(data.isDuplicated){
                            alert("중복되는 학번입니다. 다른 값을 입력해주세요");
                        }else{
                            alert("사용가능한 학번입니다.");
                        }
                    }
                });
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
        }
    });

    app.mount('#app');
</script>