package com.hos.domain;

public class UploadFileResponse {

	private String name;
	private String type;
	private String downloadURI;
	private long size;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDownloadURI() {
		return downloadURI;
	}
	public void setDownloadURI(String downloadURI) {
		this.downloadURI = downloadURI;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public UploadFileResponse(String name, String type, String downloadURI, long size) {
		super();
		this.name = name;
		this.type = type;
		this.downloadURI = downloadURI;
		this.size = size;
	}
	public UploadFileResponse() {
		super();
	}
	
}
