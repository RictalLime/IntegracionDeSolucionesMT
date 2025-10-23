package modelo.mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

    private static final String RESOURCE = "modelo/mybatis/mybatis-config.xml";
    private static final String ENVIROMENT = "desarrollo";

    public static SqlSession getSession() {
        SqlSession session = null;
        try {
            Reader reader = Resources.getResourceAsReader(RESOURCE);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader, ENVIROMENT); //Factory represetna todas las reglas pero no construye y Builder es la ccion la construccion del metodo.
            // El factory ayuda a tener siempre una estructura fija en las conexiones. Basicamente hacer estandar una conexion. Ayuda a identificar rapidamente la conexion.
            // Cuando vea un Factory, es porque tiene muchas formas de como crearse 
            // Builder es para acortar lo que tiene mi clase
            session = sqlMapper.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }
}
