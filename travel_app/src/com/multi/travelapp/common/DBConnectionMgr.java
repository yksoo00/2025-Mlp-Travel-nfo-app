
 /* Copyright(c) 2001 iSavvix Corporation (http://www.isavvix.com/)
 *
 *                        All rights reserved
 *
 * Permission to use, copy, modify and distribute this material for
 * any purpose and without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies, and that the name of iSavvix Corporation not be used in
 * advertising or publicity pertaining to this material without the
 * specific, prior written permission of an authorized representative of
 * iSavvix Corporation.
 *
 * ISAVVIX CORPORATION MAKES NO REPRESENTATIONS AND EXTENDS NO WARRANTIES,
 * EXPRESS OR IMPLIED, WITH RESPECT TO THE SOFTWARE, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR ANY PARTICULAR PURPOSE, AND THE WARRANTY AGAINST
 * INFRINGEMENT OF PATENTS OR OTHER INTELLECTUAL PROPERTY RIGHTS.  THE
 * SOFTWARE IS PROVIDED "AS IS", AND IN NO EVENT SHALL ISAVVIX CORPORATION OR
 * ANY OF ITS AFFILIATES BE LIABLE FOR ANY DAMAGES, INCLUDING ANY
 * LOST PROFITS OR OTHER INCIDENTAL OR CONSEQUENTIAL DAMAGES RELATING
 * TO THE SOFTWARE.
 *
 */
package com.multi.travelapp.common;

import java.sql.*;
import java.util.Properties;
import java.util.Vector;
public class DBConnectionMgr {
    private Vector connections = new Vector(10); //Connection 10
    private String _driver = "com.mysql.cj.jdbc.Driver",
    _url = "jdbc:mysql://localhost:3306/travel_db",
    _user = "root",
    _password = "rudtn315";
    private boolean _traceOn = false;
    private boolean initialized = false;
    private int _openConnections = 10;
    private static DBConnectionMgr instance = null;

    public DBConnectionMgr() {
    }

    /** Use this method to set the maximum number of open connections before
     unused connections are closed.
     */

    public static DBConnectionMgr getInstance() {
        // 인스턴스가 아직 생성되지 않았을 경우에만 인스턴스 생성
        if (instance == null) {
            // 동기화 블록으로 멀티스레드 환경에서 동시 접근을 제어
            synchronized (DBConnectionMgr.class) {
                // 두 번째 null 체크로 다른 스레드에서 인스턴스가 생성되었는지 확인
                if (instance == null) {
                    // 인스턴스 생성
                    instance = new DBConnectionMgr();
                }
            }
        }
        // 생성된 인스턴스 반환
        return instance;
    }

    public void setOpenConnectionCount(int count) {
        _openConnections = count;
    }

    public void setEnableTrace(boolean enable) {
        _traceOn = enable;
    }

    /** Returns a Vector of java.sql.Connection objects */
    public Vector getConnectionList() {
        return connections;
    }

    /** Opens specified "count" of connections and adds them to the existing pool */
    public synchronized void setInitOpenConnections(int count)
            throws SQLException {
        // 이미 초기화된 커넥션이 있을 경우 다시 초기화하지 않음
        if (connections.size() > 0) {
            System.out.println("Connection pool is already initialized with " + connections.size() + " connections.");
            printAllConnections();

            return; // 이미 초기화된 경우 메서드를 종료
        }

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < count; i++) {
            c = createConnection();
            co = new ConnectionObject(c, false);
            connections.addElement(co);
            System.out.println("ConnectionPoolManager: Adding new DB connection to pool (" + connections.size() + ")");
        }
    }

    /** Returns a count of open connections */
    public int getConnectionCount() {
        return connections.size();
    }

    /** Returns an unused existing or new connection.  */
    public synchronized Connection getConnection()
            throws Exception {
        if (!initialized) {
            Class c = Class.forName(_driver);
            DriverManager.registerDriver((Driver) c.newInstance());
            initialized = true;
        }
        Connection c = null;
        ConnectionObject co = null;
        boolean badConnection = false;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.get(i);

            // If connection is not in use, test to ensure it's still valid!
            if (!co.inUse) {
                try {
                    badConnection = co.connection.isClosed();
                    if (!badConnection)
                        badConnection = (co.connection.getWarnings() != null);

                } catch (Exception e) {
                    badConnection = true;
                    e.printStackTrace();
                }
                // Connection is bad, remove from pool
                if (badConnection) {
                    connections.removeElementAt(i);
                    System.out.println("ConnectionPoolManager: Remove disconnected DB connection #" + i);
                    continue;
                }

                c = co.connection;
                co.inUse = true;


                break;


            }
        }

        if (c == null) {
            c = createConnection();
            co = new ConnectionObject(c, true);
            connections.addElement(co);


        }
        return c;
    }

    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    public synchronized void freeConnection(Connection c) {
        if (c == null)
            return;

        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.get(i);
            if (c == co.connection) {
                co.inUse = false;


                break;
            }
        }

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.get(i);
            if ((i + 1) > _openConnections && !co.inUse)
                removeConnection(co.connection);

        }
    }

    public void freeConnection(Connection c, PreparedStatement p, ResultSet r) {
        try {
            if (r != null) r.close();
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) r.close();
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, PreparedStatement p) {
        try {
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s) {
        try {
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    public synchronized void removeConnection(Connection c) {
        if (c == null)
            return;

        ConnectionObject co = null;
        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.get(i);
            if (c == co.connection) {
                try {
                    c.close();
                    connections.removeElementAt(i);
                    trace("Removed " + c.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private Connection createConnection()
            throws SQLException {
        Connection con = null;
        
        try {
            if (_user == null)
                _user = "";
            if (_password == null)
                _password = "";

            Properties props = new Properties();
            props.put("user", _user);
            props.put("password", _password);

            con = DriverManager.getConnection(_url, props);
        } catch (Throwable t) {
            throw new SQLException(t.getMessage());
        }
        return con;
    }

    /** Closes all connections and clears out the connection pool */
    public void releaseFreeConnections() {
        System.out.println("ConnectionPoolManager.releaseFreeConnections()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.get(i);
            if (!co.inUse)
                removeConnection(co.connection);
        }
    }

    /** Closes all connections and clears out the connection pool */
    public void finalize() {
        System.out.println("ConnectionPoolManager.finalize()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.get(i);
            try {
                co.connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            co = null;
        }
        connections.removeAllElements();
    }

    private void trace(String s) {
        if (_traceOn)
            System.err.println(s);
    }
    public void freeConnection(Statement s, ResultSet r) {
        try {
            if (r != null) r.close();
            if (s != null) s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void freeConnection(Statement s) {
        try {
            if (s != null) s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isPrinted = false; // 출력 여부를 저장하는 플래그 변수

    public void printAllConnections() {
        if (!isPrinted) {
            Vector<ConnectionObject> connections = getConnectionList(); // 현재 생성된 커넥션 목록을 가져옴

            // 스트림을 사용하여 각 ConnectionObject의 정보를 출력
            connections.stream()
                    .forEach(co -> System.out.println("Connection: " + co.connection ));

            isPrinted = true; // 한번 출력 후 플래그를 true로 변경
        }
    }
}

class ConnectionObject {
    public Connection connection = null;
    public boolean inUse = false;

    public ConnectionObject(Connection c, boolean useFlag) {
        connection = c;
        inUse = useFlag;
    }
}
