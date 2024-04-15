package com.ktdsuniversity.edu.pms.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.pms.exceptions.FileNotExistsException;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

public class FileHandler {

	private Logger logger = LoggerFactory.getLogger(FileHandler.class);

	private String baseDir;
	private boolean enableObfuscation;
	private boolean enableObfuscationHideExt;
	private boolean enableAvailableFileList;
	private List<String> availableFileList;
	private String handler;

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public void setEnableObfuscation(boolean enableObfuscation) {
		this.enableObfuscation = enableObfuscation;
	}

	public void setEnableObfuscationHideExt(boolean enableObfuscationHideExt) {
		this.enableObfuscationHideExt = enableObfuscationHideExt;
	}

	public void setEnableAvailableFileList(boolean enableAvailableFileList) {
		this.enableAvailableFileList = enableAvailableFileList;
	}

	public void setAvailableFileList(List<String> availableFileList) {
		this.availableFileList = availableFileList;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public StoredFile storeFile(MultipartFile multipartFile, boolean hideExt) {

		// 사용자가 업로드한 파일의 이름.
		String uploaedFileName = multipartFile.getOriginalFilename();

		// 난독화 정책에 의해서 만들어진 파일의 이름.
		// 서버에 저장될 파일의 이름.
		String fileName = this.getObfuscationFileName(uploaedFileName, hideExt);

		// 파일이 저장될 경로.
		// this.baseDir : app.multipart.base-dir 에 할당된 값.
		File storePath = new File(this.baseDir, fileName);

		// 업로드할 경로가 존재하지 않을 경우.
		if (!storePath.getParentFile().exists()) {
			// 업로드할 경로(폴더)를 만들어 준다.
			storePath.getParentFile().mkdirs();
		}

		// 사용자가 업로드한 파일을 storePath 경로로 저장시킨다.
		try {
			multipartFile.transferTo(storePath);
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e);
			// 서버의 디스크 용량이 부족할 때!!
			return null;
		}

		if (this.enableAvailableFileList) {
			// 업로드된 파일의 마임타입을 가져온다.
			String mimeType = null;

			if (this.handler.equalsIgnoreCase("tika")) {
				Tika tika = new Tika();
				try {
					mimeType = tika.detect(storePath);
				} catch (IOException e) {
					logger.info(mimeType + " 파일은 업로드 할 수 없습니다.");
					storePath.delete();
					logger.error(e.getMessage(), e);
					return null;
				}
			} else if (this.handler.equalsIgnoreCase("jmimemagic")) {
				Path path = Paths.get(storePath.getAbsolutePath());
				try {
					byte[] data = Files.readAllBytes(path);
					MagicMatch match = Magic.getMagicMatch(data);
					mimeType = match.getMimeType();
				} catch (IOException | MagicParseException
						| MagicMatchNotFoundException | MagicException e) {
					logger.info(mimeType + " 파일은 업로드 할 수 없습니다.");
					storePath.delete();
					logger.error(e.getMessage(), e);
					return null;
				}
			}

			if (!this.availableFileList.contains(mimeType)) {
				storePath.delete();
				logger.info(mimeType + " 파일은 업로드 할 수 없습니다.");
				return null;
			}

			logger.info(mimeType + " 파일을 업로드했습니다.");

		}

		// 업로드 결과를 반환한다.
		return new StoredFile(multipartFile.getOriginalFilename(), storePath);
	}

	/**
	 * 사용자가 업로드한 파일을 서버에 저장한다.
	 * 
	 * @param multipartFile 사용자가 업로드한 파일. (Spring에서 사용자가 업로드한 파일은 MultipartFile로
	 *                      받아올 수 있다)
	 * @return 업로드 결과 (사용자가 업로드한 파일명, 저장된 파일명, 저장된 파일의 크기, 저장된 파일의 경로)
	 */
	public StoredFile storeFile(MultipartFile multipartFile) {
		return this.storeFile(multipartFile, true);
	}

	/**
	 * 사용자가 파일 업로드를 했을 때 application.yml에 정의된 난독화 정책에 의해서 파일명을 난독화한 뒤 난독화된 파일명을
	 * 반환한다. 만약, 난독화 정책을 사용하지 않겠다 라고 설정한 경우는 업로드한 파일의 이름을 그대로 반환한다.
	 * 
	 * @param fileName 사용자가 업로드한 파일의 이름.
	 * @return 난독화된 파일의 이름.
	 */
	private String getObfuscationFileName(String fileName, boolean hideExt) {

		// application.yml 파일의
		// app.multipart.obfuscation.enable 의 값이 true 일 경우
		if (this.enableObfuscation) {
			// 업로드한 파일의 이름에서 확장자만 분리한다.
			// app.multipart.obfuscation.hide-ext.enable 의 값이 true 일 때
			// 파일의 확장자를 숨겨야하고 false일 때 확장자를 붙여야 하기 때문.
			// 파일의 이름이 uploadtest.xml 일 경우 ".xml" 이 할당된다.
			String ext = fileName.substring(fileName.lastIndexOf("."));

			// 파일의 이름을 난독화하기 위해서 난수를 생성한다.
			// 생성되어야 하는 난수는 절대 중복이 생성되어서는 안된다!!!
			// 현재시간(연월일시분초밀리초) 기반의 난수를 생성하면 중복은 발생하지 않는다.
			String obfuscationName = UUID.randomUUID().toString();

			/*
			 * app.multipart.obfuscation.hide-ext.enable의 값이 true일 때
			 */
			if (this.enableObfuscationHideExt && hideExt) {
				// 난독화된 파일의 이름을 반환.
				return obfuscationName;
			}
			// app.multipart.obfuscation.hide-ext.enable의 값이 false일 때
			else {
				// 난독화된 파일의 이름.확장자
				return obfuscationName + ext;
			}

		}

		return fileName;
	}

