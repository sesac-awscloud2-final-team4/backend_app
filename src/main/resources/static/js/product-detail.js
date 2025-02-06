function showProduct(productId) {
    const recommended = [];

    document.querySelectorAll('.card').forEach(function(card) {
        recommended.push(card.id);
    });

    window.location.href = '/product/' + productId + '?rec=' + recommended.join(',');
}