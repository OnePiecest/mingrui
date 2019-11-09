$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/users/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'adminUserId', index: 'adminUserId', width: 50, key: true, hidden: true},
            {label: '用户名称', name: 'loginUserName', index: 'loginUserName', width: 60},
            {label: '用户密码', name: 'loginPassword', index: 'loginPassword', width: 120},
            {label: '用户昵称', name: 'nickName', index: 'nickName', width: 60},
            {label: '创建时间', name: 'createdTime', index: 'createdTime', width: 120, formatter: timeFormat},
            {label: '修改时间', name: 'modifiedTime', index: 'modifiedTime', width: 120, formatter: timeFormat},
            {label: '用户状态', name: 'locked', index: 'locked', width: 120}
        ],
        height: 700,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    // function statusFormatter(cellvalue) {
    //     if (cellvalue == 0) {
    //         return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">待审核</button>";
    //     } else if (cellvalue == 1) {
    //         return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">已审核</button>";
    //     }
    // }

});


/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function userAdd() {
    reset();
    $('.modal-title').html('用户添加');
    $('#linkModal').modal('show');
}

function reset() {
    $("#loginUserName").val('');
    $("#loginPassword").val('');
    $("#nickName").val('');
    $('#edit-error-msg').css("display", "none");
    $("#linkType option:first").prop("selected", 'selected');
}

function userDelete() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
        if(flag) {
            $.ajax({
                type: "POST",
                url: "/admin/users/delete",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r.resultCode == 200) {
                        swal("删除成功", {
                            icon: "success",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        swal(r.message, {
                            icon: "error",
                        });
                    }
                }
            });
        }
    }
)
    ;
}


//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var adminUserId = $("#adminUserId").val();
    var loginUserName = $("#loginUserName").val();
    var loginPassword = $("#loginPassword").val();
    var nickName = $("#nickName").val();

    if (!validCN_ENString2_18(loginUserName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的名称！");
        return;
    }
    if (!validCN_ENString2_18(loginPassword)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的密码！");
        return;
    }
    if (!validCN_ENString2_18(nickName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的昵称！");
        return;
    }
    var params = $("#userForm").serialize();
    var url = '/admin/users/save';
    if (adminUserId != null && adminUserId > 0) {
        url = '/admin/users/update';
    }
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200 ) {
                $('#linkModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            } else {
                $('#linkModal').modal('hide');
                swal("保存失败", {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });

});

/*function userEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/user/list/" + id;
}*/


function timeFormat(time) {
    var d = new Date(time);

    var year = d.getFullYear();       //年
    var month = d.getMonth() + 1;     //月
    var day = d.getDate();            //日

    var hh = d.getHours();            //时
    var mm = d.getMinutes();          //分
    var ss = d.getSeconds();           //秒

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day + " ";

    if (hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";

    if (ss < 10) clock += '0';
    clock += ss;
    return (clock);
}