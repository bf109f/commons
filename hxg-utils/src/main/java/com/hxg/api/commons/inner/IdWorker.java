package com.hxg.api.commons.inner;

/**
 * IdWorker 单例防止id出现重复
 * 
 * @ClassName: IdWorker
 * @Description: TODO(雪花算法id生成器)
 * @author user
 * @date 2018年10月24日
 *
 */
public class IdWorker
{
	private final long twepoch = 1288834974657L;
	private final long workerIdBits = 5L;
	private final long datacenterIdBits = 5L;
	// 支持的最大机器id，结果是1023 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private final long sequenceBits = 12L;
	private final long workerIdShift = sequenceBits;
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;

	private static volatile IdWorker singleton;

	private IdWorker(long workerId, long datacenterId)
	{
		if (workerId > maxWorkerId || workerId < 0)
		{
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0)
		{
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	public synchronized long nextId()
	{
		long timestamp = timeGen();
		if (timestamp < lastTimestamp)
		{
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}
		if (lastTimestamp == timestamp)
		{
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0)
			{
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else
		{
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp)
	{
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp)
		{
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen()
	{
		return System.currentTimeMillis();
	}

	public static IdWorker getInstance()
	{
		if (singleton == null)
		{
			synchronized (IdWorker.class)
			{
				if (singleton == null)
				{
					singleton = new IdWorker(0, 0);
				}
			}
		}
		return singleton;
	}

	private IdWorker()
	{
	}

	/*public static void main(String[] args)
	{
		IdWorker idWorker = new IdWorker(9, 5);
		long id = idWorker.nextId();
		System.out.println(id);
	}*/

}
