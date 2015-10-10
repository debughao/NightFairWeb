/**
 * Created by ZhangHao on 2015/8/29 0029.
 */

var oTable;
var $el = $('.dialog');


$(document).ready(function () {
    oTable = $('#example').DataTable({
    	 "language": {
             "url": "../zh-CN.txt"
         },
        ordering: true,
        ajax: {
        	"url": "/NightFair/Seller/GetCoupon", 
        	"data": {"action": "select"},
        	"type": "POST"},
        columns: [{"data": "id"},           
            {"data": "original_price"},
            {"data": "current_price"},
            {"data": "description"},
            {"data": "public_time"},
            {"data": "update_time"}, 
            {"data": "seller_counts"}, 
            {
                "data": null, "defaultContent": "",
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a href='javascript:void(0);' " +
                        "onclick='_editFun(this,\"" + oData.id + "\",\"" + oData.public_time + "\",\"" + oData.original_price + "\",\"" + oData.current_price + "\",\"" + oData.description + "\")'>编辑</a>&nbsp;&nbsp;")
                        .append("<a href='javascript:void(0);' onclick='_deleteFun(this," + oData.id + ")'>删除</a>");                            			
                    methods.remove("#HOverlay,#HCloseBtn,#HTitle");
        			 }
            }
        ]
    });

});
function _add_btn() {
    $('#btn').val('增加');
}
function btnOnclick() {
	 var id=$('#id').val();
	 var original_price= $('#original_price').val(); 
	 var current_price=$('#current_price').val();
	 var description=$('#description').val();
	 var NumReg1 = /^\d{1,11}$/; 
	
		var $original_price = $('#original_price');
		var $current_price = $('#current_price');
		var $description = $('#description');
		if ($original_price.val() == '') {
			$.tooltip('亲!美食价格还没填呢...');
			$original_price.focus();
			return;
		}  else if (!NumReg1.test($original_price.val())) {
			$.tooltip('亲!价格请输入1-11位数...');
			$original_price.focus();
			return;
		}  else if ($current_price.val() == '') {
			$.tooltip('亲!美食描述还没填呢...');
			$current_price();
			return;
		} else if (!NumReg1.test($current_price.val())) {
				$.tooltip('亲!销量请输入1-11字...');
				$current_price.focus();
				return;
		}   else if ($description.val() == '') {
			$.tooltip('亲!美食描述还没填呢,没有请填0.');
			$description.focus();
			return;
		}   else { 
			$.tooltip('提交成功，2秒后自动关闭', 2000, true);
			setTimeout(function() {
				$el.hDialog('close', {
					box : '#HBox'
				});
			}, 2000);
		}

    if ($('#btn').val() == "增加"){
        $.ajax({
            url: "/NightFair/Seller/GetCoupon",
            data: {
                "original_price": original_price,
                "current_price": current_price,
                "description": description,         
                "action": "add"
            },
            type: "post",
            success: function (data) { 
                var dataObj = JSON.parse(data);
                if (dataObj.status == 200) {
                    oTable.row.add({
                        "id": dataObj.coupon.id,
                        "original_price": dataObj.coupon.original_price,
                        "current_price": dataObj.coupon.current_price,
                        "description": dataObj.coupon.description,
                        "public_time": dataObj.coupon.public_time,
                        "update_time": dataObj.coupon.update_time,
                        "seller_counts": dataObj.coupon.seller_counts,
                    }).draw();
                } else {
                    alert(dataObj.status);
                }
            }, error: function (error) {
                console.log(error);
                /*alert('请求失败请稍后!');*/
            }
        });
    }else if($("#btn").val() == "修改"){
        $.ajax({
            url: "/NightFair/Seller/GetCoupon",
            data: {
                "id":id,
                "original_price": original_price,
                "current_price": current_price,
                "description": description,     
                "action": "update"
            },
            type: "post",
            success: function (data) {
                var dataObj = JSON.parse(data);
                if (dataObj.status == 200) {
                    var tds = $('.edit').parents("tr").children();
                    for(var i=1;i<tds.length-1;i++){
                        if(i==1){
                            $(tds[i]).html(original_price);
                        }
                        else if(i==2){
                            $(tds[i]).html(current_price);
                        }
                        else if(i==3){
                            $(tds[i]).html(description);
                        }  
                        else if(i==5){
                            $(tds[i]).html(dataObj.coupon.update_time);
                        } 
                    }
                } else {
                    alert("错误代码："+dataObj.status);
                }
            }, error: function (error) {
                console.log(error);
                alert('网络异常!');
            }
        });

    }


}
function _deleteFun(e, id) {

    if (confirm("确定要删除这条记录？")) {
        $.ajax({
            url: "/NightFair/Seller/GetCoupon",
            data: {"id": id, "action": "delete"},
            type: "post",
            success: function (data) {
               /* alert(data);*/
                var dataObj = JSON.parse(data);
                if (dataObj.status == 200) {
                    var table = $('#example').DataTable();
                    table.row($(e).parents('tr')).remove().draw();
                } else {
                    alert(dataObj.status);
                }
            }, error: function (error) {
                console.log(error);
                alert('网络异常!');
            }
        });
    }
}
function _editFun(a,e,public_time,original_price,current_price,description) {
	$('.demo6').click();
	clearEdit();
    $(a).addClass("edit");
    $('#btn').val('修改');
    $('#id').val(e);
    $('#public_time').val(public_time);
    $('#original_price').val(original_price);
    $('#current_price').val(current_price);
    $('#description').val(description);   
}

function clearEdit(){
   var edits =$('.edit');
    for(var i=0;i<edits.length;i++){
        $(edits[i]).removeClass("edit");
    }
}

function logout()
{
    $.ajax({
        url: "/NightFair/Register",
        data: {"validate":"logout"},
        type: "post",
        success: function (data) {
            var dataObj = JSON.parse(data);
            if (dataObj.status ==200) {
            	location.replace("/NightFair/login.html");
            } else {
                alert("错误代码："+dataObj.status);
            }
        }, error: function (error) {
            console.log(error);
            alert('网络异常!');
        }
    });
}