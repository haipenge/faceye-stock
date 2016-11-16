package com.faceye.component.stock.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算工具
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2015年2月20日
 */
public class StockPlan {
	private Logger logger = LoggerFactory.getLogger(StockPlan.class);
	// 上涨比率
	private static Double[] INCREACE_RATES = new Double[] { 0.5, 1.0, 0.25, 0.125, 0.0625, 0.33, 0.66, 2D };
	// 下跌比率
	private static Double[] DECREACE_RATES = new Double[] { 0.5, 0D, 0.25, 0.125, 0.0625, 0.33, 0.66, 0D };
	// 极高、低折价比率
	private static Double[] HALF_DECREACE_RATES = new Double[] { 0.5, 0.75 };
	// 列宽
	private static Integer COLUMN_WIDTH = 15;

	private static Integer SPACE_COUNT = 2;

	/**
	 * 比率计算(涨）
	 * @todo
	 * @param point,点位
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月20日
	 */
	public List<PointRateComputeResult> pointIncreaceCompute(Double point) {
		List<PointRateComputeResult> result = new ArrayList<PointRateComputeResult>();
		for (Double rate : INCREACE_RATES) {
			PointRateComputeResult pointComputeResult = null;
			pointComputeResult = new PointRateComputeResult();
			Double computeRate = 1 + rate;
			pointComputeResult.setRate("" + (this.formatNumber(rate * 100)) + "%");
			Double computePoint = point * computeRate;
			pointComputeResult.setPoint(this.formatNumber(computePoint).toString());
			result.add(pointComputeResult);
		}
		return result;
	}

	/**
	 * 比率计算（跌)
	 * @todo
	 * @param point
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月20日
	 */
	public List<PointRateComputeResult> pointDecreaceCompute(Double point) {
		List<PointRateComputeResult> res = new ArrayList<PointRateComputeResult>();
		for (Double rate : DECREACE_RATES) {
			PointRateComputeResult pointComputeResult = new PointRateComputeResult();
			if (rate.compareTo(0D) == 0) {
				pointComputeResult.setPoint("--");
				pointComputeResult.setRate("--");
			} else {
				Double computeRate = 1D - rate;
				Double computeResult = computeRate * point;
				String p = this.formatNumber(computeResult);
				pointComputeResult.setPoint(p);
				pointComputeResult.setRate("-" + (rate * 100) + "%");
			}
			res.add(pointComputeResult);
		}

		return res;
	}

	/**
	 * 计算极限最低价与极限最高价间50%，75%点位
	 * @todo
	 * @param lowPoint
	 * @param highPoint
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月20日
	 */
	public List<PointRateComputeResult> pointHalfDecreaceCompute(Double lowPoint, Double highPoint) {

		List<PointRateComputeResult> res = new ArrayList<PointRateComputeResult>();
		for (Double rate : HALF_DECREACE_RATES) {
			Double computePoint = highPoint - rate * (highPoint - lowPoint);
			PointRateComputeResult pointComputeResult = new PointRateComputeResult();
			pointComputeResult.setRate("[H]-" + (this.formatNumber(rate * 100)) + "%");
			pointComputeResult.setPoint("" + this.formatNumber(computePoint));
			res.add(pointComputeResult);
		}
		return res;
	}

	/**
	 * 
	 * @todo
	 * @param currentPoint,当前点位
	 * @param lowPoint,极限最低价
	 * @param highPoint,极限最高位
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月20日
	 */
	public List<PointRateComputeResult> pointCompute(Double currentPoint, Double lowPoint, Double highPoint) {
		List<PointRateComputeResult> res = new ArrayList<PointRateComputeResult>();
		// 计算上涨点数矩阵
		List<PointRateComputeResult> increacePointComputeResults = this.pointIncreaceCompute(currentPoint);
		// 计算下跌点数矩阵
		List<PointRateComputeResult> decreacePointComputeResults = this.pointDecreaceCompute(currentPoint);
		// 计算极商极低折半点数
		List<PointRateComputeResult> haftDecreacePointComputeResults = this.pointHalfDecreaceCompute(lowPoint, highPoint);
		for (int i = 0; i < INCREACE_RATES.length; i++) {
			PointRateComputeResult pointComputeResult = new PointRateComputeResult();
			String point = increacePointComputeResults.get(i).getPoint() + "/" + decreacePointComputeResults.get(i).getPoint();
			pointComputeResult.setPoint(point);
			res.add(pointComputeResult);
		}
		if (CollectionUtils.isNotEmpty(haftDecreacePointComputeResults)) {
			for (int i = 0; i < haftDecreacePointComputeResults.size(); i++) {
				PointRateComputeResult pointComputeResult = new PointRateComputeResult();
				pointComputeResult.setPoint(haftDecreacePointComputeResults.get(i).getPoint());
				res.add(pointComputeResult);
			}
		}
		return res;
	}

