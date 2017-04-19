/**
 * Created by mr_young on 2017/4/19.
 */
$(function() {
    /*
     * 加载index.html页面检查cookie信息确定用户名和登录状态
     * cookie需包含两条选项：username, islogin
     */
    function updateLoginInfo() {
        // 读取cookie
        var cookie = getCookie();

        if ("name" in cookie) {
            if (cookie.name.length > 0) {
                if (JSON.parse(cookie.islogin)) {
                    $("a.btn .btn-default .log-in").text(cookie.name)
                        .attr("href", "#");
                    // $("a.login").text("注销");
                }
            }
        }
    }

    /*
     * 跳转到path指定的页面
     */
    function redirect(path) {
        window.location.pathname = "/crrs" + path;
    }

    /*
     * 根据用户名和密码请求server端验证登录
     * user_info- {name: xx, password: xx, rember_me: xx}
     */
    function requestLogin(user_info) {
        $.ajax({
            method: "POST",
            url: "/crrs/user_login.action",
            contentType: "application/json;charset='utf-8'",
            data: JSON.stringify(user_info)
        }).done(function (response_body) {
            // 登录成功则切换至index.html页面并显示在index.html页面显示用户信息
            if (response_body !== null) {
                // 登录成功增加登录状态的cookie信息
                document.cookie = "islogin=true";
                updateLoginInfo();
                // 登录失败则给出错误提示"用户名或密码错误..."
            } else {
                redirect("/index.html");
            }
        });
    }

    /*
     * 点击登录按钮处理：ajax请求发往server端进行登录处理，登录成功跳转到
     * index.html页面，失败进行错误提示
     */
    $("#btn-login").on("click", function() {
        // 获取登录信息
        var login_info = {};
        login_info.name = $(".form-horizontal input").eq(0).val();
        login_info.s_password = $(".form-horizontal input").eq(1).val();
        // login_info.rember_me = document.querySelector("input[name=rember-me").checked;

        // 发送ajax请求到server进行用户登录
        requestLogin(login_info);
    })

    $("#dropdownMenu1").hover(function () {
        $("div.dropdown").addClass("open");
    })
});