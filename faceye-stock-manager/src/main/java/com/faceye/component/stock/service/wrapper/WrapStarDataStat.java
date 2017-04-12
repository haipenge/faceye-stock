package com.faceye.component.stock.service.wrapper;

import org.springframework.data.domain.Page;

import com.faceye.component.stock.entity.StarDataStat;

/**
 * 包装对星标数据的分析
 * 
 * @author haipenge
 *
 */
public class WrapStarDataStat {

	public Page<StarDataStat> starDataStats = null;
	// 5日上张成功率
	private Double max5DayIncreaseSuccessRate = 0.0D;
	// 10日上张成功率
	private Double max10DayIncreaseSuccessRate = 0.0D;
	// 20日上张成功率
	private Double max20DayIncreaseSuccessRate = 0.0D;
	// 30日上张成功率
	private Double max30DayIncreaseSuccessRate = 0.0D;
	// 60日上张成功率
	private Double max60DayIncreaseSuccessRate = 0.0D;
	// 120日上张成功率
	private Double max120DayIncreaseSuccessRate = 0.0D;
	public Page<StarDataStat> getStarDataStats() {
		return starDataStats;
	}
	public void setStarDataStats(Page<StarDataStat> starDataStats) {
		this.starDataStats = starDataStats;
	}
	public Double getMax5DayIncreaseSuccessRate() {
		return max5DayIncreaseSuccessRate;
	}
	public void setMax5DayIncreaseSuccessRate(Double max5DayIncreaseSuccessRate) {
		this.max5DayIncreaseSuccessRate = max5DayIncreaseSuccessRate;
	}
	public Double getMax10DayIncreaseSuccessRate() {
		return max10DayIncreaseSuccessRate;
	}
	public void setMax10DayIncreaseSuccessRate(Double max10DayIncreaseSuccessRate) {
		this.max10DayIncreaseSuccessRate = max10DayIncreaseSuccessRate;
	}
	public Double getMax20DayIncreaseSuccessRate() {
		return max20DayIncreaseSuccessRate;
	}
	public void setMax20DayIncreaseSuccessRate(Double max20DayIncreaseSuccessRate) {
		this.max20DayIncreaseSuccessRate = max20DayIncreaseSuccessRate;
	}
	public Double getMax30DayIncreaseSuccessRate() {
		return max30DayIncreaseSuccessRate;
	}
	public void setMax30DayIncreaseSuccessRate(Double max30DayIncreaseSuccessRate) {
		this.max30DayIncreaseSuccessRate = max30DayIncreaseSuccessRate;
	}
	public Double getMax60DayIncreaseSuccessRate() {
		return max60DayIncreaseSuccessRate;
	}
	public void setMax60DayIncreaseSuccessRate(Double max60DayIncreaseSuccessRate) {
		this.max60DayIncreaseSuccessRate = max60DayIncreaseSuccessRate;
	}
	public Double getMax120DayIncreaseSuccessRate() {
		return max120DayIncreaseSuccessRate;
	}
	public void setMax120DayIncreaseSuccessRate(Double max120DayIncreaseSuccessRate) {
		this.max120DayIncreaseSuccessRate = max120DayIncreaseSuccessRate;
	}
	
	
}
