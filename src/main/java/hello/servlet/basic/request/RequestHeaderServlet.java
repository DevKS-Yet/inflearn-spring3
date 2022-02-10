package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);
    }

    // drag(ctrl + shift + alt + t)
    private void printStartLine(HttpServletRequest req) {
        System.out.println("--- REQUEST-LINE - start ---");

        System.out.println("request.getMethod() = " + req.getMethod());
        System.out.println("request.getProtocol() = " + req.getProtocol());
        System.out.println("request. getScheme() = " + req.getScheme());
        System.out.println("request.getRequestURL() = " + req.getRequestURL());
        System.out.println("request.getRequestURI() = " + req.getRequestURI());
        System.out.println("request.getQueryString() = " + req.getQueryString());
        System.out.println("request.isSecure() = " + req.isSecure());

        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest req) {
        System.out.println("--- Headers - start ---");

        // 이건 예전 스타일
//        Enumeration<String> headerNames = req.getHeaderNames();
//        while(headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println("headerName = " + headerName);
//        }

        // 이건 현대 스타일 (아씨... 람다 필요없다매....)
        req.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println("headerName = " + headerName));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest req) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + req.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + req.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        req.getLocales().asIterator().forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + req.getLocale());  // 원하는 최상위 언어 (en/ko 등등)
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + req.getContentType());  // get 방식일 경우에는 null 왜냐면 body에 데이터가 없기때문... postman에서 get으로 설정하고 body에 뭐 적어서 보낸 후에 실행된다고 말하지 마라
        System.out.println("request.getContentLength() = " + req.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + req.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest req) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + req.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + req.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + req.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + req.getLocalName());
        System.out.println("request.getLocalAddr() = " + req.getLocalAddr());
        System.out.println("request.getLocalPort() = " + req.getLocalPort());

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

}
