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
         <div>
                <label for="prof-no">
                    교수번호:
                </label>
                <input type="text" id="prof-no" v-model="info.profNo" readonly disabled/>
            </div>

            <div>
                <label for="prof-name">
                    이름
                </label>
                <input type="text" id="prof-name" v-model="info.name" />

            </div>
            <div>
                <label for="prof-position">
                    직급:
                </label>
                <input type="text" id="prof-position" v-model="info.position" />
            </div>
            <div>
                <label for="pay">
                    급여:
                </label>
                <input type="text" id="pay" v-model="info.pay" />
            </div>

            <div>
                <button @click="fnEdit">
                    수정
                </button>
                <button @click="fnBack">
                    뒤로가기
                </button>
            </div>

    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                profNo: "${profNo}",
                info: {
                    profNo:"",
                    name:"",
                    position:"",
                    pay:""
                }
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnInfo : function () {
                let self = this;
                let param = {
                    profNo: self.profNo
                };
                $.ajax({
                    url: "http://localhost:8080/professor/info.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(self.profNo);
                        self.info = data.info;
                    }
                });
            },
            fnEdit: function(){
                let self = this;
                console.log("이름: "+self.info.name);
                let param = {
                    profNo: self.profNo,
                    name: self.info.name,
                    position: self.info.position,
                    pay: self.info.pay
                };
                $.ajax({
                    url: "http://localhost:8080/professor/edit.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        if(data.result == "success"){
                            alert("변경이 완료되었습니다.");
                            location.href = "view.do?profNo=" + self.profNo;
                        }else{
                            alert("변경 실패");
                            history.back();
                        }
                        
                    }
                });
            }, fnBack: function(){
                history.back();
            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnInfo();
        }
    });

    app.mount('#app');
</script>