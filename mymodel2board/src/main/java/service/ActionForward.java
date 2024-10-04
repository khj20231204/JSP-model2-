package service;

public class ActionForward { //Forward 방식 설정
	private boolean redirect;
	private String path;
	
	public boolean isRedirect() {
		return redirect;
	}
	public String getPath() {
		return path;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
