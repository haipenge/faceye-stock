package com.faceye.component.stock.entity;

/**
 * 现金流量表(4)
 */
public class CashFlowSheet {
	/**
	 * <p>@Description:经营活动产生的现金流量 (2)</p>
	 */
	private Ele2 ele2 = new Ele2();

	public void setEle2(Ele2 ele2) {
		this.ele2 = ele2;
	}

	public Ele2 getEle2() {
		return this.ele2;
	}

	/**
	 * 投资活动产生的现金流量 (3)
	 */
	private Ele3 ele3 = new Ele3();

	public void setEle3(Ele3 ele3) {
		this.ele3 = ele3;
	}

	public Ele3 getEle3() {
		return this.ele3;
	}

	/**
	 * 筹资活动产生的现金流量 (4)
	 */
	private Ele4 ele4 = new Ele4();

	public void setEle4(Ele4 ele4) {
		this.ele4 = ele4;
	}

	public Ele4 getEle4() {
		return this.ele4;
	}

	/**
	 * 附注 (5)
	 */
	private Ele5 ele5 = new Ele5();

	public void setEle5(Ele5 ele5) {
		this.ele5 = ele5;
	}

	public Ele5 getEle5() {
		return this.ele5;
	}
}