	/**
	 * 格式化数字
	 * 
	 * @param number
	 * @return
	 * @throws Exception
	 */
	private String formatNumber(Double number) {
		return this.formatNumber(number, "########.0");
	}

	private String formatNumber(Double number, String format) {
		if (StringUtils.isEmpty(format)) {
			format = "############.00";
		}
		DecimalFormat df = new DecimalFormat(format);
		String res = "";
		res = df.format(number);
		return res;
	}

	/**
	 * 打印头信息
	 * @todo
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月20日
	 */
	public String printHeader() {
		StringBuilder sb = new StringBuilder();
		String columnName = "当前点位";
		// sb.append(this.repeatBlankPoint(COLUMN_WIDTH - columnName.length()));
		sb.append(columnName);
		sb.append(this.repeatBlankPoint(SPACE_COUNT));
		for (int i = 0; i < INCREACE_RATES.length; i++) {
			columnName = (INCREACE_RATES[i] * 100) + "%";
			columnName += '/';
			columnName += "-";
			columnName += (DECREACE_RATES[i] * 100) + "%";
			sb.append(this.repeatBlankPoint(COLUMN_WIDTH - columnName.length()));
			sb.append(columnName);
			sb.append(this.repeatBlankPoint(SPACE_COUNT));
		}
		for (int i = 0; i < HALF_DECREACE_RATES.length; i++) {
			columnName = "-";
			columnName += (HALF_DECREACE_RATES[i] * 100) + "%";
			sb.append(this.repeatBlankPoint(COLUMN_WIDTH - columnName.length()));
			sb.append(columnName);
			sb.append(this.repeatBlankPoint(SPACE_COUNT));
		}
		sb.append("\n");
		return sb.toString();
	}

	public String printPointRateComputeResults(Double currentPoint, List<PointRateComputeResult> results) {

		StringBuilder sb = new StringBuilder();
		// sb.append(this.repeatBlankPoint(COLUMN_WIDTH - currentPoint.toString().length()));
		sb.append(currentPoint);
		sb.append(this.repeatBlankPoint(SPACE_COUNT));
		for (int i = 0; i < results.size(); i++) {
			PointRateComputeResult pointComputeResult = results.get(i);
			String point = pointComputeResult.getPoint();
			sb.append(this.repeatBlankPoint(COLUMN_WIDTH - point.length()));
			sb.append(point);
			sb.append(this.repeatBlankPoint(SPACE_COUNT));
		}
		sb.append("\n");
		return sb.toString();
	}

