const getCart = async () => {
    let container = $('#cart-items');
    let totalPrice = 0;
    let urlPath = window.location.pathname;
    let id = urlPath.split('/').pop();
    await axios.get(`/api/get-order-by-makh-and-status/${id}`)
        .then(response => {
            container.html('');
            console.log(response.data.data);
            response.data.data.orderDetails.forEach(item => {
                let total = item.product.giaBan* item.quantity;
                let html = `
                    <tr>
                        <td>${item.product.tenSP}</td>
                        <td>${item.product.giaBan.toLocaleString()} VND</td>
                        <td><div>
                            <button class="btn-decrease" data-id="${item.orderDetailId}" style="width: 25px">-</button>
                            <input style="width: 50px" type="text" class="text-center quantity" value="${item.quantity}" readonly>
                            <button class="btn-increase" data-id="${item.orderDetailId}" style="width: 25px">+</button>
                        </div></td>
                        
                        <td>${total.toLocaleString()} VND</td>
                        <td><button class="btn btn-danger btn-sm" onclick="removeItem(${item.orderDetailId})">Xóa</button></td>
                    </tr>
                `;
                totalPrice += item.price * item.quantity;
                container.append(html);
            });
            $('#total-price').text(totalPrice.toLocaleString() + ' VND');
        })
        .catch(error => {
            alert(error);
        });
}
const removeItem = async (orderDetailId) => {
    await axios.delete(`/api/delete-order-detail?id=${orderDetailId}`)
        .then(response => {
            alert(response.data.message);
            getCart();
        })
        .catch(error => {
            alert(error);
        });
}
const updateQuantity = async (orderDetailId, quantity) => {
    await axios.put('/api/update-order-detail',null, {
        params: {
            id: orderDetailId,
            quantity: quantity
        },
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            getCart(); // Reload cart to reflect updated quantity
        })
        .catch(error => {
            alert(error);
        });
}


    $(document).on('click','.btn-increase', function() {
        let orderDetailId = $(this).data('id');
        let input = $(this).siblings('input');
        let quantity = parseInt(input.val()) + 1;
        input.val(quantity);
        updateQuantity(orderDetailId, quantity);
    });

    $(document).on('click','.btn-decrease', function() {
        let orderDetailId = $(this).data('id');
        let input = $(this).siblings('input');
        let quantity = parseInt(input.val()) - 1;
        if (quantity >= 1) {
            input.val(quantity);
            updateQuantity(orderDetailId, quantity);
        }
    });

// Gọi hàm getAllProduct khi trang được tải
document.addEventListener('DOMContentLoaded', getCart);
