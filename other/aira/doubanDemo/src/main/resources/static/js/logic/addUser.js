layui.use(['layer', 'form', 'jquery'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;

    form.on('submit(saveAndCommit)', function (data) {
        $.post(
            '/logic/user/add',
            {
                'uid': data.field.uid,
                'pwd': data.field.pwd,
                'nickName' : data.field.nickName
            },
            function (returnResult) {
                if (returnResult != null){
                    layer.alert("用户注册成功")
                }else {
                    layer.alert("用户已注册")
                }
            }
        )
    })
})