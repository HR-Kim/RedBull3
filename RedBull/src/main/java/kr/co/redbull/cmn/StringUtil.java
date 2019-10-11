package kr.co.redbull.cmn;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kr.co.redbull.code.service.Code;
import kr.co.redbull.product.service.Product;

public class StringUtil {
	private static Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	
	// File Root 디렉토리
	public static final String UPLOAD_ROOT = "D:\\HR_FILE";
	
	/**
	 * Product 리스트에서 startNum에서 LastNum까지 추출하는 메소드
	 * 0번째 부터 시작
	 */
	public static List<Product> selectList(List<Product> list, int startNum, int lastNum){
		List<Product> outList = new ArrayList<Product>();
		for(int i=0; i<list.size(); i++) {
			if(i>=startNum && i<=lastNum) {
				Product getOne = list.get(i);
				outList.add(getOne);
			}
		}
		return outList;
	}
	
	
	/**
	 * D:\\HR_FILE\2019\09
	 */
	public static String dynamicDir() {
		// 01.동적으로 UPLOAD_ROOT 디렉토리 생성
		File fileRootDir = new File(UPLOAD_ROOT);
		if (fileRootDir.isDirectory() == false) {
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=flag=" + flag);
		}

		// 02.년월 디렉토리 생성:D:\\HR_FILE\2019\09
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=yyyy=" + yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=mm=" + mm);
		String datePath = UPLOAD_ROOT + File.separator + yyyy + File.separator + mm;
		LOG.debug("=datePath=" + datePath);

		File fileYearMM = new File(datePath);

		if (fileYearMM.isDirectory() == false) {
			boolean flag = fileYearMM.mkdirs();
			LOG.debug("=fileYearMM flag=" + flag);
		}
		
