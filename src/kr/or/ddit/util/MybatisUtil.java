package kr.or.ddit.util;


// MyBatis환경설정파일(mybatis-config.xml)을 읽어와서 그 내용을 처리한 후
// 처리된 결과를 갖는 sqlSessionFactory객체를 생성하고
// SqlSession객체를 반환하는 메소드를 구성된 class 작성하기

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    //객체 션수선언
    static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream in = null;

        try {
            // 1-1. 환경설정파일을 읽어올 스트림 객체를 생성한다
            in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");

            // 1-2. 환경 설정 파일을 읽어와 환경 설정 작업이 완성되면
            // SqlSessionFactory객체를 생성한다
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            System.out.println("MyBatis 초기화 실패");
            e.printStackTrace();
        }finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // SqlSession객체를 생성해서 반환하는 메소드
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
