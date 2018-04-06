window.onload = function () {
    var a = document.getElementById("releaseDateId").value;
    if (a === null || a === "") {
        document.getElementById("releaseDateId").value = "1111-11-11";
    }
};