package com.heendy.action.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * 이미지 다운로드 Action 클래스
 * */
public class DownloadImageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String imageRepoPath;
		/*OS 정보를 읽어 OS에 따른 업로드 폴더 경로 설정*/
		String osName = System.getProperty("os.name");
		
		String name = request.getParameter("name");
		System.out.println(name);
		
		String ext = FilenameUtils.getExtension(name);
		String contentType = "image/"+ext+";charset=utf-8";
		response.setContentType(contentType);
		
		try {
			if(osName.startsWith("Mac")) {
				imageRepoPath = request.getServletContext().getInitParameter("MacUploadImagePath");
			} else if (osName.startsWith("Windows")) {
				imageRepoPath = request.getServletContext().getInitParameter("WindowsUploadImagePath");
			} else {
				throw new NotSupportOS(osName);
			}
			
			OutputStream out = response.getOutputStream();
			
			String downFile = imageRepoPath+"/"+name;
			File file = new File(downFile);
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName="+name);
			
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[1024*8];
			while(true) {
				int count = in.read(buffer);
				if(count == -1) {
					break;
				}
				out.write(buffer,0,count);
			}
			in.close();
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
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

	
}
