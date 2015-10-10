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
        	"url": "/NightFair/Seller/GetGoods", 
        	"data": {"action": "select"},
        	"type": "POST"},
        columns: [{"data": "id"},           
            {"data": "good_name"},
            {"data": "real_price"},
            {"data": "seller_counts"},
            {"data": "recommend_num"},   
            {
                "data": null, "defaultContent": "",
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<a href='javascript:void(0);' " +
                        "onclick='_editFun(this," + oData.id + ")'>编辑</a>&nbsp;&nbsp;")
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
	 var good_name= $('#good_name').val(); 
	 var introduction=$('#introduction').val();
	 var real_price=$('#real_price').val();
	 var seller_counts= $('#seller_counts').val();
	 var NumReg1 = /^\d{1,11}$/; 
	 var NumReg2 = /^([1-9][0-9]{0,9}(\.[0-9]{1,2})?|0\.[1-9][0-9]|0\.0[1-9])$/;
		var $good_name = $('#good_name');
		var $real_price = $('#real_price');
		var $seller_counts = $('#seller_counts');
		var $introduction = $('#introduction');
		if ($good_name.val() == '') {
			$.tooltip('亲!美食名称还没填呢...');
			$good_name.focus();
			return;
		} else if ($real_price.val() == '') {
			$.tooltip('亲!美食价格还没填呢...');
			$real_price.focus();
			return;
		}  else if (!NumReg2.test($real_price.val())) {
			$.tooltip('亲!价格请输入1-9位可含2位小数的数...');
			$real_price.focus();
			return;
		}  else if ($seller_counts.val() == '') {
			$.tooltip('亲!美食描述还没填呢...');
			$seller_counts();
			return;
		} else if (!NumReg1.test($seller_counts.val())) {
				$.tooltip('亲!销量请输入1-9位的数字...');
				$seller_counts.focus();
				return;
		}   else if ($introduction.val() == '') {
			$.tooltip('亲!美食描述还没填呢,没有请填0.');
			$introduction.focus();
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
            url: "/NightFair/Seller/GetGoods",
            data: {
                "good_name": good_name,
                "real_price": real_price,
                "seller_counts": seller_counts,
                "introduction": introduction,
                "action": "add"
            },
            type: "post",
            success: function (data) { 
                var dataObj = JSON.parse(data);
                if (dataObj.status == 200) {
                    oTable.row.add({
                        "id": dataObj.goods.id,
                        "good_name": dataObj.goods.good_name,
                        "real_price": dataObj.goods.real_price,
                        "seller_counts": dataObj.goods.seller_counts,
                        "recommend_num": dataObj.goods.recommend_num,
                        "null": ""
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
            url: "/NightFair/Seller/GetGoods",
            data: {
                "id":id,
                "good_name": good_name,
                "real_price": real_price,
                "seller_counts": seller_counts,
                "introduction": introduction,
                "action": "update"
            },
            type: "post",
            success: function (data) {
                var dataObj = JSON.parse(data);
                if (dataObj.status == 200) {
                    var tds = $('.edit').parents("tr").children();
                    for(var i=1;i<tds.length-1;i++){
                        if(i==1){
                            $(tds[i]).html(good_name);
                        }
                        else if(i==2){
                            $(tds[i]).html(real_price);
                        }
                        else if(i==3){
                            $(tds[i]).html(seller_counts);
                        }                         
                    }
              /*      alert("修改成功！");*/

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
            url: "/NightFair/Seller/GetGoods",
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
function _editFun(a,e) {
	$('.demo6').click();	
    $.ajax({
        url: "/NightFair/Seller/GetGoods",
        data: {
            "id": e,
            "action": "edit"
        },
        type: "post",
        success: function (data) {
            var dataObj = JSON.parse(data);
            if (dataObj.status == 200) {
                clearEdit();
                $(a).addClass("edit");
                $('#btn').val('修改');
                $('#id').val(dataObj.goods.id);
                $('#good_name').val(dataObj.goods.good_name);
                $('#real_price').val(dataObj.goods.real_price);
                $('#seller_counts').val(dataObj.goods.seller_counts);
                $('#introduction').val(dataObj.goods.introduction);
            } else {
                alert("错误代码："+dataObj.status);
            }
        }, error: function (error) {
            console.log(error);
            alert('网络异常!');
        }
    });
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