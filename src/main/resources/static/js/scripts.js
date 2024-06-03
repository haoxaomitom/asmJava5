document.addEventListener('DOMContentLoaded', function () {
    console.log("Script loaded successfully!"); // Kiểm tra nếu tệp được tải
    const mainImage = document.getElementById('mainImage');
    const thumbnails = document.querySelectorAll('#carousel-thumbs img');

    thumbnails.forEach(thumb => {
        thumb.addEventListener('click', function () {
            mainImage.src = this.src;
            console.log("Image changed to: " + this.src); // Kiểm tra nếu sự kiện click được gọi
        });
    });
});
