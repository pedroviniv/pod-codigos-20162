package ag.proxyserver.reverse.impl;

import ag.proxyserver.CodeGenerator;
import ag.proxyserver.CodeRepository;
import ag.proxyserver.reverse.ReverseServer;
import ag.proxyserver.reverse.ReverseServerException;
import ag.proxyserver.streamer.Stream;
import ag.proxyserver.streamer.StreamBinder;
import ag.proxyserver.streamer.StreamBinderManager;
import ag.proxyserver.util.Util;

public class ReverseServerImpl implements ReverseServer {
	private String code = null;
	private final StreamBinderManager manager;
	
	public ReverseServerImpl(StreamBinderManager manager) {
		this.manager = manager;
	}

	@Override
	public String register(String oldCode) throws ReverseServerException {
		//
		String result = oldCode;
		if ("CAM 000000-00".equals(oldCode) || oldCode == null){
			String newCode = CodeGenerator.code();
			CodeRepository.add(newCode);
			result = newCode;
		}
		//
		if (!"CAM 000000-00".equals(oldCode) && !CodeRepository.exists(oldCode)){
			throw new ReverseServerException(2002, "Código inexistente.");
		}
		//
		manager.registry((code = result));
		//
		return result;
	}

	@Override
	public String awaitCommand() throws ReverseServerException {
		//
		if (code == null) throw new ReverseServerException(4000, "Erro ao aguardar o comando. Código inválido.");
		//
		StreamBinder binder = manager.getBinder(code);
		Stream stream = binder.getCommandStream();
		//
		return Util.readStream(stream);
	}

	@Override
	public void transferStreamToProxy(int byteStream) throws ReverseServerException {
		if (code == null) throw new ReverseServerException(5000, "Erro ao transferir dados do stream. Código inválido.");
		//
		StreamBinder binder = manager.getBinder(code);
		Stream stream = binder.getTransferStream();
		//
		stream.send(byteStream);
	}

}
