package tek.api.utility;

public enum EndPoints {
	
	TOKEN_GENERATION("/api/token"),
	TOKEN_VEIFY("/api/token/verify"),
	GET_ALL_ACCOUNTS("/api/accounts/get-all-accounts");
	
	
	private String value;
	
	EndPoints(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
