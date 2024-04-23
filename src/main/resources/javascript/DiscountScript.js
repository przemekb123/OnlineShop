function showDiscountModal() {
    $('#discountModal').modal('show');
}

function applyDiscount() {
    var discountedPrice = $('#discountPrice').val();


    $('.product-container').addClass('product-discounted');

    $('#discountModal').modal('hide');
}