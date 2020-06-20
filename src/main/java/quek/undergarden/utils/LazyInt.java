package quek.undergarden.utils;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class LazyInt implements IntSupplier
{
	private final IntSupplier generator;
	private volatile int value;
	private volatile boolean resolved = false;

	public LazyInt(IntSupplier intSupplier)
	{
		generator = intSupplier;
	}

	public LazyInt(Supplier<Integer> intSupplier)
	{
		this((IntSupplier) intSupplier::get);
	}

	@Override
	public int getAsInt()
	{
		if (!resolved) {
			synchronized (this) {
				if (!resolved) {
					value = generator.getAsInt();
					resolved = true;
				}
			}
		}
		return value;
	}

	public synchronized void invalidate()
	{
		resolved = false;
	}
}
