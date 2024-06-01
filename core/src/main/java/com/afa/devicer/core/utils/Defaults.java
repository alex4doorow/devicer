package com.afa.devicer.core.utils;

import java.nio.charset.StandardCharsets;

public class Defaults {
	public static final String X_Request_ID = "X-Request-ID";
	public static final String X_Request_Type = "X-Request-Type";
	public static final String X_Message_Type = "X-Message-Type";
	public static final String X_Agent_ID = "X-Agent-ID";
	public static final String X_Client_ID = "X-Client-ID";
	public static final String X_Token = "X-Token";

	public static final String defEncoding = StandardCharsets.UTF_8.name();
	public static final String defSignatureCharset = StandardCharsets.UTF_16LE.name();
//	public static final String defMxSignatureAlgorithm = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";

}
