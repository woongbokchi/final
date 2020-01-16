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
	// servlet-context�� ������ ���ε� �ּ�
	@Resource(name = "uploadPath")
	private String path;

	// ���� ���ε� �޼ҵ�
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID(); // �ߺ����� �ʴ� �����̸� ����
		String savedName = uid.toString() + "_" + originalName; // ������ �̸��� ������
																// ����
		File target = new File(path, savedName); // File��ü�� ����
		FileCopyUtils.copy(fileData, target); // ������ ��ü�� �����Ѵ�.
		return savedName;
	}

	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(MultipartFile file) throws Exception {
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		return savedName;
	}

	// ���ε� ������ �̹����� ����
	@ResponseBody
	@RequestMapping("/display")
	public byte[] display(String fileName) throws Exception {
		InputStream in = new FileInputStream(path + "/" + fileName);
		byte[] image = IOUtils.toByteArray(in);
		in.close();
		return image;
	}

	// ���ε� ������ ����
	@ResponseBody
	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public void deleteFile(String fileName) {
		new File(path + "/" + fileName).delete();
	}

	// ���� �ٿ�ε�
	@ResponseBody
	@RequestMapping(value = "downloadFile")
	public ResponseEntity<byte[]> downloadFile(String fileName) throws Exception {
		System.out.println("�����̸�:" + fileName);
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