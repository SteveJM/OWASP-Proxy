package org.owasp.proxy.ssl;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509KeyManager;

public class SingleX509KeyManager implements X509KeyManager {

	private String alias;

	private PrivateKey pk;

	private X509Certificate[] certs;

	public SingleX509KeyManager(String alias, PrivateKey pk,
			X509Certificate[] certs) {
		this.alias = alias;
		this.pk = pk;
		this.certs = certs;
	}

	public String chooseClientAlias(String[] keyType, Principal[] issuers,
			Socket socket) {
		throw new UnsupportedOperationException("Not implemented");
	}

	public String chooseServerAlias(String keyType, Principal[] issuers,
			Socket socket) {
		return alias;
	}

	public X509Certificate[] getCertificateChain(String alias) {
		return certs;
	}

	public String[] getClientAliases(String keyType, Principal[] issuers) {
		throw new UnsupportedOperationException("Not implemented");
	}

	public PrivateKey getPrivateKey(String alias) {
		return pk;
	}

	public String[] getServerAliases(String keyType, Principal[] issuers) {
		return new String[] { alias };
	}
}