package ag.proxyserver.streamer;

public class StreamBinder {
	private final Stream commandStream;
	private final Stream transferStream;
	
	public StreamBinder() {
		this.commandStream = new Stream();
		this.transferStream = new Stream();
	}
	
	public Stream getCommandStream() {
		return commandStream;
	}
	
	public Stream getTransferStream() {
		return transferStream;
	}
}
