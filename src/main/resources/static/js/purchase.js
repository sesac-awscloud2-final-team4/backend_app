document.addEventListener("DOMContentLoaded", function() {
    // 모든 별점 라디오 버튼에 이벤트 추가
    document.querySelectorAll('.rate input[type="radio"]').forEach(function(radio) {
        radio.addEventListener("change", function() {
            // 클릭한 라디오 버튼에서 orderId, itemId, 별점 값 추출
            let rateDiv = this.closest('.rate');  // 부모 div 찾기
            let rateId = rateDiv.id.split('-');   // id에서 orderId, itemId 추출
            let orderId = rateId[0];              // 주문 ID
            let productId = rateId[1];               // 상품 ID
            let rating = this.value;              // 선택한 별점 값

            // 서버로 전송할 데이터 구성
            let data = {
                orderId: orderId,
                productId: productId,
                rating: rating
            };

            // 서버로 POST 요청 보내기
            fetch('/rating', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .catch(error => {
                    console.error("Error:", error);
                    alert("서버 요청 중 오류 발생");
                });
        });
    });
});