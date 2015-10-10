package com.nightfair.vo;

/**
 * 
 * @ClassName: Comment
 * @Description: TODO(存放评论的具体信息)
 * @author debughao
 * @date 2015年9月16日
 */
public class Comment {
	private int id;
	private double grade;
	private String comment;
	private String time;
	private int seller_id;
	private int user_id;
	private String nickname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 
	 * 创建一个新的实例 Comment.
	 *
	 * @param id
	 * @param grade
	 * @param comment
	 * @param time
	 * @param seller_id
	 * @param user_id
	 * @param nickname
	 */
	public Comment(int id, double grade, String comment, String time, int seller_id, int user_id, String nickname) {
		super();
		this.id = id;
		this.grade = grade;
		this.comment = comment;
		this.time = time;
		this.seller_id = seller_id;
		this.user_id = user_id;
		this.nickname = nickname;
	}

	public Comment() {

	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", grade=" + grade + ", comment=" + comment + ", time=" + time + ", seller_id="
				+ seller_id + ", user_id=" + user_id + ", nickname=" + nickname + "]";
	}

}
