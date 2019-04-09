package com.faceye.component.stock.entity;

public enum CashFlowTypeNames {

	Type0("未知",0),TYPE1("妖精", 1),TYPE2("老母鸡", 2),Type3("蛮牛",3),Type4("奶牛",4),Type5("骗吃骗喝",5),Type6("混吃等列",6),Type7("赌徒",7),Type8("大出血",8);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private CashFlowTypeNames(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (CashFlowTypeNames c : CashFlowTypeNames.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
