package org.owasp.ajp;

import java.util.Map;

import org.owasp.httpclient.StreamingRequest;

public class AJPRequest extends StreamingRequest.Impl {

	private String remoteAddress, remoteHost, context, servletPath, remoteUser,
			authType, route, sslCert, sslCipher, sslSession, sslKeySize,
			secret, storedMethod;

	private Map<String, String> requestAttributes = null;

	public AJPRequest() {
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRequestAttributes(Map<String, String> attributes) {
		this.requestAttributes = attributes;
	}

	public Map<String, String> getRequestAttributes() {
		return requestAttributes;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return the servletPath
	 */
	public String getServletPath() {
		return servletPath;
	}

	/**
	 * @param servletPath
	 *            the servletPath to set
	 */
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	/**
	 * @return the remoteUser
	 */
	public String getRemoteUser() {
		return remoteUser;
	}

	/**
	 * @param remoteUser
	 *            the remoteUser to set
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	/**
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * @param authType
	 *            the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * @param route
	 *            the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the sslCert
	 */
	public String getSslCert() {
		return sslCert;
	}

	/**
	 * @param sslCert
	 *            the sslCert to set
	 */
	public void setSslCert(String sslCert) {
		this.sslCert = sslCert;
	}

	/**
	 * @return the sslCipher
	 */
	public String getSslCipher() {
		return sslCipher;
	}

	/**
	 * @param sslCipher
	 *            the sslCipher to set
	 */
	public void setSslCipher(String sslCipher) {
		this.sslCipher = sslCipher;
	}

	/**
	 * @return the sslSession
	 */
	public String getSslSession() {
		return sslSession;
	}

	/**
	 * @param sslSession
	 *            the sslSession to set
	 */
	public void setSslSession(String sslSession) {
		this.sslSession = sslSession;
	}

	/**
	 * @return the sslKeySize
	 */
	public String getSslKeySize() {
		return sslKeySize;
	}

	/**
	 * @param sslKeySize
	 *            the sslKeySize to set
	 */
	public void setSslKeySize(String sslKeySize) {
		this.sslKeySize = sslKeySize;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret
	 *            the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * @return the storedMethod
	 */
	public String getStoredMethod() {
		return storedMethod;
	}

	/**
	 * @param storedMethod
	 *            the storedMethod to set
	 */
	public void setStoredMethod(String storedMethod) {
		this.storedMethod = storedMethod;
	}

	/**
	 * @param remoteAddress
	 *            the remoteAddress to set
	 */
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	/**
	 * @param remoteHost
	 *            the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

}
