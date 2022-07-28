package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("-----------------------------------");
		System.out.println("Post - upload.do 호출");
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		System.out.println("-----------------------------------");
		System.out.println("Post - upload.do 호출");		
		System.out.println("Download 수행 비즈니스 로직 실행");		
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("-----------------------------------");
		System.out.println("Download 수행 비즈니스 로직 실행");	
		
		request.setCharacterEncoding("utf-8");						// request , UTF-8 인코딩
		response.setContentType("text/html; charset=utf-8");		// response, text/html , UTF-8 인코딩 (컨텐츠 타입) 
		
		String file_repo = "C:\\file_repo";							// 다운로드 받을 경로 (클라이언트)
		String fileName = (String) request.getParameter("fileName");// 파라미터로 파일 이름을 받는다
		System.out.println("fileName=" + fileName);					// 파일 이름 출력
		
		OutputStream out = response.getOutputStream();				// 응답 출력 스트림 생성 , response
		
		String downFile = file_repo + "\\" + fileName;				// 다운로드 파일이 현재있는 경로 + 파일 이름
		File f = new File(downFile);								// 다운로드 받은 파일의 절대 경로를 담은, 파일 객체 생성
		
		
		// [파일 헤더] 추가
		response.setHeader("Cache-Control", "no-cache");							   //(1)			
		// (1) : Response의 헤더 설정 , 노캐쉬 , (걍쓰셈) 
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName); // (2)
		// (2) : Response의 헤더 추가 , 파일 첨부한다 ~
		
		// [중요] 파일과 연결된 파일 inputStream, 이객체로 읽는다
		FileInputStream in = new FileInputStream(f);				
		// 서버의 파일을 일단 가져와야하니 , 파일 입력 스트림 객체 생성 
		
		byte[] buffer = new byte[1024 * 8];		// 버퍼 사용할 바이트 생성 (한번에 8KB 씩)
		while (true) {							// 무한동력 While문
			int count = in.read(buffer);		// 파일을 읽는다 8바이트씩
			if (count == -1)					// 읽을게 1도없으면 
				break;							// break 멈춰!!!
			out.write(buffer, 0, count);		// 출력 스트림에서 buffer를 0부터 count까지 출력
		}										// 74번에서 buffer만큼 읽어서 buffer에 담고, count에도 담는다
		in.close();
		out.close();
	}

}
