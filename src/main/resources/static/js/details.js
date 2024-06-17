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
console.log("load product")
// Gọi hàm detailProduct khi trang được tải
document.addEventListener('DOMContentLoaded', detailProduct);

const carouselImg = async () => {
    console.log("load img")
    let productContainer = $('#image');
    let urlPath = window.location.pathname;
    let id = urlPath.split('/').pop();
    await axios.get(`/api/get-product-by-id/${id}`)
        .then(response => {
            console.log(response.data); // Log the response data to check if it contains the expected data
            let product = response.data.data;
            if (product) {
                productContainer.html('');
                let html = `
                    <div id="carousel-thumbs" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="/img/${product.hinh}" class="d-block w-100" alt="${product.tenSP}">
                            </div>
                            <!-- Add more carousel items if needed -->
                        </div>
                        <!-- Add carousel indicators if needed -->
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

// Call the carouselImg function when the document is loaded
document.addEventListener('DOMContentLoaded', carouselImg);