		return datePath;
	}
	
	/**
	 * 파일 Rename 인데 파일명만 뱉음
	 * 
	 * @param f
	 * @return 파일 rename명 cloude.jpg->cloude1~9999.jpg
	 */
	public static String fileRenameShort(File f) {
		String retFileNm = "";
		// 01.파일 존재 Check
		if (!f.exists()) {
			retFileNm = f.getAbsolutePath();
			return retFileNm;
		}

		// 02.파일 있으면: rename
		// cloude + 확장자
		String name = f.getName();// cloude.jpg
		LOG.debug("1.name : "+name);
		String body = null;// cloude
		String ext = null;// jpg
		int dot = name.lastIndexOf(".");
		LOG.debug("2.dot : "+dot);
		if (dot != -1) {
			body = name.substring(0, dot);
			LOG.debug("3.body : "+body);
			ext = name.substring(dot);// .jpg
			LOG.debug("4.ext : "+ext);
		}

		// 03.반복문 처리
		int count = 0;
		while (f.exists() && count < 99999) {
			count++;
			retFileNm = body + count + ext;
			f = new File(f.getParent(), retFileNm);
		}

		return retFileNm;
	}
	
	/**
	 * 파일 Rename
	 * 
	 * @param f
	 * @return 파일 rename명 cloude.jpg->cloude1~9999.jpg
	 */
	public static String fileRename(File f) {
		String retFileNm = "";
		// 01.파일 존재 Check
		if (!f.exists()) {
			retFileNm = f.getAbsolutePath();
			return retFileNm;
		}

		// 02.파일 있으면: rename
		// cloude + 확장자
		String name = f.getName();// cloude.jpg
		LOG.debug("1.name : "+name);
		String body = null;// cloude
		String ext = null;// jpg
		int dot = name.lastIndexOf(".");
		LOG.debug("2.dot : "+dot);
		if (dot != -1) {
			body = name.substring(0, dot);
			LOG.debug("3.body : "+body);
			ext = name.substring(dot);// .jpg
			LOG.debug("4.ext : "+ext);
		}

		// 03.반복문 처리
		int count = 0;
		while (f.exists() && count < 99999) {
			count++;
			retFileNm = body + count + ext;
			f = new File(f.getParent(), retFileNm);
		}

		return f.getAbsolutePath();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String retUUID = uuid.toString().replace("-", "");
		LOG.debug("===========================");
		LOG.debug("retUUID=\n" + retUUID);
		LOG.debug("retUUID length=\n" + retUUID.length());
		LOG.debug("===========================");
		return retUUID;
	}

	/**
	 * yyyy -> 2019 MM -> 10
	 * 
	 * @param format
	 * @return
	 */
	public static String cureDate(String format) {
		if (null == format || format.equals(""))
			format = "yyyyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		return formatter.format(new Date());
	}

	/**
	 * 현제 날자를 8자리로 반환 한다
	 * 
	 * @return yyyyMMdd
	 */
	public static String getNowDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(Calendar.getInstance().getTime());
	}

	/**
	 * 
	 * @Method Name : renderPaing
	 * @작성일 : 2019. 7. 24.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param maxNum:총글수
	 * @param currPageNo:      현재페이지
	 * @param rowPerPage:한페이지에 보여질 글수
	 * @param bottomCount:바닥에  보여질 페이지수
	 * @param url:호출url
	 * @param scriptName:호출    javascript
	 * @return
	 */
	public static String renderPaging(int maxNum, int currPageNo, int rowPerPage, int bottomCount, String url,
			String scriptName) {
		/*
		 * 총글수 : 21 현재페이지 1 총글수 0 바닥에 보여질 페이지수 10 한페이지에 보여질 글수 10 호출url 호출 javascript <<
		 * < 1 2 3 4 5 6 7 8 9 10 > >> 총글수 : 21,1
		 */
		int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;// 총페이지
		int startPaeNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;
		int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;
		int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;
		int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;

		int inx = 0;
		StringBuilder html = new StringBuilder();
		if (currPageNo > maxPageNo) {
			return "";
		}

		// <table><tr><td></td></tr></table>
		html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"     >\n");
		html.append("<tr> \n");
		html.append("<td align=\"center\"> \n");
		html.append("<ul class=\"pagination\"> \n");
		// paging-------------------
		// << &laquo; 왼쪽 꺾인 괄호
		if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
			html.append("<li  class=\"page-item\"><a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url
					+ "',1);\"  > ");
			html.append("&laquo; ");
			html.append("</a></li> \n");
		}
		// <
		if (startPaeNo > bottomCount) {
			html.append("<li  class=\"page-item\"><a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url
					+ "'," + (startPaeNo - 1) + ");\"  > ");
			html.append("< ");
			html.append("</a></li> \n");
		}
		// 1 2 .... 10
		for (inx = startPaeNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
			if (inx == currPageNo) {// 현재 page
				html.append("<li  class=\"disabled\" 	>");
				html.append("<a  href=\"javascript:#\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			} else {
				html.append("<li  class=\"page-item\">");
				html.append("<a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url + "'," + inx
						+ ");\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			}

		}
		// >
		if (maxPageNo >= inx) {
			html.append("<li  class=\"page-item\">");
			html.append("<a  class=\"page-link\" href=\"javascript:" + scriptName + "('" + url + "',"
					+ ((nowBlockNo * bottomCount) + 1) + ");\"  > ");
			html.append("> ");
			html.append("</a> \n");
			html.append("</li>");

		}
		// >> &raquo; 오른쪽 꺾인 괄호
		if (maxPageNo >= inx) {
			html.append("<li  class=\"page-item\">");
			html.append("<a class=\"page-link\" href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo
					+ ");\"  > ");
			html.append("&raquo; ");
			html.append("</a> \n");
			html.append("</li>");
		}
		html.append("</ul> \n");
		// --paging-----------------
		html.append("</td> \n");
		html.append("</tr> \n");
		html.append("</table>");

		// LOG.debug("===========================");
		// LOG.debug("html.toString()=\n"+html.toString());
		// LOG.debug("===========================");
		return html.toString();
	}
	/**
	 * 
	 * @Method Name : makeSelectBox
	 * @작성일 : 2019. 7. 22.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param list:       select에 필용한 코드정보
	 * @param selectBoxNm : <select name="lvl" id="lvl">
	 * @param allYN       : 전체 표시
	 * @return : <select name="lvl" id="lvl"> <option value="">전체</option> <option
	 *         value="1" selected>일반사용자</option> <option value="9">관리자</option>
	 *         </select>
	 */
	public static String makeSelectBox(List<Code> list, String selectBoxNm, boolean allYN) {
		StringBuilder sb = new StringBuilder();
		// <select name="lvl" id="lvl">
		sb.append("<select  class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");
		// 전체
		if (allYN == true) {
			sb.append("<option value=''>전체</option> \n");
		}

		// <option value="1" selected>일반사용자</option>
		for (int i=0; i<list.size(); i++) {
			Code vo = list.get(i);
			sb.append("\t<option value='" + vo.getCodeId() + "' ");
			if (i==0) {
				sb.append("selected='selected' ");
			}

			sb.append(">");
			sb.append(vo.getCodeNm());
			sb.append("</option>\n");
		}
		sb.append("</select> \n");
		LOG.debug("------------------------");
		LOG.debug(sb.toString());
		LOG.debug("------------------------");
		return sb.toString();
	}
	/**
	 * 
	 * @Method Name : makeSelectBox
	 * @작성일 : 2019. 7. 22.
	 * @작성자 : SIST
	 * @변경이력 : 최초작성
	 * @Method 설명 :
	 * @param list:       select에 필용한 코드정보
	 * @param selectBoxNm : <select name="lvl" id="lvl">
	 * @param selectedNm  : <option selected
	 * @param allYN       : 전체 표시
	 * @return : <select name="lvl" id="lvl"> <option value="">전체</option> <option
	 *         value="1" selected>일반사용자</option> <option value="9">관리자</option>
	 *         </select>
	 */
	public static String makeSelectBox(List<Code> list, String selectBoxNm, String selectedNm, boolean allYN) {
		StringBuilder sb = new StringBuilder();
		// <select name="lvl" id="lvl">
		sb.append("<select  class=\"form-control input-sm\" name='" + selectBoxNm + "' id='" + selectBoxNm + "' > \n");
		// 전체
		if (allYN == true) {
			sb.append("<option value=''>전체</option> \n");
		}

		// <option value="1" selected>일반사용자</option>
		for (Code dto : list) {
			Code vo = dto;
			sb.append("\t<option value='" + vo.getCodeId() + "' ");
			if (selectedNm.equals(vo.getCodeId())) {
				sb.append("selected='selected' ");
			}

			sb.append(">");
			sb.append(vo.getCodeNm());
			sb.append("</option>\n");
		}
		sb.append("</select> \n");
		LOG.debug("------------------------");
		LOG.debug(sb.toString());
		LOG.debug("------------------------");
		return sb.toString();
	}

	public static String nvl(String val) {
		return nvl(val, "");
	}

	public static String nvl(String val, String rep) {
		if (val == null || ("").equals(val)) {
			val = rep;
		}
		return val;
	}

	/**
	 * 개행문자를 <br/>
	 * 로 바꾼다.<br>
	 *
	 * @param content
	 * @return String
	 */
	public static String nl2br(String content) {
		// content = translate(content, "\n", "<br />\n");
		content = translate(content, "\r", "<br />\n");
		return content;
	}

	public static String translate(String content, String pattern, String replace) {
		if (content == null)
			return "";
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = content.indexOf(pattern, s)) >= 0) {
			result.append(content.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(content.substring(s));
		return result.toString();
	}

	/**
	 * 문자열을 입력받아서, 3자리 단위마다 구분자처리한다.
	 *
	 * @Method Name : makeMoneyType
	 * @param moneyString
	 * @return
	 */
	public static String makeMoneyType(int money) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df = new DecimalFormat("##,###.##");
		return df.format(money);
	}

	/**
	 * 문자열을 입력받아서, 3자리 단위마다 구분자처리한다.
	 *
	 * @Method Name : makeMoneyType
	 * @param moneyString
	 * @return
	 */
	public static String makeMoneyType(double money) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df = new DecimalFormat("##,###.##");
		return df.format(money);
	}

	/**
	 * String으로 받은 문자열을 가격 ,를 찍어서 변환 해주는 유틸
	 * </pre>
	 * 
	 * @Method Name : makeMoneyType
	 * @param money
	 * @return
	 */
	public static String makeMoneyType(String money) {
		String newMoney = money;
		if (newMoney == null || newMoney.isEmpty()) {
			newMoney = "0";
		}
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df = new DecimalFormat("##,###.##");
		return df.format(Float.parseFloat(newMoney));
	}

	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}
}
