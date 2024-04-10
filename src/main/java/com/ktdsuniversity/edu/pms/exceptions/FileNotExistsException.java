package com.ktdsuniversity.edu.pms.exceptions;

public class FileNotExistsException extends RuntimeException {

	private static final long serialVersionUID = -1533597945109570219L;

	public FileNotExistsException() {
		super("파일이 존재하지 않습니다.");
	}
}
