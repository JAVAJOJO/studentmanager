$(document).ready(function(){
    initMsg();
    initClick();
});
var initMsg = function(){
    var stuId = $("#userId").val();
    $.ajax({
        url:"/stu/student/getStuMsg.do",
        type:"POST",
        data:{
            stuId:stuId
        },
        success:function(result){
            $("#idcard").val(result.data.idcard);
            $("input[name='sex']").each(function(){
                if($(this).val()==result.data.sex){
                    $(this).attr("checked","checked");
                }
            });
            $("#phone").val(result.data.phone);
            $("#qq").val(result.data.qq);
            $("#email").val(result.data.email);
            $("#address").val(result.data.address);
        }
    });
};

var initClick = function(){
    $("#subUpdateBtn").click(function(){
        var stuId = $("#userId").val();
        var idcard = $("#idcard").val();
        var sex = $("input[name='sex']:checked").val();
        var phone = $("#phone").val();
        var qq =  $("#qq").val();
        var email = $("#email").val();
        var address = $("#address").val();
        $.ajax({
            url:"/stu/student/updateMsg.do",
            type:"POST",
            data:{
                stuId:stuId,
                idcard:idcard,
                sex:sex,
                phone:phone,
                qq:qq,
                email:email,
                address:address
            },
            success:function(result){
                $.messager.confirm("提示信息",result.msg,function(r){
                    if(r){
                       window.location.reload();
                    }else{
                        window.location.reload();
                    }
                });
            }
        });
    });
};
