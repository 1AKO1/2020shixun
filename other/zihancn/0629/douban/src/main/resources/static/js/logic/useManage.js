layui.use(['jquery', 'form', 'table', 'layer'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;
    layer = layui.layer;
    table.render({
        elem: '#tblResult',
        page: true,
        limit: 6,
        cols: [[
            {field: 'uid', title: '用户名', align: 'center'},
            {field: 'nickName', title: '昵称', align: 'center'},
            {
                field: 'state', title: '用户状态', align: 'center',
                templet: function (rowData) {
                    switch (rowData.state) {
                        case 0:
                            return '<font color="blue"><i class="layui-icon layui-icon-about"></i>未激活</font>';
                        case 1:
                            return '<font color="green"><b>已激活</b></font>';
                        case 2:
                            return '<font color="red">已注销</font>';
                    }
                }
            },
            {
                field: 'id', title: '操作', align: 'center',
                templet: function (rowData) {
                    var btnReset = '<button class="layui-btn layui-btn-sm layui-btn-warm " lay-event="reset">' +
                        '<i class="layui-icon layui-icon-password>"</i>重置密码</button>';

                    var btninfo = '<button class="layui-btn layui-btn-sm layui-btn-normal " lay-event="info">' +
                        '<i class="layui-icon layui-icon-about>"</i>用户详情</button>';

                    var btndisable = '<button class="layui-btn layui-btn-sm layui-btn-normal " lay-event="info">' +
                        '<i class="layui-icon layui-icon-close>"</i>用户注销</button>';

                    return btnReset + btninfo + btndisable;
                }
            }


        ]],
        data: []
    });

    table.on('tool(tblResult)', function (obj) {

        if ('reset' === obj.event) {
            // alert('密码重置 -uid:'+obj.data.uid+", ID:"+obj.data.id);

            let index = layer.open({
                title: '警告',
                content: '您将重置当前用户' + obj.data.uid + '的密码，请确认</br> 注意，该操作不可逆',
                btn: ['确认', '取消'],
                btnAlign: 'c',
                yes: function () {
                    // alert('重置成功')
                    $.post(
                        '/logic/user/pwdreset',
                        {
                            'id': obj.data.id
                        },
                        function (data) {
                            let msg = '服务器错误，请联系管理员'
                            if (!data.error) {
                                layer.alert('重置成功');
                            }
                            layer.alert(msg);
                            layer.close(index);
                        }
                    )
                },
                btn2: function () {
                    layer.closeAll();
                }
            })

        }

        if ('info' === obj.event) {
            alert('用户详情 -uid:' + obj.data.uid + ", ID:" + obj.data.id);
        }
        if('disable' === obj.event) {
            alert('屏蔽用户  -  uid：' + obj.data.uid + ", ID：" + obj.data.id);
        }

    });


    $('#btnSearch').click(function (event) {
        table.reload('tblResult', {
            url: '/logic/user/search',
            method: 'post',
            where: {
                'uid': $('#uid').val(),
                'nickName': $('#nickName').val(),
                'state': $('#selState').val()
            },
            page: {
                curr:1
            }

        })
    });
});