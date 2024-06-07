const getAllProduct = async () => {
    let productContainer = $('#getAllProduct');
    await axios.get('/api/get-all-product')
        .then(response => {
            productContainer.html('');
            console.log(response.data.data);
            response.data.data.forEach(product => {
                let salePrice = product.giaBan * (1 - product.khuyenMai); // Tính giá bán sau khuyến mãi
                let discountPercentage = Math.round(product.khuyenMai * 100); // Tính phần trăm khuyến mãi
                let html = `
                <div class="item col-sm-2">
                <a href="/product/detail/${product.maSP}">
                    <div class="hover-item">
                        <div class="null-item"></div>
                        <div class="img-item">
                            <img src="/img/ss.png" alt="Samsung S24 Ultra">
                        </div>
                        <div class="null-item"></div>
                        <div class="name-product">
                            <p>${product.tenSP}</p>
                        </div>
                        <div class="sale-off">
                            <p>${product.giaBan.toLocaleString()} (-${discountPercentage}%)</p>
                        </div>
                        <div class="item-price">
                            <p>${salePrice.toLocaleString()}</p>
                        </div>
                        <div class="null-item"></div>
                    </div>
                </a>
                <div class="add-to-cart">
                    <button class="btn btn-success" data-bs-toggle="modal"
                            data-bs-target="#exampleModal" type="button">Thêm vào giỏ hàng
                    </button>
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Thông báo</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Thêm vào giỏ hàng thành công
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">Đóng
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="null-item"></div>
            </div>
                `;
                productContainer.append(html);
            });
        })
        .catch(error => {
            alert(error);
        });
}
// Gọi hàm getAllProduct khi trang được tải
document.addEventListener('DOMContentLoaded', getAllProduct);
