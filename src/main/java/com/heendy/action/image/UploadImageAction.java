package com.heendy.action.image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.heendy.action.Action;
import com.heendy.common.ErrorCode;
import com.heendy.common.ErrorResponse;
import com.heendy.common.exception.NotSupportExtension;
import com.heendy.common.exception.NotSupportOS;


/**
 * @author 이승준
 * 
 * 이미지 업로드 Action 클래스
 * */
public class UploadImageAction implements Action {

	//허용하는 확장자 명 배열
	private final String[] supportExts = {"jpg","jpeg","png"};
	
	//업로드 성공시 반환 할 메시지 클래스
	private class SuccessRes {
		private final String upload = "true";
		private final String result = "업로드가 완료되었습니다.";
		private final String imageName;
		
		public SuccessRes(String imageName) {
			this.imageName = imageName;
		}
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String encoding = "utf-8";
		String imageRepoPath;
		
		
		/*OS 정보를 읽어 OS에 따른 업로드 폴더 경로 설정*/
		String osName = System.getProperty("os.name");
		
		if(osName.startsWith("Mac")) {
			imageRepoPath = request.getServletContext().getInitParameter("MacUploadImagePath");
		} else if (osName.startsWith("Windows")) {
			imageRepoPath = request.getServletContext().getInitParameter("WindowsUploadImagePath");
		} else {
			throw new NotSupportOS(osName);
		}
		
		
		System.out.println(imageRepoPath);
		
		//이미지 저장 경로 및 업로드 용량 설정
		File uploadPath = new File(imageRepoPath);
		if(!uploadPath.exists()) uploadPath.mkdir();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(uploadPath);
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);

		
		List<String> savedImageNames = new ArrayList<>();
		
		try {
			List<FileItem> items = upload.parseRequest(request);

		
			for (FileItem item : items) {
				if (item.isFormField()) {
					System.out.println(item.getFieldName() + "=" + item.getString(encoding));
				} else {
					System.out.println("매개변수이름 : " + item.getFieldName());
					System.out.println("파일이름 : " + item.getName());
					System.out.println("파일크기 : " + item.getSize() + "bytes");

					
					if (item.getSize() > 0) {
						int idx = item.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = item.getName().lastIndexOf("/");
						}
						
						//파일 확장자 명 추출
						String ext = FilenameUtils.getExtension(item.getName());
						
						//파일 확장자 검증
						//검증이 실패시, 예외 발생
						if(!this.validExt(ext)) {
							throw new NotSupportExtension(ext);
						}
						
						//파일 명을 uuid로 변경
						String uuidFileName = this.getUUID() + "." + ext;
						
						File uploadFile = new File(uploadPath + "/" + uuidFileName);
						item.write(uploadFile);
						savedImageNames.add(uuidFileName);
					}
				}
			}
			
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/image/product?name=";
			
			//저장한 이미지 파일 중 첫 번째 이미지 url만 사용자에게 반환
			String imgUrl = url + savedImageNames.get(0);
			
			response.setStatus(201);
			String json = new Gson().toJson(new SuccessRes(imgUrl));
			response.getWriter().write(json);
		
		} catch (Exception e) {
			e.printStackTrace();
			ErrorResponse errorResponse;
			if(e instanceof NotSupportExtension) {
				errorResponse = ErrorResponse.of(ErrorCode.NOT_SUPPORT_IMAGE_FILE);
			} else {
				errorResponse= ErrorResponse.of(ErrorCode.UNCAUGHT_SERVER_ERROR);
			}
		
			String json = new Gson().toJson(errorResponse);
			response.setStatus(errorResponse.getStatus());
			response.getWriter().write(json);
		}

	}
	
	/**
	 * @author 이승준
	 * 
	 * @param ext 확장자 문자열
	 * 
	 * @return boolean
	 * 
	 * 요청한 파일 확장자가 허용되는 확장자인지 검증하는 메소드
	 * */
	private boolean validExt(String ext) {
		return Arrays.asList(this.supportExts).contains(ext);
	}
	
	/**
	 * @author 이승준
	 * 
	 * @return String
	 * 
	 * uuid 문자열을 생성하는 메소드
	 * */
	private String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
