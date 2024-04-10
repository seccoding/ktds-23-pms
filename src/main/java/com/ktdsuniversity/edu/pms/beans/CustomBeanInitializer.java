package com.ktdsuniversity.edu.pms.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

@SpringBootConfiguration
public class CustomBeanInitializer {

	private Logger logger = LoggerFactory.getLogger(CustomBeanInitializer.class);

	/**
	 * application.yml 파일에 작성된 사용자 환경설정 정보들을 읽어와서 멤버변수로 할당해 둔다.
	 */
	@Value("${app.multipart.base-dir:c:/uploadFiles}")
	private String baseDir;

	@Value("${app.multipart.obfuscation.enable:false}")
	private boolean enableObfuscation;

	@Value("${app.multipart.obfuscation.hide-ext.enable:false}")
	private boolean enableObfuscationHideExt;

	@Value("${app.multipart.available-file-list.enable:false}")
	private boolean enableAvailableFileList;

	@Value("${app.multipart.available-file-list.list}")
	private List<String> availableFileList;

	@Value("${app.multipart.available-file-list.handler:tika}")
	private String fileMimeTypeHandler;

	/**
	 * 스프링이 클래스를 객체화 시키고 필요한 값들이나 객체를 모두 할당한 이후에
	 * 
	 * @PostContruct 가 적용된 메소드를 실행시킨다.
	 */
	@PostConstruct
	public void postContructor() {
		logger.debug("> baseDir: " + baseDir);
		logger.debug("> enableObfuscation: " + enableObfuscation);
		logger.debug("> enableObfuscationHideExt: " + enableObfuscationHideExt);
		logger.debug("> availableFileList: " + availableFileList);
		logger.debug("> availableFileList: " + availableFileList.size());
		logger.debug("> availableFileList: " + availableFileList.get(0));
		logger.debug("> availableFileList: " + availableFileList.get(1));
	}

	/**
	 * @Bean 애노테이션이 적용된 메소드가 실행되면 이 메소드가 반환하는 객체를 Bean Container에 적재를 한다.
	 * 
	 *       메소드의 이름이 Bean객체의 이름이 된다.
	 * @return
	 */
	@Bean
	FileHandler fileHanlder() {
		FileHandler fileHandler = new FileHandler();
		fileHandler.setBaseDir(this.baseDir);
		fileHandler.setEnableObfuscation(this.enableObfuscation);
		fileHandler.setEnableObfuscationHideExt(this.enableObfuscationHideExt);
		fileHandler.setAvailableFileList(this.availableFileList);
		fileHandler.setEnableAvailableFileList(this.enableAvailableFileList);
		fileHandler.setHandler(this.fileMimeTypeHandler);
		return fileHandler;
	}

	@Bean
	SHA sha() {
		return new SHA();
	}

}