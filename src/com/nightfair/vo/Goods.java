package com.nightfair.vo;

/**
 * 
 * Goods
 * 
 * 2015年9月11日 上午10:44:01
 * 
 * @version 1.0.0
 *
 */
public class Goods {
	private int id;
	private String good_name;
	private double real_price;
	private int seller_counts;
	private String introduction;
	private String img;
	private int seller_id;
	private int recommend_num;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGood_name() {
		return good_name;
	}

	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}

	public double getReal_price() {
		return real_price;
	}

	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}

	public int getSeller_counts() {
		return seller_counts;
	}

	public void setSeller_counts(int seller_counts) {
		this.seller_counts = seller_counts;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getRecommend_num() {
		return recommend_num;
	}

	public void setRecommend_num(int recommend_num) {
		this.recommend_num = recommend_num;
	}

	public Goods() {

	}

	/**
	 * 
	 * 创建一个新的实例 Goods. 用于接口使用
	 * 
	 * @param id
	 * @param good_name
	 * @param real_price
	 * @param total_counts
	 * @param introduction
	 * @param img
	 * @param seller_id
	 * @param recommend_num
	 */

	public Goods(int id, String good_name, double real_price, int seller_counts,
			String introduction, String img, int seller_id, int recommend_num) {
		super();
		this.id = id;
		this.good_name = good_name;
		this.real_price = real_price;
		this.seller_counts = seller_counts;
		this.introduction = introduction;
		this.img = img;
		this.seller_id = seller_id;
		this.recommend_num = recommend_num;
	}

	/**
	 * 
	 * 创建一个新的实例 Goods.
	 * 
	 * @param id
	 * @param good_name
	 * @param real_price
	 * @param seller_counts
	 * @param introduction
	 * 
	 */
	public Goods(int seller_id, String good_name, double real_price,
			int seller_counts, String introduction) {
		super();
		this.seller_id = seller_id;
		this.good_name = good_name;
		this.real_price = real_price;
		this.seller_counts = seller_counts;
		this.introduction = introduction;

	}

	/**
	 * 
	 * 创建一个新的实例 Goods.用于商户更改商品信息
	 * 
	 * @param id
	 * @param good_name
	 * @param real_price
	 * @param seller_counts
	 * @param introduction
	 * @param seller_id
	 */
	public Goods(int id, String good_name, double real_price, int seller_counts,
			String introduction, int seller_id) {
		super();
		this.id = id;
		this.good_name = good_name;
		this.real_price = real_price;
		this.seller_counts = seller_counts;
		this.introduction = introduction;
		this.seller_id = seller_id;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", good_name=" + good_name + ", real_price="
				+ real_price + ", seller_counts=" + seller_counts
				+ ", introduction=" + introduction + ", img=" + img
				+ ", seller_id=" + seller_id + "]";
	}

}
