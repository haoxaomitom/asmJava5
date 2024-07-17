// listProduct.js

const paginationConfig = {
    pageSize: 15,
    pageNo: 1
};

const fetchProducts = async (endpoint, pageNo = paginationConfig.pageNo, pageSize = paginationConfig.pageSize) => {
    let productContainer = $('#getAllProduct');
    try {
        const response = await axios.get(`/api/admin/${endpoint}?pageNo=${pageNo}&pageSize=${pageSize}`);
        productContainer.html('');
        const products = response.data.data;
        const totalPages = response.data.totalPages;
        const currentPage = response.data.currentPage;

        return { products, totalPages, currentPage };
    } catch (error) {
        alert("An error occurred while fetching the products.");
        console.error(error);
    }
};

const renderPagination = (paginationElement, totalPages, currentPage, fetchFunction) => {
    paginationElement.html('');

    if (currentPage > 1) {
        paginationElement.append(`
            <li class="page-item">
                <a class="page-link" href="#" onclick="${fetchFunction}(${currentPage - 1})">Previous</a>
            </li>
        `);
    }

    for (let i = 1; i <= totalPages; i++) {
        paginationElement.append(`
            <li class="page-item ${i === currentPage ? 'active' : ''}">
                <a class="page-link" href="#" onclick="${fetchFunction}(${i})">${i}</a>
            </li>
        `);
    }

    if (currentPage < totalPages) {
        paginationElement.append(`
            <li class="page-item">
                <a class="page-link" href="#" onclick="${fetchFunction}(${currentPage + 1})">Next</a>
            </li>
        `);
    }
};

const listProduct = async (pageNo = paginationConfig.pageNo) => {
    try {
        const { products, totalPages, currentPage } = await fetchProducts('get-all-product', pageNo);

        $('#getAllProduct').html('');

        products.forEach(product => {
            let html = `
                <tr>
                    <td>${product.maSP}</td>
                    <td>${product.tenSP}</td>
                    <td>${product.hangSX}</td>
                    <td>${product.hinh}</td>
                    <td>${product.giaGoc}</td>
                    <td>${product.giaBan}</td>
                    <td>${product.khuyenMai}</td>
                    <td>${product.soLuong}</td>
                    <td>
                        <button class="btn btn-sm btn-danger edit-btn" data-id="${product.maSP}">edit</button>
                    </td>
                </tr>
            `;
            $('#getAllProduct').append(html);
        });

        renderPagination($('.pagination'), totalPages, currentPage, 'listProduct');

    } catch (error) {
        alert("An error occurred while fetching the products.");
        console.error(error);
    }
};

// Initialize the first page load
$(document).ready(function() {
    listProduct();
});

$(document).on('click', '.edit-btn', function() {
    let productId = $(this).data('id');

    axios.get(`/api/get-product-by-id/${productId}`)
        .then(response => {
            let productData = response.data.data;
            console.log("Product Data:", productData);

            $('#masp').val(productData.maSP);
            $('#tensp').val(productData.tenSP);
            $('#hangsx').val(productData.hangSX);
            $('#hinh').val(productData.hinh);
            $('#giaban').val(productData.giaBan);
            $('#giagoc').val(productData.giaGoc);
            $('#khuyenmai').val(productData.khuyenMai);
            $('#soluong').val(productData.soLuong);

            // Show edit form or modal
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
});
