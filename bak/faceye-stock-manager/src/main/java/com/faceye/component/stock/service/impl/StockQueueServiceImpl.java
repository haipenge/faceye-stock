package com.faceye.component.stock.service.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.faceye.feature.service.impl.MultiQueueServiceImpl;

@Service
public class StockQueueServiceImpl<Stock> extends MultiQueueServiceImpl<Stock> {
    private Queue queue=null;
	@Override
	public Queue<Stock> getQueue() {
		if(queue==null){
			queue=new ConcurrentLinkedQueue<Stock>();
		}
		return queue;
	}

}
