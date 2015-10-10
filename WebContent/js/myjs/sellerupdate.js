/**
 * create by zhanghao 2015年9月10日19:45:03
 */

$(document).ready(
		function() {
			var $el = $('.dialog');
			$el.hDialog();
			// 遮罩不可关闭
			$('.demo6').hDialog({
				resetForm : false,
				width : 600,
				height : 450,
				modalHide : false,
				modalBg : 'rgba(0,0,0,0.5)',
				closeBg : 'rgba(0,0,0,0.2)',
			});
			$('#file_upload').uploadify({
				'debug' : false,// true显示调试Form窗口
				'swf' : '../uploadify/uploadify.swf',// 上传按钮的图片，默认是这个flash文件
				'uploader' : '/NightFair/ShopImageUpload',// 上传所处理的服务器
				'cancelImg' : '../uploadfiy/uploadify-cancel.png',// 取消图片
				'method' : 'post',
				'folder' : 'UploadFile/shopimage',// 上传后，所保存文件的路径
				'queueID' : 'fileQueue',// 上传显示进度条的那个div
				'buttonText' : '请选择文件',
				'auto' : false,
				// 'multi' : true,
				'file_types_description' : '图片文件',
				'file_types' : '*.jpg;*.png;*jpeg;*.gif',// 上传类型，格式如：’*.doc;*.pdf;*.rar’
				// 。
				'onError' : function(errorType, errObj) {
					alert('上传失败: ' + "文件类型不支持");
				},
				'onUploadSuccess' : function(file) {// 当上传成功后，弹出文件的提示
					alert('文件上传成功 ');
				}
			});
			$('.submitBtn').click(function() {
				$.tooltip('提交成功，1-2工作日内审核回复结果,该窗口2秒后自动关闭', 2000, true);
				setTimeout(function() {
					$el.hDialog('close', {
						box : '#HBox'
					});
				}, 2000);
			});
			$.ajax({
				url : "/NightFair/SellerUpdateInfo",
				data : {
					"action" : "get"
				},
				type : "post",
				success : function(data) {
					var dataObj = JSON.parse(data);
					if (dataObj.state == 200) {
						var name = dataObj.data.seller_name;
						var phone = dataObj.data.phone;
						var classify_id = dataObj.data.classify_id;
						var latitude = dataObj.data.latitude;
						var longitude = dataObj.data.longitude;
						var province = dataObj.data.province;
						var city = dataObj.data.city;
						var arer = dataObj.data.arer;
						var street = dataObj.data.street;
						$('[name="name"]').val(name);
						$('[name="phone"]').val(phone);
						$('[name="classify"]').val(classify_id);
						$('[name="longitude"]').val(longitude);
						$('[name="latitude"]').val(latitude);
						$('[name="txtProvince"]').html("");
						new PCAS("txtProvince", "txtCity", "txtArea", province,
								city, arer);
						$('[name="street"]').val(street);
					} else {
						new PCAS("txtProvince", "txtCity", "txtArea", "江苏",
								"苏州", "吴中区");
						/* alert(dataObj.state); */
					}
				},
				error : function(error) {
					console.log(error);
					alert('网络异常，稍后再试!');
				}
			});
		});
/*-- 获取分类 ----*/
$(document).ready(
		function() {
			$.ajax({
				url : "/NightFair/apis/classify/get",
				data : {},
				type : "post",
				success : function(data) {
					var dataObj = JSON.parse(data);
					if (dataObj.state == 0) {
						for (var i = 0; i < dataObj.data.length; i++) {
							$('#classify').append(
									"<option value=" + dataObj.data[i].id
											+ " >"
											+ dataObj.data[i].classify_name
											+ "</option>");
						}

					} else {
						alert(dataObj.state);
					}
				},
				error : function(error) {
					console.log(error);
					alert('网络异常!');
				}
			});
		});
