package com.faceye.component.stock.service.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.faceye.component.stock.entity.Forecast;
import com.faceye.component.stock.entity.ForecastIndex;
import com.faceye.component.stock.entity.Valuation;

/**
 * 对机构估值进行包装 目标：对数据按时间、机构进行分组
 * 
 * @author haipenge
 *
 */
public class WrapForecast {

	private ForecastIndex forecastIndex = null;
	private List<Forecast> forecasts = new ArrayList<Forecast>(0);

	private Valuation valuation = null;

	public ForecastIndex getForecastIndex() {
		return forecastIndex;
	}

	public void setForecastIndex(ForecastIndex forecastIndex) {
		this.forecastIndex = forecastIndex;
	}

	public List<Forecast> getForecasts() {
		return forecasts;
	}

	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}

	public void addForecast(Forecast forecast) {
		this.forecasts.add(forecast);
	}

	public Valuation getValuation() {
		return valuation;
	}

	public void setValuation(Valuation valuation) {
		this.valuation = valuation;
	}

}
