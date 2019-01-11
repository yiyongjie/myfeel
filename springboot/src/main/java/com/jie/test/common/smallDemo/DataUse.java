package com.jie.test.common.smallDemo;

import com.jie.test.common.model.gen.GenColumn;
import com.jie.test.common.model.gen.GenContent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataUse {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/yyjtest?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123";

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("close connection failure", e);
            }
        }
    }

//    public static void main(String[] args) throws SQLException {
//        Connection conn = getConnection();
//        DatabaseMetaData db = conn.getMetaData();
//        ResultSet rs = db.getTables("yyjtest", null, null, new String[] { "TABLE" });
//        List tableNameList = new ArrayList();
//        while (rs.next()) {
//            tableNameList.add(rs.getString("TABLE_NAME"));
//        }
//        System.out.println(db.getUserName());
//    }
    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
//        ResultSet rs = null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES");
        try {

            //获取数据库的元数据
//            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
//            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(1));
//                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            log.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的所有注释
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    log.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }

    /**
     * 获取模板元素
     * @param tableName
     * @return
     */
    public static GenContent getModel(String tableName){
        GenContent genContent=new GenContent();
        String[] splitTableName=tableName.split("_");
        StringBuffer className=new StringBuffer();
        StringBuffer varName=new StringBuffer();
        //这是对象的类名,变量名
        for(int i=0;i<splitTableName.length;i++){
            String splitContent=splitTableName[i].substring(0,1).toUpperCase().concat(splitTableName[i].substring(1));
            String splitVarContent=splitTableName[i].substring(0,1).concat(splitTableName[i].substring(1));
            className.append(splitContent);
            varName.append(splitVarContent);
        }
        genContent.setClassName(className.toString());
        genContent.setVarName(varName.toString());
        genContent.setTableName(tableName);
        //拿到所有的字段属性
        List<String> columnNames=getColumnNames(tableName);
        List<String> columnType=getColumnTypes(tableName);
        List<String> columnComment=getColumnComments(tableName);
        List<GenColumn> columns=new ArrayList<>();
        for(int i=0;i<columnNames.size();i++){
            GenColumn genColumn=new GenColumn();
            genColumn.setColumn(columnNames.get(i));
            genColumn.setColunmType(columnType.get(i));
            genColumn.setColumnRemark(columnComment.get(i));
            //对象里的字段名称
            StringBuffer ModelColunmName=new StringBuffer();
            String[] splitColumnName=columnNames.get(i).split("_");
            for(int j=0;j<splitColumnName.length;j++){
                String splitContent=splitColumnName[i].substring(0,1).toUpperCase().concat(splitColumnName[i].substring(1));
                ModelColunmName.append(splitContent);
            }
            genColumn.setModelColumn(ModelColunmName.toString());
            columns.add(genColumn);
        }
        genContent.setGenColumns(columns);
        return genContent;
    }


    public static void main(String[] args) throws SQLException {
        List<String> tableNames = getTableNames();
        System.out.println("tableNames:" + tableNames);
        for (String tableName : tableNames) {
            System.out.println("ColumnNames:" + getColumnNames(tableName));
            System.out.println("ColumnTypes:" + getColumnTypes(tableName));
            System.out.println("ColumnComments:" + getColumnComments(tableName));
        }
    }
}
