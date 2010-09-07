package net.win.vo;

import java.io.File;
/**
 * 文件上传对象
 * 
 * @author xgj
 * 
 */
public class UpLoadFileVO {
	/**
	 * 图片
	 */
	private String title;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String savePath;

	public String getSuffix() {
		return "."+fileFileName.split("\\.")[1];
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
