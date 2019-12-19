$(document).ready(function(){
	initSwitch();
	initDataGrid();
	initClick();
});
//初始化按钮开关
var initSwitch = function(){
    $("#studentSwitch").bootstrapSwitch({
        onText:"开放",
        offText:"关闭",
        onColor:"primary",
        offColor:"default",
        size:"small",
        onInit:function(){
            $.ajax({
                url:"/stu/stuManager/getSwitch.do",
                type:"POST",
                data:{
                    name:"studentSwitch"
                },
                success:function (result) {
                    if(result.data==1){
                        $("#studentSwitch").bootstrapSwitch('toggleState');
                    }
                }
            });
        },
        onSwitchChange:function(event,state){
            changeSwitchState(state);
        }
    });
};
//设置switch状态
var changeSwitchState = function(state){
    $.ajax({
        url:"/stu/stuManager/setSwitch.do",
        type:"POST",
        data:{
            name:"studentSwitch",
            state:state
        },
        success:function (result) {
        }
    });
};
//初始化datagrid
var initDataGrid = function(){
	$("#content").datagrid({
		url:"/stu/stuManager/getAllStu.do",
		width:'auto',   //表格宽度
		height:'500px',
		columns:[[
			{field:'id',title:'编号',width:'auto',hidden:'true'},
			{field:'name',title:'姓名',width:'50',align : 'center'},
			{field:'idcard',title:'身份证',width:'140',align : 'center'},
			{field:'sex',title:'性别',width:'50',align : 'center'},
			{field:'phone',title:'联系电话',width:'100',align : 'center'},
			{field:'qq',title:'QQ',width:'100',align : 'center'},
			{field:'email',title:'邮箱',width:'120',align : 'center'},
			{field:'address',title:'联系地址',width:'200',align : 'center'},
			{field:'opt',title:'操作',width:'200',align:'center',
				formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="showUpdateBox(\''+row.id+'\')" name="update" class="easyui-linkbutton" ></a>';
                str += '<a href="javascript:;" onclick="del(\''+row.id+'\')" name="del" class="easyui-linkbutton" ></a>';
                str += '<a href="javascript:;" onclick="dorm(\''+row.id+'\')" name="dorm" class="easyui-linkbutton" ></a>';
                return str; }
			}
		]],
        onLoadSuccess:function(data){
            $("a[name='update']").linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});
            $("a[name='del']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            $("a[name='dorm']").linkbutton({text:'住宿',plain:true,iconCls:'icon-man'});
        },
        border:false,
		pagination:true,
		pageList:[18,25,30,40,50],
		pageSize:18,
		striped:true,
		nowrap:true,
		autoRowHeight:true,
		checkOnSelect:true,
		fitColumns:true,
		loadMsg:'怕是等一下哦',
		toolbar:"#toolBar",
        queryParams:{
            keywords:""
		}
	});
};
//绑定点击事件
var initClick = function(){
	//搜索按钮
	$("#searchBarBtn").click(function(){
		var keywords = $("#searchBarText").val();
        $("#content").datagrid("load",{keywords:keywords});
	});
	//添加按钮
	$("#add-btn").click(function(){
        $("#dd").dialog({
            title:"添加学生",
			width:400,
			height:'auto'
        });
	});
    $("#add-btnn").click(function(){
        $("#ddd").dialog({
            title:"添加登陆信息",
            width:400,
            height:'auto'
        });
    });
	//添加学生
	$("#subAddBtn").click(function(){
	    var name = $("#add-name").val();
	    var idcard = $("#add-idcard").val();
	    var sex = $("input[name='addSex']:checked").val();
        if(name=='' || name.length<2 || name.length>10)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"请填写正确格式的名字！长度>=2 <=10"
            });
            return
        }

        $.ajax({
            url:"/stu/stuManager/addsstudent.do",
            type:"POST",
            data:{
                name:name,
                idcard:idcard,
                sex:sex
            },

            success:function(data){
            	if(data.code){
					$.messager.confirm("提示",data.msg,function(r){
						if(r){
                            $("#dd").dialog('close');
                            var name = $("#add-name").val("");
                            var idcard = $("#add-idcard").val("");
                            $("#add-radio1").attr("checked","checked");
                            $("#content").datagrid("load");
						}else{
                            $("#dd").dialog('close');
                            $("#content").datagrid("load");
						}
					});
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                        	//DO NOTING
                        }
                    });
				}
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });
    });
