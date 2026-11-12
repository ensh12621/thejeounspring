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
        <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e03846a5bae385cef3b2da33124bd8d&libraries=services"></script>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f9f9f9;
            }

            h2 {
                color: #333;
            }

            #map {
                width: 100%;
                height: 500px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin-bottom: 15px;
            }

            .info-box {
                background: #fff;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
                font-size: 15px;
                color: #555;
            }

            .info-box span {
                font-weight: bold;
                color: #007bff;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <h2>📍 카카오 지도 원하는 위치에 핀 찍기</h2>
            <p>지도를 클릭하면 해당 위치에 마커가 생성되고 위도/경도가 표시됩니다.</p>

            <!-- 지도를 표시할 div -->
            <div id="map"></div>

            <!-- 선택된 위치 정보를 보여줄 영역 -->
            <div class="info-box">
                선택된 위치 - 위도: <span id="lat">-</span>, 경도: <span id="lng">-</span>
            </div>
        </div>
    </body>

    </html>

    <script>
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    infowindow: null,
                    map: null,
                    ps: null,
                    category: "",
                    markerList: []
                };
            },
            methods: {
                // 함수(메소드) - (key : function())
                fnMapInit: function () {
                    var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
                    var options = {
                        center: new kakao.maps.LatLng(37.566826, 126.978656), // 지도의 중심좌표 (서울 시청 기준)
                        level: 3 // 지도의 레벨(확대, 축소 정도)
                    };

                    // 2. 지도 생성
                    var map = new kakao.maps.Map(container, options);

                    // 3. 마커를 미리 생성 (클릭할 때마다 위치를 옮기거나 새로 만들 수 있음)
                    var marker = new kakao.maps.Marker({
                        position: map.getCenter()
                    });

                    // 초기 마커는 지도 중심에 표시
                    //marker.setMap(map);

                    // 4. 지도에 클릭 이벤트 등록
                    kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

                        // 클릭한 위도, 경도 정보 가져오기
                        var latlng = mouseEvent.latLng;

                        // 마커의 위치를 클릭한 위치로 이동
                        marker.setPosition(latlng);
                        marker.setMap(map);

                        // HTML 화면에 위도, 경도 좌표 출력
                        document.getElementById('lat').innerText = latlng.getLat();
                        document.getElementById('lng').innerText = latlng.getLng();
                    });
                },

            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;
                self.fnMapInit();
            }
        });

        app.mount('#app');
    </script>