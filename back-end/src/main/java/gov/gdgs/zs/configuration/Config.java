package gov.gdgs.zs.configuration;

public final class Config {

	/**
	 * prefix of Project's REST API
	 */
	public static final String URI_API_PREFIX = "/api";

	public static final String URI_API_VERSION = "1.1";

	public static final String PROJECT_SCHEMA = ""; // 例如: zs_new.

	public static final String URI_API_ZS = "/zs";

	public static final String PUBLIC_PREFIX = "/pub";

	public static final String URL_PROJECT = URI_API_PREFIX + URI_API_ZS;

	public static final String URL_PUBLIC = PUBLIC_PREFIX + URI_API_PREFIX;

	public static final String HASHID_SALT = "gdzs & gdky";

	public static final int HASHID_LEN = 6;

}
