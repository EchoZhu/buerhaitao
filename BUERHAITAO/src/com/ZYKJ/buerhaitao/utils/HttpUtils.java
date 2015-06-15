package com.ZYKJ.buerhaitao.utils;

import java.io.File;
import java.io.FileNotFoundException;

import android.content.Context;
import android.util.Log;

import com.ZYKJ.buerhaitao.data.AppValue;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
/**
 * @author 作者 zhang
 * @version 创建时间：2015年1月6日 上午10:33:29 类说明 AsyncHttp 异步联网第三方库
 */
public class HttpUtils {
	public static final String base_url = "http://115.28.21.137/mobile/";
	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象
	static {
		client.setTimeout(5000); // 设置链接超时，如果不设置，默认为15s
		client.setMaxRetriesAndTimeout(3, 5000);
		// client.setEnableRedirects(true);

	}

	public static void initClient(Context c) {
		PersistentCookieStore myCookieStore = new PersistentCookieStore(c);
		client.setCookieStore(myCookieStore);
	}

	public static AsyncHttpClient getClient() {

		return client;
	}

	/**
	 * 1登录
	 * 
	 * @param res
	 * @param loginname
	 * @param pwd
	 */

	public static void login(AsyncHttpResponseHandler res, String loginname,
			String pwd) {
		String url = null;
		url = base_url + "index.php?act=login";
		RequestParams requestParams  = new RequestParams();
		requestParams.put("mobile", loginname);
		requestParams.put("password", pwd);
		requestParams.put("client", AppValue.CLIENT);
		client.post(url,requestParams,res);

	}

	/**
	 * 2注册
	 * 
	 * @param res
	 * @param mobile
	 *            必选 用户名
	 * @param password
	 *            必选 密码
	 * @param verify_code
	 *            必选 验证码
	 * @param client
	 *            必选 客户端类型：android
	 */
	public static void regist(AsyncHttpResponseHandler res, String mobile ,
			String password , String verify_code) {
		String url = null;
		url = base_url + "index.php?act=login&op=register";
		RequestParams requestParams  = new RequestParams();
		requestParams.put("mobile", mobile);
		requestParams.put("password", password);
		requestParams.put("verify_code", verify_code);
		requestParams.put("client", AppValue.CLIENT);
		client.post(url,requestParams,res);
	}
	/**
	 * 注销
	 * @param res
	 * @param mobile
	 *            必选 手机号
	 * @param key 
	 *            必选 当前登录令牌
	 * @param client
	 *            必选 客户端类型：android
	 */
	public static void logout(AsyncHttpResponseHandler res, String mobile ,
			String key) {
		String url = null;
		url = base_url + "index.php?act=logout";
		RequestParams requestParams  = new RequestParams();
		requestParams.put("mobile", mobile);
		requestParams.put("key", key);
		requestParams.put("client", AppValue.CLIENT);
		client.post(url,requestParams,res);
	}

	/**
	 * 3获取首页轮播图
	 * 
	 * @param res
	 */
	public static void getScreenList(AsyncHttpResponseHandler res) {
		String url = base_url + "getSpecialList";
		client.get(url, res);
	}

