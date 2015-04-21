osgi-jdbc-jtds
==============

Appender to JTDS that registers org.osgi.service.jdbc.DataSourceFactory OSGi
service based on the JTDS JDBC driver.

During the call of
[DataSourceFactory.createDataSource(java.util.Properties)][1]
and [DataSourceFactory.createXADataSource(java.util.Properties)][2] functions
the following properties can be defined:

 - [JDBC_SERVER_NAME][3]
 - [JDBC_PORT_NUMBER][4]
 - [JDBC_DATABASE_NAME][5]
 - [JDBC_USER][6]
 - [JDBC_PASSWORD][7]
 - All properties defined in [net.sourceforge.jtds.jdbc.Driver][8] class

In case any of the configuration parameter is duplicated by using
both the constants of [DataSourceFactory][9] and [Driver][8] class the one
defined with the constant of [DataSourceFactory] will be applied.

If you like this bundle, you might be interested in the [osgi-jdbc-dsf][10]
project that:

 - is a configurable component (configuration via configadmin)
 - picks up a DataSourceFactory OSGi service
 - Creates a Driver, DataSource or XADataSource and registers it as an
   OSGi service

## Download

The artifact is available on maven-central.

[1]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#createDataSource(java.util.Properties)
[2]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#createXADataSource(java.util.Properties)
[3]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#JDBC_SERVER_NAME
[4]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#JDBC_PORT_NUMBER
[5]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#JDBC_DATABASE_NAME
[6]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#JDBC_USER
[7]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html#JDBC_PASSWORD
[8]: http://jtds.sourceforge.net/doc/net/sourceforge/jtds/jdbc/Driver.html
[9]: https://osgi.org/javadoc/r4v43/cmpn/org/osgi/service/jdbc/DataSourceFactory.html
[10]: https://github.com/everit-org/osgi-jdbc-dsf
