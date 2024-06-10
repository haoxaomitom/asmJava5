const detailProduct = async () => {
    let productContainer = $('#details');
    let urlPath = window.location.pathname;
    let id = urlPath.split('/').pop();
    await axios.get(`/api/get-product-by-id/${id}`)
        .then(response => {
            let product = response.data.data;
            if (product) {
                productContainer.html('');
                let salePrice = product.giaBan * (1 - product.khuyenMai); // Tính giá bán sau khuyến mãi
                let discountPercentage = Math.round(product.khuyenMai * 100); // Tính phần trăm khuyến mãi
                let html = `
                <div class="name-product">
                    <p>${product.tenSP}</p>
                </div>
                <div class="item-price">
                    <p>${product.giaBan.toLocaleString()} (-${discountPercentage}%)</p>
                </div>
                <div class="sale-off">
                    <p>${salePrice.toLocaleString()}</p>
                </div>
                `;
                productContainer.append(html);
            } else {
                productContainer.html('<p>No product found</p>');
            }
        })
        .catch(error => {
            alert(error);
        });
}

// Gọi hàm detailProduct khi trang được tải
document.addEventListener('DOMContentLoaded', detailProduct);