	public void compute() {
		StringBuilder sb = new StringBuilder("\n");
		sb.append(this.printHeader());
		// 编码，将要计算的点位集，Double[current,low,hight],length=3
		Map<String, List<Double[]>> points = new LinkedHashMap<String, List<Double[]>>();
		// 大盘
		// List<Double[]> sh = new ArrayList<Double[]>();
		// // 2015.02.17
		// //dapanData.add(new Double[] { 3258.03, 1991.08, 3406.79 });
		// //2005.6.6
		// sh.add(new Double[]{998.23,998.23,1783.01});
		// //2005.07.19
		// sh.add(new Double[]{1014.35,998.23,1783.01});
		// //2005.9.20
		// sh.add(new Double[]{1123.56,998.23,1123.56});
		// //2005.12.06
		// sh.add(new Double[]{1087.79,998.23,1783.01});
		// //2006.3.1
		// sh.add(new Double[]{1308.02,1067.41,1308.02});
		// //2006.4.4
		// sh.add(new Double[]{1330.10,1067.41,1330.10});
		// //2006.5.16 创新高后步入调整
		// sh.add(new Double[]{1678.60,1067.41,1678.60});
		// //2006.7.11
		// sh.add(new Double[]{1750.60,1067.41,1750.60});
		// //2007.1.23
		// sh.add(new Double[]{2970.69,1067.41,2970.69});
		// //2007.7.30
		// sh.add(new Double[]{4450.60,1067.41,4450.60});
		// //2007.10.17
		// sh.add(new Double[]{6124.04,1067.41,6124.04});
		// //2008.10.29
		// sh.add(new Double[]{1664.60,1664.60,6124.04});
		// //2009.7.29
		// sh.add(new Double[]{3478.60,1664.41,3478.6});
		// //2012.12.04
		// sh.add(new Double[]{1949.46,1949.46,3478.6});
		// //2013.6.25
		// sh.add(new Double[]{1849.65,1849.46,32343478.6});
		// //2014.3.12.
		// sh.add(new Double[]{1974.38,1974.38,3406.79});
		// //2015.1.23
		// sh.add(new Double[]{3406.79,1974.38,3406.79});
		// points.put("000001-上证", sh);
		// //招商轮船
		// List<Double[]> zs = new ArrayList<Double[]>();
		// zs.add(new Double[]{2.18,1.88,3.23});
		// zs.add(new Double[]{7.6,1.88,7.6});
		// points.put("601872-招商轮船", zs);
		// //中信海直
		// List<Double[]> zx = new ArrayList<Double[]>();
		// //2014.4.28
		// zx.add(new Double[]{7.57,5.82,16.2});
		// //2014.8.13
		// zx.add(new Double[]{9.9,5.82,9.9});
		// //2014.9.4
		// zx.add(new Double[]{10.5,5.82,16.2});
		// //2014.10.14
		// zx.add(new Double[]{13.23,5.82,13.23});
		// zx.add(new Double[]{16.2,5.82,16.2});
		// points.put("000099-中信海直", zx);
		//
		// /**
		// * 中泰华学
		// */
		// List<Double[]> zt=new ArrayList<Double[]>();
		// zt.add(new Double[]{4.83,4.83,4.83});
		// zt.add(new Double[]{8.46,4.83,8.46});
		// zt.add(new Double[]{9.14,4.83,9.14});
		// points.put("中泰化学", zt);
		//
		// List<Double[]>shjc=new ArrayList<Double[]>();
		// shjc.add(new Double[]{10.09,10.09,10.09});
		// shjc.add(new Double[]{13.13,10.09,13.13});
		// shjc.add(new Double[]{10.63,10.63,13.13});
		// shjc.add(new Double[]{17.1,10.63,17.11});
		// shjc.add(new Double[]{12.86,10.63,17.11});
		// shjc.add(new Double[]{11.71,10.63,17.11});
		// points.put("上海机场", shjc);
		// List<Double[]> cqlq=new ArrayList<Double[]>();
		// cqlq.add(new Double[]{6.89,3.41,6.89});
		// cqlq.add(new Double[]{6.97,3.41,7.25});
		// points.put("重庆路桥", cqlq);
		// //岷江水电
		// List<Double[]> mjsd =new ArrayList<Double[]>();
		// mjsd.add(new Double[]{7.05,3.55,7.3});
		// points.put("岷江水电", mjsd);
		// 隆平高科
		List<Double[]> lpgk = new ArrayList<Double[]>();
		lpgk.add(new Double[] { 37.36, 10.4, 37.36 });
		lpgk.add(new Double[] { 37.36, 0.4, 37.36 });
		points.put("隆平高科", lpgk);
		// 中信海直
		List<Double[]> zxhz = new ArrayList<Double[]>();
		zxhz.add(new Double[] { 26.82, 7.41, 26.82 });
		zxhz.add(new Double[] { 26.82, 1.67, 26.82 });
		points.put("中信海直", zxhz);
		//中泰化学
		List<Double[]> zthx=new ArrayList<Double[]>();
		zthx.add(new Double[]{17.70,4.78,17.70});
		zthx.add(new Double[]{17.70,2.53,17.70});
		points.put("中泰化学",zthx);
		//北方稀土
		List<Double[]> bfxt=new ArrayList<Double[]>();
		bfxt.add(new Double[]{31.12,12.34,31.12});
		bfxt.add(new Double[]{31.12,-0.24,31.12});
		points.put("北方稀土", bfxt);
		//盐田港
		List<Double[]> ytg=new ArrayList<Double[]>();
		ytg.add(new Double[]{13.79,4.77,13.78});
		ytg.add(new Double[]{13.79,-0.25,13.78});
		points.put("盐田港", ytg);

		Iterator<String> it = points.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();

			List<Double[]> datas = points.get(key);

			for (Double[] data : datas) {
				sb.append(key + "---极限最低价:" + data[1] + ",---极限最高价:" + data[2] + "\n");
				List<PointRateComputeResult> pointComputeResults = this.pointCompute(data[0], data[1], data[2]);
				sb.append(this.printPointRateComputeResults(data[0], pointComputeResults));
				// sb.append("\n");
			}
			sb.append("\n\n");
			if (it.hasNext()) {
				sb.append(this.printHeader());
			}
		}
		logger.debug(sb.toString());
	}

	/**
	 * 重复空白的次数
	 * @todo
	 * @param count
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2015年2月20日
	 */
	private String repeatBlankPoint(int count) {
		String res = "";
		for (int i = 0; i < count; i++) {
			res += " ";
		}
		return res;
	}

	/**
	 * 点位计算结果对像
	 * @author @haipenge 
	 * haipenge@gmail.com
	*  Create Date:2015年2月20日
	 */
	class PointRateComputeResult {
		public PointRateComputeResult() {

		}

		private String rate;
		private String point;

		public String getRate() {
			return rate;
		}

		public void setRate(String rate) {
			this.rate = rate;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

	}

	public static void main(String[] args) {
		StockPlan plan = new StockPlan();
		plan.compute();
	}

}
