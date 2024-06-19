document.addEventListener('DOMContentLoaded', function () {
    const query = /*[[${query}]]*/ '';
    if (query.trim() !== '') {
        searchProducts(query);
    } else {
        const resultsContainer = document.getElementById('searchResults');
        resultsContainer.innerHTML = '<p>Vui lòng nhập từ khóa để tìm kiếm.</p>';
    }
});

async function searchProducts(query) {
    try {
        const response = await axios.get(`/api/search-product?query=${query}`);
        const results = response.data.data;
        const resultsContainer = document.getElementById('searchResults');
        resultsContainer.innerHTML = ''; // Clear previous results

        if (results.length > 0) {
            results.forEach(product => {
                let salePrice = product.giaBan * (1 - product.khuyenMai);
                let discountPercentage = Math.round(product.khuyenMai * 100);
                let html = `
                    <div class="item col-sm-2">
                        <a href="/product/detail/${product.maSP}">
                            <div class="hover-item">
                                <div class="null-item"></div>
                                <div class="img-item">
                                    <img src="/img/${product.hinh}" alt="${product.tenSP}">
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
                resultsContainer.innerHTML += html;
            });
        } else {
            resultsContainer.innerHTML = '<p>Không tìm thấy kết quả nào.</p>';
        }
    } catch (error) {
        console.error('Có lỗi xảy ra khi tải kết quả tìm kiếm:', error);
    }
}
