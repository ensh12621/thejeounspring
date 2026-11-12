<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
        <script src="/js/page-change.js"></script>
        <style>
            table,
            tr,
            td,
            th {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 5px 10px;
                text-align: center;
            }

            th {
                background-color: beige;
            }

            tr:nth-child(even) {
                background-color: azure;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <!-- html 코드는 id가 app인 태그 안에서 작업 -->
            <div>
                <h1>제목: {{info.title}}</h1>
                <h2>작성자: {{info.userName}} | 조회수: {{info.cnt}}</h2>
                <hr>
                <h3>내용</h3>
                <img v-for="item in fileList" :src="item.filePath" alt="aa">

                <div v-html="info.contents">
                </div>

                <div v-if="sessionId == info.userId || sessionRole == 'A'">
                    <button @click="fnEdit">
                        수정
                    </button>
                    <button @click="fnRemove">
                        삭제
                    </button>
                </div>

                <hr>

                <div style="width:800px; margin:0 auto;">
                    <h3>댓글 목록</h3>
                    <ul style="list-style-type:none;">
                        <li v-for="item in replyList">
                            <div style="display:flex; justify-content:space-between" v-if="editAt != item.commentNo">
                                <div>
                                    {{item.userName}}
                                    :
                                    {{item.contents}}
                                </div>
                                <div>
                                    {{item.cdatetime}}

                                </div>
                            </div>
                            <div v-if="sessionId == item.userid">
                                <button
                                    @click="fnOpenReplyEdit(item.commentNo);replyEditContent=item.contents">수정</button>
                                <button v-if="editAt != item.commentNo" @click="fnRemoveReply(item.commentNo)">삭제</button>
                                <input type="text" v-if="editAt == item.commentNo" v-model="replyEditContent">
                                <button @click="fnEditReply" v-if="editAt == item.commentNo">수정하기</button>

                            </div>



                        </li>
                    </ul>
                </div>

                <div style="width:800px; margin:0 auto;">
                    <h3>댓글 달기</h3>
                    <input type="text" style="width:300px;" v-model="newReply" @keyup.enter="fnAddReply">
                    <button @click="fnAddReply">등록</button>
                </div>



            </div>
        </div>
    </body>

    </html>

    <script>
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    boardNo: "${boardNo}",
                    info: {},
                    fileList: [],
                    sessionId: "${sessionId}",
                    sessionRole: "${sessionRole}",
                    replyList: [],
                    newReply: "",
                    editAt: "",
                    replyEditContent: "",
                };

            },
            methods: {
                // 함수(메소드) - (key : function())
                fnRemoveReply: function (commentNo) {
                    if (!confirm("삭제하시겠습니까?")) {
                        return;
                    }
                    let self = this;

                    let param = {
                        commentNo: commentNo
                    };
                    $.ajax({
                        url: "http://localhost:8080/board/remove-reply.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            if (data.result == "success") {
                                
                                self.newReply = "";
                                self.replyEditContent = "";
                                self.editAt = "";
                                alert(data.message);
                                self.fnInfo();
                            }
                        }
                    });



                },
                
                fnEditReply: function () {
                    let self = this;

                    if (self.replyEditContent.trim().length == 0) {
                        return;
                    }

                    let param = {
                        commentNo: self.editAt,
                        contents: self.replyEditContent
                    };
                    $.ajax({
                        url: "http://localhost:8080/board/edit-reply.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            if (data.result == "success") {
                                self.fnInfo();
                                self.newReply = "";
                                self.replyEditContent = "";
                                self.editAt = "";
                                alert(data.message);
                                
                            }
                        }
                    });


                },
                fnOpenReplyEdit: function (commentNo) {
                    const self = this;
                    self.editAt = commentNo;
                },
                fnAddReply: function () {
                    let self = this;

                    if (self.newReply.trim().length == 0) {
                        return;
                    }

                    let param = {
                        boardNo: self.boardNo,
                        contents: self.newReply
                    };
                    $.ajax({
                        url: "http://localhost:8080/board/add-reply.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            if (data.result == "success") {
                                self.fnInfo();
                                self.newReply = "";
                            }
                        }
                    });



                },
                fnInfo: function () {
                    let self = this;
                    let param = {
                        boardNo: self.boardNo
                    };
                    $.ajax({
                        url: "http://localhost:8080/board/view.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            self.info = data.info;
                            self.fileList = data.fileList;
                            console.log(data);
                            self.replyList = data.replyList;
                        }
                    });
                }, fnEdit: function () {
                    const self = this;
                    pageChange("/board/edit.do", {
                        boardNo: self.boardNo
                    });
                }, fnRemove: function () {
                    let self = this;
                    let param = {
                        boardNo: self.boardNo
                    };
                    $.ajax({
                        url: "http://localhost:8080/board/remove.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            alert(data.message);
                            if (data.result == "success") {
                                location.href = "/board/list.do";
                            }

                        }
                    });
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