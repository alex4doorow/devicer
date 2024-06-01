package com.afa.devicer.core.errors;

public class SecurityException extends CoreException {

	public static final String KEYSTORE_INIT_ERROR = "KEYSTORE_INIT_ERROR";

	public static final String CERT_NOT_FOUND = "CERT_NOT_FOUND";
	public static final String CERT_PARSE_FAILED = "CERT_PARSE_FAILED";

	public static final String KEY_LOAD_ERROR = "KEY_LOAD_ERROR";

	public static final String SIGNATURE_VERIFICATION_ERROR = "SIGNATURE_VERIFICATION_ERROR";
	public static final String DIGITAL_SIGNING_ERROR = "DIGITAL_SIGNING_ERROR";

	public static final String JWT_PARSE_ERROR = "JWT_PARSE_ERROR";

	public static final String LICENSE_VERIFICATION_ERROR = "LICENSE_VERIFICATION_ERROR";
	
	public static final String ENC_FAILED = "ENCRYPTION_FAILED";
	public static final String DEC_FAILED = "DECRYPTION_FAILED";

	public SecurityException(String respCode, String respDesc) {
		super(respCode, respDesc, true);
	}
	
	public SecurityException(String respCode) {
		super(respCode);
	}
}