	/**
	 * 첨부된 파일을 삭제한다
	 * 
	 * @param storedFileName 삭제할 파일의 이름
	 */
	public void deleteFileByFileName(String storedFileName) {
		File file = new File(this.baseDir, storedFileName);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * 서버에 저장된 파일을 반환한다. 만약, 존재하지 않는 파일이라면 File 인스턴스만 반환한다.
	 * 
	 * @param fileName
	 * @return
	 */
	public File getStoredFile(String fileName) {
		return new File(this.baseDir, fileName);
	}

	/**
	 * 파일을 만든다. 만약, 같은 이름의 파일이 이미 존재할 경우 파일명 뒤에 (번호)를 붙인다.
	 * 
	 * @param fileNameWithExtention 확장자를 포함한 파일.
	 * @return
	 */
	public File makeNewFile(String fileNameWithExtention) {
		File file = this.getStoredFile(fileNameWithExtention);

		if (!file.exists()) {
			try {
				// 파일이 존재하지 않을 경우,
				// 임시로 파일을 만든다.
				file.createNewFile();
				return file;
			} catch (IOException e) {
			}
		}

		// 만드려는 파일의 확장자.
		String fileExt = fileNameWithExtention
				.substring(fileNameWithExtention.lastIndexOf("."));
		// 만드려는 파일의 이름 (확장자 제외)
		String fileName = fileNameWithExtention.substring(0,
				fileNameWithExtention.lastIndexOf("."));

		// 같은 이름의 파일이 존재할 경우, 붙일 번호
		int index = 1;
		// 파일 이름에 번호를 붙이기 위한 임시 변수
		String nextFileName = fileName;

		// 파일이 존재할 경우 계속 반복한다.
		while (file.exists()) {
			// 파일 이름에 번호를 붙인다.
			nextFileName = fileName + " (" + index + ")";

			// 확장자를 포함해 새로운 파일을 얻어온다.
			file = this.getStoredFile(nextFileName + fileExt);

			// 파일의 번호를 증가시킨다.
			index += 1;
		}

		try {
			file.createNewFile();
		} catch (IOException e) {
		}

		return file;
	}

	/**
	 * 서버에 저장된 파일을 사용자에게 다운로드 한다.
	 * 
	 * @param originFileName 사용자가 다운로드 받을 파일의 이름 (원본 명)
	 * @param fileName       사용자에게 다운로드 해줄 서버에 저장된 파일의 이름.
	 * @return 다운로드 스트림
	 */
	public ResponseEntity<Resource> download(String originFileName,
			String fileName) {
		// 사용자에게 다운로드할 파일을 가져온다.
		File downloadFile = new File(this.baseDir, fileName);

		// 사용자에게 다운로드할 파일의 이름을 셋팅한다.
		// MS, Linux, Mac, 브라우저 별로 셋팅이 달란다.

		// 동작중인 서버가 Windows 일 경우, 파일의 이름을 Windows 전용 인코딩으로 변경해야한다.
		String newFileName = originFileName;
		try {
			newFileName = new String(originFileName.getBytes("UTF-8"),
					"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + newFileName);

		// 사용자에게 다운로드할 준비를 진행한다.
		InputStreamResource resource;
		try {
			resource = new InputStreamResource(
					new FileInputStream(downloadFile));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new FileNotExistsException();
		}

		// 사용자에게 다운로드 해준다.
		return ResponseEntity.ok().headers(header)
				.contentLength(downloadFile.length())
				.contentType(MediaType.parseMediaType("application/donwload"))
				.body(resource);
	}

	public class StoredFile {
		private String fileName;
		private String realFileName;
		private String realFilePath;
		private long fileSize;

		public StoredFile(String fileName, /* java.io.File */ File storeFile) {
			this.fileName = fileName;
			this.realFileName = storeFile.getName();
			this.realFilePath = storeFile.getAbsolutePath();
			this.fileSize = storeFile.length();
		}

		public String getFileName() {
			return fileName;
		}

		public String getRealFileName() {
			return realFileName;
		}

		public String getRealFilePath() {
			return realFilePath;
		}

		public long getFileSize() {
			return fileSize;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public void setRealFileName(String realFileName) {
			this.realFileName = realFileName;
		}

		public void setRealFilePath(String realFilePath) {
			this.realFilePath = realFilePath;
		}

		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}
		
		

	}

}