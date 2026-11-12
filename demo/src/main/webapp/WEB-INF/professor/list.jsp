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


            #paging {
                margin-top: 20px;
                display: flex;
                gap: 15px;
                justify-content: center
            }

            #paging a {
                text-decoration: none;
                ;
            }

            #paging .active {
                font-weight: bold;
                color: black;
                font-size: 20px;
            }

            .search {
                text-align: center;
            }

            .search input {
                outline: none;
                border: 0 none;
                border-bottom: 1px solid black;
                padding: 2px 10px;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <!-- html 코드는 id가 app인 태그 안에서 작업 -->
            <h1>교수 목록</h1>


            <div>
                검색어:
                <input type="text" v-model="keyword" @keyup.enter="fnList" />
                <button @click="fnList">검색</button>
            </div>

            <div>
                <select v-model="pageSize" @change="currentPage=1; fnList()">
                    <option value="3">3개씩</option>
                    <option value="10">10개씩</option>
                    <option value="15">15개씩</option>
                    <option value="20">20개씩</option>
                </select>
            </div>


            <div style="margin:10px 0 ; display:flex; gap:10px">
                <label>학과
                    <select name="" id="" v-model="deptNo" @change="fnList">
                        <option value="" selected>:: 전체 ::</option>
                        <option v-for="item in deptList" :value="item.deptNo">
                            {{item.dName}}

                        </option>
                    </select>
                </label>

                <label>직급
                    <select name="" id="" v-model="position" @change="fnList">
                        <option value="" selected>:: 전체 ::</option>
                        <option value="정교수">정교수</option>
                        <option value="전임강사">전임강사</option>
                        <option value="조교수">조교수</option>
                        <option value="정교수">정교수</option>
                    </select>
                </label>

            </div>

            <table>
                <thead>
                    <tr>
                        <th>
                            교수번호
                        </th>

                        <th>
                            교수명
                        </th>
                        <th>
                            직급
                        </th>
                        <th>
                            email
                        </th>
                        <th>
                            학과
                        </th>
                        <th>
                            삭제
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in list">
                        <td>
                            {{item.profNo}}
                        </td>

                        <td>
                            <a href="javascript:;" @click="fnView(item.profNo)">
                                {{item.name}}
                            </a>
                        </td>
                        <td>
                            {{item.position}}
                        </td>
                        <td>
                            {{item.email}}
                        </td>
                        <td>
                            {{item.dName}}
                        </td>
                        <td>
                            <button @click="removeProfessor(item.profNo)">삭제</button>
                        </td>
                    </tr>
                    <tr v-if="list.length == 0">
                        <td colspan="6">
                            조건에 맞는 데이터가 없습니다.

                        </td>

                    </tr>

                </tbody>
            </table>

            <div>
                <a href="add.do">
                    <button>교수추가</button>

                </a>
            </div>

            <div id="paging">
                <a href="javascript:;" v-if="currentPage != 1" @click="currentPage--;fnList();">◀</a>
                <a v-for="item in idx" :class="{active : currentPage == item}" href="javascript:;"
                    @click="currentPage = item; fnList();">

                    {{item}}
                </a>
                <a href="javascript:c;" v-if="currentPage != idx" @click="currentPage++;fnList()">▶</a>
            </div>

        </div>
    </body>

    </html>

    <script>
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    list: [],
                    keyword: "",
                    deptList: [],
                    deptNo: "",
                    position: "",
                    pageSize: 3,
                    idx: 1,
                    currentPage: 1,
                };
            },
            methods: {
                // 함수(메소드) - (key : function())
                fnList: function () {
                    let self = this;
                    let param = {
                        keyword: self.keyword,
                        deptNo: self.deptNo,
                        position: self.position,
                        limit: self.pageSize,
                        offset: (self.currentPage - 1) * self.pageSize,
                    };

                    $.ajax({
                        url: "http://localhost:8080/professor/list.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            self.list = data.list;
                            self.deptList = data.deptList;
                            self.idx = Math.ceil(data.count / self.pageSize);
                            console.log("idx:" + self.idx);
                        }
                    });
                }, removeProfessor: function (profNo) {
                    let self = this;
                    if (!confirm("삭제?")) {
                        return;
                    }
                    $.ajax({
                        url: "http://localhost:8080/professor/remove.dox",
                        dataType: "json",
                        type: "POST",
                        data: {
                            profNo
                        },
                        success: function (data) {
                            if (data.result == "success") {
                                self.fnList();
                            }
                        }
                    });

                }, fnView: function (profNo) {

                    pageChange("view.do", {
                        profNo
                    });
                }

            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;
                self.fnList();
            }
        });

        app.mount('#app');
    </script>