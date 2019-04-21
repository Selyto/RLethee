package utils;

public enum browser {
	CHROME("chrome"), 
	FIREFOX("firefox"), 
	IE("iexplorer");
	
	    private String bname = null;
	 
	    browser(String term) {
	        this.bname = term;
	    }
	 
	    public String setBrowser() {
	        return bname;
	    }
}