// 登陆
    $("#subAddBtnn").click(function(){
        var username = $("#add-username").val();
        var password = $("#add-password").val();
        var id = $("#add-id").val();
        $.ajax({
            url:"/stu/stuManager/addstudent.do",
            type:"POST",
            data:{
                username:username,
                password:password,
                id:id
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#ddd").dialog('close');
                            var username = $("#add-username").val("");
                            var password = $("#add-password").val("");
                            var id = $("#add-id").val("");
                            $("#content").datagrid("load");
                        }else{
                            $("#ddd").dialog('close');
                            $("#content").datagrid("load");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });
    });

    //修改学生信息
    $("#subUpdateBtn").click(function(){
        var id = $("#update-hiddenID").val();
        var name = $("#update-name").val();
        var idcard = $("#update-idcard").val();
        var sex = $("input[name='updateSex']:checked").val();
        var phone = $("#update-phone").val();
        var qq = $("#update-qq").val();
        var email = $("#update-email").val();
        var address = $("#update-address").val();

        if(name=='' || name.length<2 || name.length>10)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"请填写正确格式的名字！"
            });
            return
        }

        if(phone.length!=11)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"电话号码长度错误！"
            });
            return
        }

        var p = /[a-z A-Z]/i;

        if(p.test(phone)==true)
        {
            $.messager.alert({
                title:"提示",
                icon:"error",
                msg:"电话号码包含非法字符！"
            });
            return
        }

        $.ajax({
            url:"/stu/stuManager/updateStudent.do",
            type:"POST",
            data:{
                id:id,
                name:name,
                idcard:idcard,
                sex:sex,
                phone:phone,
                qq:qq,
                email:email,
                address:address
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateBox").dialog("close");
                            $("#content").datagrid("load");
                        }else{
                            $("#updateBox").dialog("close");
                            $("#content").datagrid("load");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"修改失败！"
                });
            }
        });

    });

    //修改住宿信息
    $("#subDormUpdateBtn").click(function(){
        var id = $("#update-hiddenID").val();
        var zone_id = $("#dormZoneCombox").combobox("getValue");
        var building = $("#update-building").val();
        var room = $("#update-room").val();

        if(zone_id=="请选择宿舍园区")  //如果没有选择园区
        {
            alert("必须选择园区!");
            return;
        }

        $.ajax({
            url:"/stu/dormitory/updateStuDormitoryInfo.do",
            type:"POST",
            data:{
                stuId:id,
                zone_id:zone_id,
                building:building,
                room:room
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#updateDormBox").dialog("close");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"修改失败！"
                });
            }
        });

    });

    //删除住宿信息
    $("#subDormDelBtn").click(function(){

        var id = $("#update-hiddenID").val();
        $.ajax({
            url:"/stu/dormitory/delStuDormitoryInfo.do",
            type:"POST",
            data:{
                stuId:id,
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#updateDormBox").dialog("close");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"删除失败！"
                });
            }
        });

    });

    //添加住宿信息
    $("#subDormAddBtn").click(function(){
        var id = $("#update-hiddenID").val();
        var zone_id = $("#dormZoneCombox").combobox("getValue");
        var building = $("#update-building").val();
        var room = $("#update-room").val();

        if(zone_id=="请选择宿舍园区")  //如果没有选择园区
        {
            alert("必须选择园区!");
            return;
        }

        $.ajax({
            url:"/stu/dormitory/addStuDormitoryInfo.do",
            type:"POST",
            data:{
                stuId:id,
                zone_id:zone_id,
                building:building,
                room:room
            },
            success:function(data){
                if(data.code){
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            $("#updateDormBox").dialog("close");
                        }else{
                            $("#updateDormBox").dialog("close");
                        }
                    });
                }else{
                    $.messager.confirm("提示",data.msg,function(r){
                        if(r){
                            //DO NOTING
                        }
                    });
                }
            },
            error:function(e){
                $.messager.alert({
                    title:"提示",
                    icon:"error",
                    msg:"添加失败！"
                });
            }
        });

    });
};
//显示修改学生信息对话框
var showUpdateBox = function(id){
	$.ajax({
		url:"/stu/stuManager/getStudent.do",
        type:"POST",
        data:{
            id:id
        },
		success:function(data){
			if(data.code){
				$("#update-hiddenID").val(data.data.id);
                $("#update-name").val(data.data.name);
                $("#update-idcard").val(data.data.idcard);
				$(":radio[name='updateSex'][value='" + data.data.sex + "']").prop("checked", "checked");
                $("#update-phone").val(data.data.phone);
                $("#update-qq").val(data.data.qq);
                $("#update-email").val(data.data.email);
                $("#update-address").val(data.data.address);
                $("#updateBox").dialog({
                    title:"修改信息",
                    width:400,
                    height:460
                });
			}else{
                $.messager.alert("提示","信息加载失败");
			}
		},
		error:function(){
			$.messager.alert("提示","信息加载失败");
		}
	});

};

