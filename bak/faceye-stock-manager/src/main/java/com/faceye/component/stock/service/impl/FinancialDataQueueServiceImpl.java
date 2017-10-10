package com.faceye.component.stock.service.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.faceye.component.stock.entity.Stock;
import com.faceye.feature.service.impl.QueueServiceImpl;

@Service("financialDataQueueService")
public class FinancialDataQueueServiceImpl extends QueueServiceImpl<Stock> {

	private Queue queue = null;
	@Override
	public Queue<Stock> getQueue() {
		if (queue == null) {
			if (queue == null) {
				queue = new ConcurrentLinkedQueue<Stock>();
			}
		}
		return queue;
	}

}
