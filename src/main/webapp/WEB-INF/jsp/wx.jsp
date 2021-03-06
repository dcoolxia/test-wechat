<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
	window.onload=function(){
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: 'wx71a94214415a75c2', // 必填，公众号的唯一标识
		    timestamp: '1505133758', // 必填，生成签名的时间戳
		    nonceStr: '1265275094', // 必填，生成签名的随机串
		    signature: '1cf71c093dc938172b48fa96c4e5e35dc80d59e2',// 必填，签名，见附录1
		    jsApiList: ['hideMenuItems','hideAllNonBaseMenuItem','showMenuItems'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		
		wx.ready(function(){
			alert('成功');
			hideAllMenuItems();
		});
		
		wx.error(function(res){
			alert('失败');
		});
		
	}
	
	/* 批量隐藏功能按钮接口
	 * 发送给朋友: "menuItem:share:appMessage"
	 * 分享到朋友圈: "menuItem:share:timeline"
	 * 分享到QQ: "menuItem:share:qq"
	 * 分享到Weibo: "menuItem:share:weiboApp"
	 * 收藏: "menuItem:favorite"
	 * 分享到FB: "menuItem:share:facebook"
	 * 分享到 QQ 空间/menuItem:share:QZone
	 */
	function hideMenuItems() {
		wx.hideMenuItems({
		    menuList: ["menuItem:share:timeline"] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
		});
	}
	
	//隐藏所有非基础按钮接口
	function hideAllMenuItems() {
	    wx.hideAllNonBaseMenuItem();
	}
	
	/* 批量显示功能按钮接口
     * 发送给朋友: "menuItem:share:appMessage"
     * 分享到朋友圈: "menuItem:share:timeline"
     * 分享到QQ: "menuItem:share:qq"
     * 分享到Weibo: "menuItem:share:weiboApp"
     * 收藏: "menuItem:favorite"
     * 分享到FB: "menuItem:share:facebook"
     * 分享到 QQ 空间/menuItem:share:QZone
     */
    function showMenuItems() {
        wx.showMenuItems({
    	    menuList: ["menuItem:share:appMessage","menuItem:share:timeline"] // 要显示的菜单项，所有menu项见附录3
        });
    }
</script>
</head>
<body>
    <h1>测试微信js</h1>
    <input type="button" value="隐藏指定" onclick="hideMenuItems()"/>
    <input type="button" value="隐藏所有" onclick="hideAllMenuItems()"/>
    <input type="button" value="显示指定" onclick="showMenuItems()"/>
</body>
</html>