//删除学生信息
var del = function(id){
	$.messager.confirm("确认信息","您确定要删除这条学生信息吗？",function(r){
		if(r){
			$.ajax({
				url:"/stu/stuManager/delStudent.do",
				type:"POST",
				data:{
					id:id
				},
				success:function(data){
					if(data.code){
                        $.messager.confirm("提示",data.msg,function(r){
                            if(r){
                                $("#content").datagrid("load");
                            }else{
                                $("#content").datagrid("load");
                            }
                        });
					}else{
                        $.messager.confirm("提示",data.msg,function(r){
                            if(r){
                                //DO NOTING
                            }
                        });
					}
				},
				error:function(){
                    $.messager.alert({
                        title:"提示",
                        icon:"error",
                        msg:"删除失败！"
                    });
				}
			});
		}
	});
};

//住宿信息弹出框
var dorm = function(studentId){
    //住宿信息弹出框
    $("#update-hiddenID").val(studentId);

    $("#updateDormBox").dialog({
        title:"住宿信息修改",
        width:400,
        height:400
    })
    var selectedStuZoneId=loadStuDormMsg(studentId);
    $("#dormZoneCombox").combobox({
        loader:function(param,success,error){
            $.ajax({
                url:"/stu/dormitory/getdormZoneCombox.do",
                type:"POST",
                data:{
                },
                success:success,
                error:error
            })
        },
        valueField:"id",
        textField:"zone_name",
        value:selectedStuZoneId,//默认值
        width:220,
        onLoadSuccess:function(){

        },
        onBeforeLoad:function() {

        }
    });
};
//通过id查找学生的住宿信息
var loadStuDormMsg = function (studentId) {

    $('#subDormAddBtn').attr('disabled',"true");
    $('#subDormUpdateBtn').attr('disabled',"true");
    $('#subDormDelBtn').attr('disabled',"true");
    var zone_id=undefined;
    $.ajax({
        async: false,
        url:"/stu/dormitory/getStuDormitoryInfo.do",
        type:"POST",
        data:{
            stuId:studentId},
        success:function(result){
            if(result.data!=undefined) {
                $("#update-building").val(result.data.building);
                $("#update-room").val(result.data.room);
                $('#subDormUpdateBtn').removeAttr("disabled");
                $('#subDormDelBtn').removeAttr("disabled");
                zone_id=result.data.zone_id;
            }
            else
            {
                $("#update-building").val("没有入住");
                $("#update-room").val("没有入住");
                $('#subDormAddBtn').removeAttr("disabled");
                zone_id=undefined;
            }
        }
    });
    return zone_id;
};