	/**
	 * 4首页商品列表
	 * 
	 * @param res
	 */
	public static void getHomeGoods(AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象
	{

		String url = base_url + "getHomeGoods";
		client.get(url, res);
		Log.i("landousurl", url);
	}

	/**
	 * 5获取商品分类 parent_id默认为0
	 * 
	 * @param res
	 * @param parent_id
	 */
	public static void getGoodsClass(AsyncHttpResponseHandler res,
			String parent_id) {
		String url = base_url + "getGoodsClass&parent_id=" + parent_id;
		client.get(url, res);
	}

	/**
	 * 6获取商品列表
	 * 
	 * @param res_getGoodsList
	 * 
	 * @param gc_id
	 *            类别排序
	 * @param store_id
	 *            店铺
	 * @param search_text
	 *            搜索关键字
	 */
	public static void getGoodsList(AsyncHttpResponseHandler res_getGoodsList,
			String g_type) {
		String url = base_url + "getGoodsList" + g_type;
		Log.i("landousurl", url);
		client.get(url, res_getGoodsList);
	}
	/**
	 * 修改昵称
	 * @param res_getGoodsList
	 * @param user_name
	 * @param key
	 */
	public static void editname(AsyncHttpResponseHandler res,String user_name,String key) {
		String url = base_url + "index.php?act=member_index&op=editname";
		RequestParams params = new RequestParams();
		params.put("user_name", user_name);
		params.put("key", key);
		client.post(url, params, res);
	}
	

	/**
	 * 7获取商品详情
	 * 
	 * @param goods_id
	 * @param getGoodsDetail
	 */
	public static void getGoodsDetail(String goods_id,
			AsyncHttpResponseHandler getGoodsDetail) {
		String url = base_url + "getGoodsDetail&goods_id=" + goods_id;
		client.get(url, getGoodsDetail);
	}

	/**
	 * 8获取商品评价
	 * 
	 * @param res
	 * @param goods_id
	 */
	public static void getGoodsComments(AsyncHttpResponseHandler res,
			String goods_id) {
		String url = base_url + "getGoodsComments&goods_id=" + goods_id;
		client.get(url, res);
	}

	/**
	 * 9获取商店列表
	 * 
	 * @param res
	 */
	public static void getStoreList(AsyncHttpResponseHandler res,
			String search_text) {
		String url = null;
		if (search_text == null) {
			url = base_url + "getStoreList";
		} else {
			url = base_url + "getStoreList&search_text=" + search_text;
		}
		client.get(url, res);
	}

	/**
	 * 10获取店铺分类商品列表。。
	 * 
	 * @param res
	 * @param store_id
	 */
	public static void getStoreGoodsClass(AsyncHttpResponseHandler res,
			String store_id) {
		String url = base_url + "getStoreGoodsClass&store_id=" + store_id;
		client.get(url, res);

	}

	/**
	 * 11添加商品收藏
	 * 
	 * @param res
	 * @param goods_id
	 */
	public static void addFavoriteGoods(AsyncHttpResponseHandler res,
			String goods_id) {
		String url = base_url + "addFavoriteGoods&goods_id=" + goods_id;
		client.get(url, res);
	}

	/**
	 * 12取消商品收藏
	 * 
	 * @param res
	 * @param goods_id
	 */
	public static void delFavoriteGoods(AsyncHttpResponseHandler res,
			String goods_id) {
		String url = base_url + "delFavoriteGoods&goods_id=" + goods_id;
		client.get(url, res);
	}

	/**
	 * 13查询商品收藏
	 * 
	 * @param res
	 * @param page
	 * @param per_page
	 */
	public static void getFavoriteGoods(AsyncHttpResponseHandler res,
			String page, String per_page) {
		String url = base_url + "getFavoriteGoods&page=" + page + "&per_page="
				+ per_page;
		client.get(url, res);
	}

	/**
	 * 14添加商店收藏
	 * 
	 * @param res
	 * @param store_id
	 */
	public static void addFavoriteStore(AsyncHttpResponseHandler res,
			String store_id) {
		String url = base_url + "addFavoriteStore&store_id=" + store_id;
		client.get(url, res);
	}

	/**
	 * 15取消商店收藏
	 * 
	 * @param res
	 * @param store_id
	 */
	public static void delFavoriteStore(AsyncHttpResponseHandler res,
			String store_id) {
		String url = base_url + "delFavoriteStore&store_id=" + store_id;
		client.get(url, res);
	}

	/**
	 * 16查询商店收藏
	 * 
	 * @param res
	 * @param page
	 * @param per_page
	 */
	public static void getFavoriteStore(AsyncHttpResponseHandler res,
			String page, String per_page) {
		String url = base_url + "getFavoriteStore&page=" + page + "&per_page="
				+ per_page;
		client.get(url, res);
	}

	/**
	 * 获取地区列表
	 * 
	 * @param res
	 * @param parent_id
	 */
	public static void getArea(AsyncHttpResponseHandler res, String key,String area_id) {
		String url = base_url + "index.php?act=member_address&op=area_list"; 
		RequestParams params  = new RequestParams();
		params.put("key",key);
		params.put("area_id",area_id);
		client.post(url, params, res);
	}

	/**
	 * 18添加地址
	 * 
	 * @param res
	 * @param true_name
	 *            真实修改
	 * @param area_id
	 *            地区id
	 * @param address
	 *            详细地址
	 * @param mob_phone
	 *            手机
	 */
	public static void addAddress(AsyncHttpResponseHandler res,
			String true_name, String area_id, String address, String mob_phone) {
		String url = base_url + "addAddress&true_name=" + true_name
				+ "&area_id=" + area_id + "&address=" + address + "&mob_phone="
				+ mob_phone;
		client.get(url, res);
	}

	/**
	 * 19修改地址
	 * 
	 * @param res
	 * @param true_name
	 *            收货人
	 * @param address
	 *            详细地址
	 * @param mob_phone
	 *            手机
	 */
	public static void changeAddress(AsyncHttpResponseHandler res,
			String true_name, String area_id, String address, String mob_phone,
			String address_id) {
		String url = base_url + "changeAddress&true_name=" + true_name
				+ "&address=" + address + "&mob_phone=" + mob_phone
				+ "&area_id=" + area_id + "&address_id=" + address_id;
		client.get(url, res);
	}

	/**
	 * 20删除地址
	 * 
	 * @param res
	 * @param address_id
	 *            地址id
	 */
	public static void delAddress(AsyncHttpResponseHandler res,
			String address_id) {
		String url = base_url + "delAddress&address_id=" + address_id;
		Log.i("del-addr", url);
		client.get(url, res);
	}

	/**
	 * 21收货地址
	 * 
	 * @param res
	 */
	public static void getAddress(AsyncHttpResponseHandler res,String key) {
		String url = base_url + "index.php?act=member_address&op=address_list"+"&key="+key;
		client.get(url, res);
	}

	/**
	 * 22设置默认地址
	 * 
	 * @param res
	 * @param address_id
	 */
	public static void setDefaultAddress(AsyncHttpResponseHandler res,
			String address_id) {
		String url = base_url + "setDefaultAddress&address_id=" + address_id;
		Log.i("landousurl", url);
		client.get(url, res);
	}

	/**
	 * 23添加购物车
	 * 
	 * @param res
	 * @param goods_id
	 *            商品id，必选
	 * @param count
	 *            商品数量，默认为1
	 */
	public static void addCart(AsyncHttpResponseHandler res, String goods_id,
			String count) {
		count = count == null ? "1" : count;
		String url = base_url + "addCart&goods_id=" + goods_id + "&count="
				+ count;
		client.get(url, res);

	}

	/**
	 * 24修改购物车
	 * 
	 * @param res
	 * @param cart_id
	 *            购物车id，必选
	 * @param count
	 *            商品数量，必选
	 */
	public static void updateCart(AsyncHttpResponseHandler res, String cart_id,
			int count) {
		String url = base_url + "updateCart&cart_id=" + cart_id + "&count="
				+ count;
		client.get(url, res);

	}

	/**
	 * 25删除购物车
	 * 
	 * @param res
	 * @param cart_id
	 *            购物车id，必选，可以多个，例如：1,2,3
	 */
	public static void delCart(AsyncHttpResponseHandler res, String cart_id) {
		String url = base_url + "delCart&cart_id=" + cart_id;
		client.get(url, res);
	}

	/**
	 * 26查询购物车
	 * 
	 * @param res
	 */
	public static void getCartList(AsyncHttpResponseHandler res, String cart_id) {
		String url = base_url + "getCartList&cart_id=" + cart_id;
		client.get(url, res);
		Log.i("landouurl", url);
	}

	/**
	 * 27订单确认
	 * 
	 * @param res
	 * @param cart_id
	 */
	public static void getOrderConfirm(AsyncHttpResponseHandler res,
			String cart_id) {
		String url = base_url + "getOrderConfirm&cart_id=" + cart_id;
		client.get(url, res);
		Log.i("landousjsonurl", url);
	}

	/**
	 * 27立即购买
	 * 
	 * @param res
	 * @param goods_id
	 */
	public static void BuyNow(AsyncHttpResponseHandler res, String goods_id,
			int count) {
		String url = base_url + "getOrderConfirm&goods_id=" + goods_id
				+ "&count=" + count;
		client.get(url, res);
		Log.i("landousjsonurl", url);
	}

	/**
	 * 28 提交订单 所有都是必选
	 * 
	 * @param res
	 * @param ship_method
	 * @param pay_method
	 * @param address_id
	 * @param cart_id
	 */
	public static void addOrder(AsyncHttpResponseHandler res,
			String ship_method, String pay_method, String address_id,
			String cart_id) {
		String url = base_url + "addOrder&ship_method=" + ship_method
				+ "&pay_method=" + pay_method + "&address_id=" + address_id
				+ "&cart_id=" + cart_id;
		client.get(url, res);
		Log.i("landousjsonurl", url);
	}

	/**
	 * 28 立即付款提交订单
	 * 
	 * @param res
	 * @param ship_method
	 * @param pay_method
	 * @param address_id
	 * @param goods_id
	 */
	public static void addOrderNow(AsyncHttpResponseHandler res,
			String ship_method, String pay_method, String address_id,
			String goods_id, String count) {
		String url = base_url + "addOrder&ship_method=" + ship_method
				+ "&pay_method=" + pay_method + "&address_id=" + address_id
				+ "&goods_id=" + goods_id + "&count=" + count;
		client.get(url, res);
	}

	/**
	 * 29查询订单列表
	 * 
	 * @param res
	 * @param order_state
	 *            订单状态：0(已取消)10(默认):未付款;20:已付款;30:已发货;40:已收货;
	 */
	public static void getOrderList(AsyncHttpResponseHandler res,
			String order_state) {
		String url = base_url + "getOrderList&order_state=" + order_state;
		client.get(url, res);

	}

	/**
	 * 30 更新移动支付减免
	 * 
	 * @param res
	 * @param pay_sn
	 * @param pay_method
	 * @param discount
	 */
	public static void updateMobileDiscount(AsyncHttpResponseHandler res,
			String pay_sn, String pay_method, String discount) {
		String url = base_url + "updateMobileDiscount&pay_sn=" + pay_sn
				+ "&pay_method=" + pay_method + "&discount=" + discount;
		client.get(url, res);
		Log.i("landousjsonurl", url);
	}

	/**
	 * 31取消订单
	 * 
	 * @param res
	 * @param order_id
	 * @param extend_msg
	 */
	public static void cancelOrder(AsyncHttpResponseHandler res,
			String order_id, String extend_msg) {
		String url = base_url + "cancelOrder&order_id=" + order_id
				+ "&extend_msg=" + extend_msg;
		client.get(url, res);
		Log.i("landousurl", url);
	}

	/**
	 * 32确认收货
	 * 
	 * @param res
	 * @param order_id
	 */
	public static void receiveGoods(AsyncHttpResponseHandler res,
			String order_id) {
		String url = base_url + "receiveGoods&order_id=" + order_id;
		client.get(url, res);
	}

	/**
	 * 33 查看物流
	 * 
	 * @param res
	 * @param order_id
	 */
	public static void getExpress(AsyncHttpResponseHandler res, String order_id) {
		String url = base_url + "getExpress&order_id=" + order_id;
		client.get(url, res);
	}

	/**
	 * 34 退款
	 * 
	 * @param res
	 * @param order_id
	 * @param rec_id
	 * @param refund_type
	 * @param refund_amount
	 * @param goods_num
	 * @param extend_msg
	 */
	public static void refund(AsyncHttpResponseHandler res, String order_id,
			String rec_id, String refund_type, String refund_amount,
			String goods_num, String extend_msg) {
		String url = base_url + "refund&order_id=" + order_id + "&rec_id="
				+ rec_id + "&refund_type=" + refund_type + "&refund_amount="
				+ refund_amount + "&goods_num=" + goods_num + "&extend_msg="
				+ extend_msg;
		client.get(url, res);
		Log.i("landousurl", url);
	}

	/**
	 * 35 订单评价
	 * 
	 * @param res
	 * @param anony
	 */
	public static void orderEvaluation(AsyncHttpResponseHandler res,
			String order_id, String anony, String other) {
		String url = base_url + "orderEvaluation&anony=" + anony + "&order_id="
				+ order_id + other;
		client.get(url, res);
		Log.i("landousurl", url);
	}

	/**
	 * 36 获取专题
	 * 
	 * @param res
	 * @param special_id
	 */
	public static void getSpecial(AsyncHttpResponseHandler res,
			String special_id) {
		String url = base_url + "getSpecial&special_id=" + special_id;
		Log.i("get-special_url", url);
		client.get(url, res);
	}

	/**
	 * 积分商城
	 * 
	 * @param res
	 * @param key
	 * 
	 */
	public static void pointsMall(AsyncHttpResponseHandler res, String key) {
		String url = base_url + "index.php?act=member_points&op=goods_list"+"&key="+key;
		client.get(url, res);
	}

	/**
	 * 38 提交积分订单
	 * 
	 * @param res
	 * @param pgoods_id
	 * @param count
	 * @param ship_method
	 * @param address_id
	 */
	public static void addPointsOrder(AsyncHttpResponseHandler res,
			String pgoods_id, String count, String ship_method,
			String address_id) {
		String url = base_url + "addPointsOrder&pgoods_id=" + pgoods_id
				+ "&count=" + count + "&ship_method=" + ship_method
				+ "&address_id=" + address_id;
		Log.i("landousjson", url);
		client.get(url, res);
	}

	/**
	 * 39积分订单付款完成
	 * 
	 * @param res
	 * @param point_orderid
	 */
	public static void payPointsOrder(AsyncHttpResponseHandler res,
			String point_orderid) {
		String url = base_url + "payPointsOrder&point_orderid=" + point_orderid;
		client.get(url, res);

	}

	/**
	 * 40 查询积分订单列表
	 * 
	 * @param res
	 * @param page
	 * @param per_page
	 */
	public static void getPointsOrder(AsyncHttpResponseHandler res,
			String page, String per_page) {
		String url = base_url + "getPointsOrder&page=" + page + "&per_page="
				+ per_page;
		client.get(url, res);
	}

	/**
	 * 41 查询积分变更列表
	 * @param res
	 * @param page
	 * @param per_page
	 */
	public static void getPointsLog(AsyncHttpResponseHandler res,String key) {
		String url = base_url + "index.php?act=member_points&op=points_log"+"&key="+key;
		client.get(url, res);
//		String url = base_url + "index.php?act=member_points&op=points_log";
//		RequestParams requestParams =new RequestParams();
//		requestParams.put("key", key);
//		client.post(url, requestParams, res);
		
	}

	/**
	 * 42 上传头像保存
	 * 
	 * @param res
	 * @param member_avatar
	 */
	public static void saveAvatar(AsyncHttpResponseHandler res,String avatar,String key ) {
		String url = base_url + "index.php?act=member_index&op=avatar_save";
		RequestParams params =new RequestParams();
		params.put("avatar", avatar);
		params.put("key", key);
		client.post(url, params, res);
	}

	/**
	 * 43 分享获得积分
	 * 
	 * @param res
	 */
	public static void addSharePoints(AsyncHttpResponseHandler res) {
		String url = base_url + "addSharePoints";
		client.get(url, res);
	}

	/**
	 * 44 签到
	 * 
	 * @param res
	 */
	public static void chackIn(AsyncHttpResponseHandler res,String key) {
		String url = base_url + "index.php?act=member_index&op=signin";
		RequestParams requestParams = new RequestParams();
		requestParams.put("key", key);
		client.post(url, requestParams, res);
	}
	/**
	 * 减钻石
	 * 
	 * @param res
	 */
	public static void subtractCheckPoints(AsyncHttpResponseHandler res, double goods_price) {
		String url = base_url + "subtractCheckPoints&goods_price="+goods_price;
		client.get(url, res);
	}
	/**
	 * 钻石充值接口
	 * 
	 * @param res
	 */
	public static void addGemsPoints(AsyncHttpResponseHandler res, int gemsNumbers) {
		String url = base_url + "subtractCheckPoints&gemsNumbers="+gemsNumbers;
		client.get(url, res);
	}

	/**
	 * 45 积分订单确认收货
	 * 
	 * @param res
	 * @param point_orderid
	 */
	public static void receivePointsOrder(AsyncHttpResponseHandler res,
			String point_orderid) {
		String url = base_url + "receivePointsOrder&point_orderid="
				+ point_orderid;
		client.get(url, res);

	}

	/**
	 * 46.获取支付宝秘钥
	 * 
	 * @param res
	 */
	public static void getAlipay(AsyncHttpResponseHandler res) {
		String url = base_url + "getAlipay";
		client.get(url, res);

	}

	/**
	 * 47.获取版本
	 * 
	 * @param res
	 */
	public static void getAppVersion(AsyncHttpResponseHandler res) {
		String url = base_url + "getAppVersion&platform=" + "android";
		client.get(url, res);
	}

	/**
	 * 48 忘记密码
	 * 
	 * @param res
	 * @param mobile 手机号
	 * @param verify_code 验证码
	 * @param client 客户端类型(android ios )
	 * 
	 */
	public static void resetPassword(AsyncHttpResponseHandler res,String mobile, String password,String verify_code) {
		String url = base_url + "index.php?act=login&op=restpasswd";
		RequestParams requestParams  = new RequestParams();
		requestParams.put("mobile", mobile);
		requestParams.put("password", password);
		requestParams.put("verify_code", verify_code);
		requestParams.put("client", AppValue.CLIENT);
		client.post(url, requestParams, res);
	}

	/**
	 * 49. 获取订单详情
	 * 
	 * @param res
	 * @param order_id
	 */
	public static void getOrderDetail(AsyncHttpResponseHandler res,
			String order_id) {
		String url = base_url + "getOrderDetail&order_id=" + order_id;
		client.get(url, res);

	}

	/**
	 * 50 获取手机减免额度
	 * 
	 * @param res
	 */
	public static void getDiscountSetting(AsyncHttpResponseHandler res) {
		String url = base_url + "getDiscountSetting";
		client.get(url, res);
	}

	/**
	 * 51.取消积分订单
	 * 
	 * @param res
	 * @param point_orderid
	 */
	public static void cancelPointsOrder(AsyncHttpResponseHandler res,
			String point_orderid) {
		String url = base_url + "cancelPointsOrder&point_orderid="
				+ point_orderid;
		client.get(url, res);
	}

	/**
	 * 52 订单完成支付
	 * 
	 * @param res
	 * @param pay_sn
	 */
	public static void payOrder(AsyncHttpResponseHandler res, String pay_sn) {
		String url = base_url + "payOrder&pay_sn=" + pay_sn;
		client.get(url, res);
	}

	public static void update(AsyncHttpResponseHandler res, String key,String name ) {
		RequestParams params = new RequestParams();
		try {
			params.put("key", key);
			params.put("name", name);
			params.put("FILES", new File(name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Upload a File
		String url = base_url + "index.php?act=member_index&op=avatar_upload";
		client.post(url, params, res);
	}
}