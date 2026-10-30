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
            <label>
                아이디:
                <input type="text" v-model="userId">
            </label>
        </div>
        
        <div>
            <label>
                비밀번호:
                <input type="password" v-model="userPwd">
            </label>
        </div>
        
        <div>
            <label>
                비밀번호 확인:
                <input type="password" v-model="userPwd2">
            </label>
        </div>
        
        <div>
            <label>
                이름:
                <input type="text" v-model="userName">
            </label>
        </div>
        
        
        <div>
            <label>
                주소:
                <input type="text" v-model="addr">
            </label>
        </div>
        
        <div>
            <button @click="fnJoin">
                가입하기
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
                userId:"",
                userPwd:"",
                userPwd2:"",
                userName:"",
                addr:""
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnJoin : function () {

                let self = this;


                // 1. 아이디는 최소 5글자
                if(!(self.userId.length >= 5)){
                    alert("아이디는 최소 5글자입니다.");
                    return;
                }

                // 2. 아이디는 영어 소문자+숫자 조합
                const idRegEx = /^(?=.*[a-z])(?=.*\d)[a-z0-9]+$/;
                if(!(idRegEx.test(self.userId))){
                    alert("아이디는 영어 소문자와 숫자의 조합이어야 하며 영어 소문자로 시작해야 합니다.");
                    return;
                }

                // 3. 비밀번호는 최소 8글자
                if(!(self.userPwd.length >= 8)){
                    alert("비밀번호는 최소 8글자입니다.");
                    return;
                }

                // 4. 비밀번호는 특수문자 필수 포함

                // 5. 비밀번호와 비밀번호 확인은 같아야 함
                if(self.userPwd != self.userPwd2){
                    alert("비밀번호가 서로 다릅니다.");
                    return;
                }

                // 6. 이름은 한글만

                // 7. 이름, 주소는 빈 값 안됨
                if(self.userName.trim() == "" || self.addr.trim() == ""){
                    alert("필수값을 모두 입력해주세요.");
                }



                let param = {
                    userId: self.userId,
                    userPwd:self.userPwd,
                    userName:self.userName,
                    addr:self.addr
                };
                $.ajax({
                    url: "http://localhost:8080/join.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);
                        if(data.result == "success"){
                            alert("가입 성공");
                        }else{
                            alert("가입 실패");
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