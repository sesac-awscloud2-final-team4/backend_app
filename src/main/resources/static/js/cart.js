// 장바구니 총액 업데이트 함수
function updateTotalPrice() {
    let totalPrice = 0;
    document.querySelectorAll(".cart-item").forEach(item => {
        const price = parseInt(item.dataset.price, 10);
        const quantity = parseInt(item.querySelector(".item-quantity").textContent, 10);
        totalPrice += price * quantity;
    });

    document.getElementById("total-price").textContent = totalPrice.toLocaleString() + "원";

    // 장바구니 비어있는 경우 메시지 표시
    checkCartEmpty();
}

// 장바구니 비었는지 확인 후 메시지 표시
function checkCartEmpty() {
    const cartItems = document.querySelectorAll(".cart-item");
    const emptyMessage = document.getElementById("empty-cart-message");
    const orderSummaryCard = document.getElementById("order-summary");

    if (cartItems.length === 0) {
        emptyMessage.style.display = "block";
        orderSummaryCard.style.display = "none";
    } else {
        emptyMessage.style.display = "none";
        orderSummaryCard.style.display = "block";
    }
}

// 상품 수량 업데이트 (+, - 버튼)
function updateCartItem(itemId, change) {
    const cardElement = document.getElementById(`cart-item-${itemId}`);
    const quantityElement = cardElement.querySelector(".item-quantity");
    let newQuantity = parseInt(quantityElement.textContent, 10) + change;

    if (newQuantity < 1) return;

    quantityElement.textContent = newQuantity;

    fetch(`/cart/update`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ itemId: itemId, quantity: newQuantity })
    })
        .then(() => updateTotalPrice())  // 총액 업데이트
        .catch(error => console.error("수량 변경 오류:", error));
}

// 페이지 로드 시 초기 확인
document.addEventListener("DOMContentLoaded", () => {
    updateTotalPrice();
    checkCartEmpty();
});

// 상품 삭제
function deleteCartItem(itemId) {
    // 서버 삭제 요청
    fetch(`/cart/delete/${itemId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    })
        .then(() => { // 카드 삭제
            const cardElement = document.getElementById(`cart-item-${itemId}`);
            if (cardElement) {
                cardElement.remove();
            }
        })
        .then(() => updateTotalPrice())  // 총액 업데이트
        .catch(error => console.error("삭제 오류:", error));
}

// 장바구니 비우기 함수
function clearCart() {
    // 모든 장바구니 아이템 제거
    document.querySelectorAll(".cart-item").forEach(item => item.remove());

    // 서버에 장바구니 비우기 요청
    fetch("/cart/clear", {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    })
        .then(() => {
            updateTotalPrice();
        })
        .catch(error => console.error("장바구니 비우기 오류:", error));
}

// 장바구니 구매하기 함수
function buyCart() {
    const cartItems = [];
    document.querySelectorAll(".cart-item").forEach(item => {
        const itemId = item.id.split('-').at(2);
        const itemName = item.querySelector(".item-name").innerText;
        const price = item.dataset.price
        const quantity = item.querySelector(".item-quantity").innerText;

        cartItems.push({
            itemId: itemId,
            price: parseInt(price),
            quantity: parseInt(quantity),
            itemName: itemName
        });
    });

    if(cartItems.length < 1) return;

    // 서버에 구매하기 요청
    fetch("/purchase/buy", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cartItems),
        redirect: 'follow'
    })
        .then(() =>
            window.location.href = '/purchase'
        )
        .catch(error => console.error("구매하기 오류:", error));
}
