layui.use(['form', 'layer', 'jquery'], function() {
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.jquery;

    //验证规则
    form.verify({
        username: function(value) {
            if(value.length == 0) {
                return '请输入用户名';
            }
        },
        password: function(value) {
            if(value.length == 0) {
                return '请输入密码';
            }
        },
    });

    $("#register").click(function(){
        location.href = "/register";
    });

    //监听提交
    form.on('submit(login)', function(data) {
        // form.submit();
        data.field.password = encrypted(data.field.password);
        $.ajax({
            url: "/login",
            data: data.field,
            type: "POST",
            success: function(data){
                if (data.msg=="登录成功") {
                    if (getCookie("user") != null)
                        delCookie("user");
                    setCookie("user", JSON.stringify(data.content.user), 1);
                    location.href = "/admin";
                }
                else
                    layer.alert("用户名或密码错误");
            },
            error:function(data){
                layer.alert("登录失败", { title: "提示" });
                console.log(data);
            }
        });
        return false;
    });
});