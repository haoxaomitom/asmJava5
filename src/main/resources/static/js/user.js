const loadUserDetails = async () => {
    try {
        // Lấy giá trị của cookie "username"
        const username = getCookie('username');

        // Kiểm tra nếu username tồn tại
        if (username) {
            // Gửi yêu cầu đến API để lấy thông tin của người dùng dựa trên username
            const response = await axios.get(`/api/get-user-by-session`);

            // Kiểm tra phản hồi API
            if (response.data.status) {
                const userData = response.data.data;
                // const birthDate = new Date(userData.ngaySinh).toISOString().split('T')[0];
                const birthDate = new Date(userData.ngaySinh);
                birthDate.setDate(birthDate.getDate() + 1); // Cộng thêm 1 ngày
                const isoDate = birthDate.toISOString().split('T')[0];


                // Đặt giá trị của các trường văn bản trong biểu mẫu từ dữ liệu người dùng
                document.getElementById('full-name').value = userData.tenKH;
                document.getElementById('email').value = userData.email;
                document.getElementById('birthdate').value = isoDate;
                document.getElementById('address').value = userData.diaChi;
                document.getElementById('phone').value = userData.soDT;
                document.getElementById('username').value = userData.makh;

                // Cập nhật giá trị của trường giới tính
                if (userData.gioiTinh === true) {
                    document.getElementById('gender').value = 'true';
                } else if (userData.gioiTinh === false) {
                    document.getElementById('gender').value = 'false';
                }

            } else {
                console.error('Failed to load user details:', response.data.message);
            }
        } else {
            console.error('No username found in cookie.');
        }
    } catch (error) {
        console.error('Error loading user details:', error);
    }
};
// Lấy giá trị của cookie
const getCookie = (name) => {
    const cookieValue = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return cookieValue ? cookieValue[2] : null;
};

// Gọi hàm loadUserDetails khi tài liệu đã sẵn sàng
document.addEventListener('DOMContentLoaded', () => {
    loadUserDetails();
});