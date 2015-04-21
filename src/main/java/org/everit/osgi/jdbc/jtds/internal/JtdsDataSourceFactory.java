/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.osgi.jdbc.jtds.internal;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.XADataSource;

import net.sourceforge.jtds.jdbc.Driver;
import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.osgi.service.jdbc.DataSourceFactory;

/**
 * {@link DataSourceFactory} implementation for JTDS JDBC Driver.
 */
public class JtdsDataSourceFactory implements DataSourceFactory {

  @Override
  public ConnectionPoolDataSource createConnectionPoolDataSource(final Properties props)
      throws SQLException {
    return createJdbcDataSource(props);
  }

  @Override
  public DataSource createDataSource(final Properties props) throws SQLException {
    return createJdbcDataSource(props);
  }

  @Override
  public java.sql.Driver createDriver(final Properties props) throws SQLException {
    if ((props != null) && (props.size() > 0)) {
      throw new SQLException("JTDS JDBC driver does not support any property to be set");
    }
    Driver driver = new Driver();
    return driver;
  }

  /**
   * Instantiating a {@link JtdsDataSource} and setting its configuration.
   *
   * @param props
   *          The configuration of the datasource.
   * @return The new datasource instance.
   */
  protected JtdsDataSource createJdbcDataSource(final Properties props) {
    JtdsDataSource dataSource = new JtdsDataSource();

    String serverName = props.getProperty(JDBC_SERVER_NAME);
    String portNumber = props.getProperty(JDBC_PORT_NUMBER);
    String databaseName = props.getProperty(JDBC_DATABASE_NAME);
    String user = props.getProperty(JDBC_USER);
    String password = props.getProperty(JDBC_PASSWORD);

    if (databaseName != null) {
      dataSource.setDatabaseName(databaseName);
    }
    if (portNumber != null) {
      Integer portNumberInt = Integer.parseInt(portNumber);
      dataSource.setPortNumber(portNumberInt);
    }
    if (serverName != null) {
      dataSource.setServerName(serverName);
    }
    if (user != null) {
      dataSource.setUser(user);
    }
    if (password != null) {
      dataSource.setPassword(password);
    }
    return dataSource;
  }

  @Override
  public XADataSource createXADataSource(final Properties props) throws SQLException {
    return createJdbcDataSource(props);
  }

}
