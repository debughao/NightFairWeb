package com.nightfair.vo;

import java.io.Serializable;

public class Classify implements Serializable {
	/**
	 * @author Gem
	 * 
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = 1L;

	public Classify() {
		super();

	}

	@Override
	public String toString() {
		return "Classify [ classify_name=" + classify_name + ",id=" + id + "]";
	}

	public Classify(String id, String classify_name) {
		super();
		this.id = id;
		this.classify_name = classify_name;
	}

	private String id;
	private String classify_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassify_name() {
		return classify_name;
	}

	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}
}
