/*package com.ex.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	// servlet-context에 지정한 업로드 주소
	@Resource(name = "uploadPath")
	private String path;

	// 파일 업로드 메소드
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID(); // 중복되지 않는 랜덤이름 생성
		String savedName = uid.toString() + "_" + originalName; // 생성된 이름을 변수에
																// 저장
		File target = new File(path, savedName); // File객체를 생성
		FileCopyUtils.copy(fileData, target); // 생성된 객체를 복사한다.
		return savedName;
	}

	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(MultipartFile file) throws Exception {
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		return savedName;
	}

	// 업로드 파일을 이미지로 보임
	@ResponseBody
	@RequestMapping("/display")
	public byte[] display(String fileName) throws Exception {
		InputStream in = new FileInputStream(path + "/" + fileName);
		byte[] image = IOUtils.toByteArray(in);
		in.close();
		return image;
	}

	// 업로드 파일을 삭제
	@ResponseBody
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public void deleteFile(String fileName) {
		new File(path + "/" + fileName).delete();
	}

	// 파일 다운로드
	@ResponseBody
	@RequestMapping(value = "downloadFile")
	public ResponseEntity<byte[]> downloadFile(String fileName) throws Exception {
		System.out.println("파일이름:" + fileName);
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(path + "/" + fileName);
			fileName = fileName.substring(fileName.indexOf("_") + 1);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition",
					"attachment;filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.toString());
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
}
*/