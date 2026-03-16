package kr.co.seoulit.insa.sys.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

public class EmpImgUploadUtil {
	public static String doFileUpload(HttpServletRequest request, MultipartFile file, String empCode) throws IOException {
		InputStream in = file.getInputStream();
		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf("."));
				
		String saveFileName = empCode + fileExt;
		
		// 실제 배포 환경이나 로컬 환경에 맞춰 경로를 유연하게 처리해야 함
		// 우선 기존 코드의 의도를 유지하되 MultipartFile 인터페이스에 맞춤
		String rootPath = request.getServletContext().getRealPath("/");
		String savePath = rootPath + "profile" + File.separator + saveFileName;
		
		File uploadDir = new File(rootPath + "profile");
		if (!uploadDir.exists()) uploadDir.mkdirs();

		FileOutputStream fout = new FileOutputStream(savePath);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
			fout.write(buffer, 0, bytesRead);
		}
		in.close();
		fout.close();
		
		return savePath;
	}
}
