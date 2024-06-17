const getAllProduct = async (pageNo = 1) => {
    let productContainer = $('#getAllProduct');
    await axios.get(`/api/get-all-product?pageNo=${pageNo}`)
        .then(response => {
            productContainer.html('');
            const products = response.data.data;
            const totalPages = response.data.totalPages;
            const currentPage = response.data.currentPage;

            // Render product items
            products.forEach(product => {
                let salePrice = product.giaBan * (1 - product.khuyenMai); // Calculate sale price after discount
                let discountPercentage = Math.round(product.khuyenMai * 100); // Calculate discount percentage
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
                productContainer.append(html);
            });

            // Render pagination
            const pagination = $('.pagination');
            pagination.html('');

            if (currentPage > 1) {
                pagination.append(`
                    <li class="page-item">
                        <a class="page-link" href="#" onclick="getAllProduct(${currentPage - 1})">Previous</a>
                    </li>
                `);
            }

            for (let i = 1; i <= totalPages; i++) {
                pagination.append(`
                    <li class="page-item ${i === currentPage ? 'active' : ''}">
                        <a class="page-link" href="#" onclick="getAllProduct(${i})">${i}</a>
                    </li>
                `);
            }

            if (currentPage < totalPages) {
                pagination.append(`
                    <li class="page-item">
                        <a class="page-link" href="#" onclick="getAllProduct(${currentPage + 1})">Next</a>
                    </li>
                `);
            }
        })
        .catch(error => {
            alert("An error occurred while fetching the products.");
            console.error(error);
        });
}

// Initialize the first page load
document.addEventListener('DOMContentLoaded', () => getAllProduct(1));
