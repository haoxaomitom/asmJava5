const getAllProduct = async () => {
    let productContainer = $('#getAllProduct');
    await axios.get('/api/get-all-product')
        .then(response => {
            productContainer.html('');
            console.log(response.data.data);
            response.data.data.forEach(product => {
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
                productContainer.append(html);
            });
        })
        .catch(error => {
            alert(error);
        });
}
// Gọi hàm getAllProduct khi trang được tải
document.addEventListener('DOMContentLoaded', getAllProduct);
$(document).on('click', '.edit-btn', function() {
    // Trích xuất ID sản phẩm từ thuộc tính data-id của nút "edit" đã nhấn
    let productId = $(this).data('id');

    // Gửi yêu cầu GET để lấy dữ liệu của sản phẩm từ API
    axios.get(`/api/get-product-by-id/${productId}`)
        .then(response => {
            let productData = response.data.data; // Dữ liệu sản phẩm được trả về từ API
            console.log("Product Data:", productData);
            // Điền các trường form với dữ liệu của sản phẩm
            $('#masp').val(productData.maSP);
            $('#tensp').val(productData.tenSP);
            $('#hangsx').val(productData.hangSX);
            $('#hinh').val(productData.hinh);
            $('#giaban').val(productData.giaBan);
            $('#giagoc').val(productData.giaGoc);
            $('#khuyenmai').val(productData.khuyenMai);
            $('#soluong').val(productData.soLuong);

            // Hiển thị form chỉnh sửa sản phẩm
            // Ví dụ: nếu bạn có một modal để hiển thị form, bạn có thể kích hoạt modal ở đây.
        })
        .catch(error => {
            console.error('Error fetching product data:', error);
        });
});
