$(function () {
    $("#saveAddress").click(function () {
        var n=$("#name").val();
        var p=$("#phone").val();
        var pro=$("#province").val();
        var city=$("#city").val();
        var county=$("#county").val();
        var town=$("#town").val();
        var a=$("#Address").val();

        $("#addressList ul").prepend("<li>\n" +
            "                                <div class=\"myAddress\">\n" +
            "                                    <div class=\"userInfo\">\n" +
            "                                        <label>"+n+"</label>\n" +
            "                                        <span>"+p+"</span>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"addressDetail\">\n" +
            "                                        <h6 class=\"province\">"+pro+"</h6>\n" +
            "                                        <h6 class=\"city\">"+city+"</h6>\n" +
            "                                        <h6 class=\"county\">"+county+"</h6>\n" +
            "                                        <h6 class=\"town\">"+town+"</h6>\n" +
            "                                        <br/>\n" +
            "                                        <p>"+a+"</p>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"addressSub\">\n" +
            "                                        <a href=\"\"><img src=\"../static/image/checkout/delete.png\">删除</a>\n" +
            "                                        <a href=\"\"><img src=\"../static/image/checkout/edit.png\">编辑</a>\n" +
            "                                    </div>\n" +
            "                                </div>\n" +
            "                            </li>");
        //清空表单
        $('form')[0].reset();
    });

    $("#addressList li").not("#addressEmpty").click(function () {
        $("#addressList li").css("border","1px solid #e0e0e0");
        $(this).css("border","1px solid rgb(240,90,45)");
    });
});