function fullscreen() {
	$('#img').addClass("fulls");
	var title = $('#img').attr("title");
	if (title == "全屏") {
		$('#img').attr("title", "退出全屏");
		$('#HBox').css({
			"position" : "fixed",
			"top" : 0,
			"left" : 0,
			"margin" : 0,
			"z-index" : 100000,
			"width" : "100%",
			"height" : "100%",
			" display" : "block",
		});
	} else if (title == "退出全屏") {
		$('#img').attr("title", "全屏");
		$('#HBox').css({
			"display" : "block",
			"position" : "fixed",
			"top" : "50%",
			"left" : "50%",
			"margin" : "-225px 0px 0px -300px",
			"z-index" : 100000,
			"width" : "600px",
			"height" : "450px",
			" display" : "block",
			"background-color" : "rgb(255, 255, 255)",
		});
	}
}
function _add_btn() {
	var longitude = $('[name="longitude"]').val();
	var latitude = $('[name="latitude"]').val();
	$('[name="lngX"]').val(longitude);
	$('[name="latY"]').val(latitude);

	var map = new AMap.Map('mapContainer', {
		zoom : 10,
		resizeEnable : true,
	});

	/*AMap.plugin([ 'AMap.ToolBar', 'AMap.Scale' ], function() {
		var toolBar = new AMap.ToolBar();
		var scale = new AMap.Scale();
		map.addControl(toolBar);
		map.addControl(scale);
	});
	// 地图类型切换
	map.plugin([ "AMap.MapType" ], function() {
		var type = new AMap.MapType({
			defaultType : 0,
			showRoad : true
		});

		map.addControl(type);
		$('.amap-maptypecontrol').css({
			"position" : "absolute",
			"top" : "40px",
			"right" : "12px",
			"z-index" : "304",
		});
	});*/

	if (document.getElementById("lngX").value != ""
			&& document.getElementById("latY").value != "") {
		map.setCenter([ longitude, latitude ]);
		var clickEventListener = map.on('click', function(e) {
			document.getElementById("lngX").value = e.lnglat.getLng();
			document.getElementById("latY").value = e.lnglat.getLat();
		});
	}else{
		var clickEventListener = map.on('click', function(e) {
			document.getElementById("lngX").value = e.lnglat.getLng();
			document.getElementById("latY").value = e.lnglat.getLat();
		});
		
	}

	/*-------------百度地图---------------------------*/
	/*
	 * var map = new BMap.Map("mapContainer",{enableHighResolution: true});
	 * map.centerAndZoom(new BMap.Point(120.747769,315.82188),12);
	 * map.enableScrollWheelZoom(true); var navigationControl = new
	 * BMap.NavigationControl({ // 靠左上角位置 anchor: BMAP_ANCHOR_TOP_LEFT, //
	 * LARGE类型 type: BMAP_NAVIGATION_CONTROL_LARGE, // 启用显示定位 enableGeolocation:
	 * true }); map.addControl(navigationControl);
	 * map.addEventListener("click",function(e){
	 * document.getElementById("lngX").value = e.point.lng;
	 * document.getElementById("latY").value = e.point.lat; });
	 * if(document.getElementById("lngX").value != "" &&
	 * document.getElementById("latY").value != ""){ map.clearOverlays(); var
	 * new_point = new BMap.Point( document.getElementById("lngX").value,
	 * document.getElementById("latY").value); var marker = new
	 * BMap.Marker(new_point); // 创建标注 map.addOverlay(marker); // 将标注添加到地图中
	 * map.panTo(new_point); }
	 */

}
function getInfo() {

	var longitude = $('[name="lngX"]').val();
	var latitude = $('[name="latY"]').val();
	/*
	 * var map = new AMap.Map('mapContainer', { center:
	 * ["+longitude+","+latitud+"], zoom: 10, resizeEnable: true
	 * //为地图注册click事件获取鼠标点击出的经纬度坐标
	 * 
	 * });
	 */
	$('[name="longitude"]').val(longitude);
	$('[name="latitude"]').val(latitude);

}
function add() {
	var name = $('[name="name"]').val();
	var phone = $('[name="phone"]').val();
	var classify = $('[name="classify"]').val();
	var longitude = $('[name="longitude"]').val();
	var latitude = $('[name="latitude"]').val();
	var province = $('[name="txtProvince"]').val();
	var city = $('[name="txtCity"]').val();
	var arer = $('[name="txtArea"]').val();
	var street = $('[name="street"]').val();

	$.ajax({
		url : "/NightFair/SellerUpdateInfo",
		data : {
			"name" : name,
			"phone" : phone,
			"classify" : classify,
			"longitude" : longitude,
			"latitude" : latitude,
			"province" : province,
			"city" : city,
			"arer" : arer,
			"street" : street,
			"action" : "update"
		},
		type : "post",
		success : function(data) {
			var dataObj = JSON.parse(data);
			if (dataObj.state == 200) {
				alert('修改成功!');
			}
		},
		error : function(error) {
			console.log(error);
			alert('网络异常，稍后再试!');
		}
	});
};

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
function onf1(x) {
	x.style.background = "yellow";
	document.getElementById("dp").innerHTML = "请填写店铺名称";

}
function onb1(dp) {
	dp.style.background = "white";
	document.getElementById("dp").innerHTML = "";
}

function onf2(x) {
	x.style.background = "yellow";
	document.getElementById("dh").innerHTML = "手机号码不低于11位";

}
function onb2(dp) {
	dp.style.background = "white";
	document.getElementById("dh").innerHTML = "";
}

function onf3(x) {
	x.style.background = "yellow";
	document.getElementById("jd").innerHTML = "请填入不低于三位的尾数";

}
function onb3(dp) {
	dp.style.background = "white";
	document.getElementById("jd").innerHTML = "";
}

function onf4(x) {
	x.style.background = "yellow";
	document.getElementById("wd").innerHTML = "请填入不低于三位的尾数";

}
function onb4(dp) {
	dp.style.background = "white";
	document.getElementById("wd").innerHTML = "";
}

function onf5(x) {
	x.style.background = "yellow";
	document.getElementById("ad").innerHTML = "此项为必填项";

}
function onb5(dp) {
	dp.style.background = "white";
	document.getElementById("ad").innerHTML = "";
}
function mOver1(obj) {
	document.getElementById("dq").innerHTML = "请选择个人所在省";
}

function mOut1(obj) {
	document.getElementById("dq").innerHTML = "";
}
function mOver2(obj) {

	document.getElementById("dq2").innerHTML = "请选择个人所在市";

}
function mOut2(obj) {
	document.getElementById("dq2").innerHTML = "";
}
function mOver3(obj) {
	document.getElementById("dq3").innerHTML = "请选择个人所在区";

}
function mOut3(obj) {
	document.getElementById("dq3").innerHTML = "";
}
