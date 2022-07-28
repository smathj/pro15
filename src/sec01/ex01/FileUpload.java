package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-----------------------------------");
		System.out.println("Get - upload.do 호출");
		
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-----------------------------------");
		System.out.println("Post - upload.do 호출");
		
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		System.out.println("-----------------------------------");
		System.out.println("Upload 수행 비즈니스 로직 실행");
		
		request.setCharacterEncoding("utf-8");
		
			String encoding = "utf-8";						 			// 인코딩 타입
			File currentDirPath = new File("C:\\file_repo"); 			// 업로드할 경로
			
			// 해당 경로에 폴더가 없을때 생성
			if(currentDirPath.exists() == false) {
				currentDirPath.mkdir();
			}

			
			DiskFileItemFactory factory = new DiskFileItemFactory(); 	// 업로드 관련 API 객체 생성 (1)
			
			factory.setRepository(currentDirPath);						// 업로드할 경로 설정
			factory.setSizeThreshold(1024 * 1024);						// 파일 크기 설정

			ServletFileUpload upload = new ServletFileUpload(factory);	// 업로드 관련 API 객체 생성 (2)
			try {
				List items = upload.parseRequest(request);				// 전달받은 매개변수르 List로 받는다
				for (int i = 0; i < items.size(); i++) {
					FileItem fileItem = (FileItem) items.get(i);		// 파일 객체를 파라미터로 받아서, 실제로 업로드 처리하는 객체
	
					if (fileItem.isFormField()) {
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					} else {
						System.out.println("매개변수이름:" + fileItem.getFieldName());
						System.out.println("파일이름:" + fileItem.getName());
						System.out.println("파일크기:" + fileItem.getSize() + "bytes");
	
						if (fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if (idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx + 1);
							File uploadFile = new File(currentDirPath + "\\" + fileName);
							fileItem.write(uploadFile);					// 업로드 수행
							System.out.println("---------업로드 성공---------");
						} // end if
					} // end if
				} // end for
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("---------업로드 실패---------");
			}
	}

}
