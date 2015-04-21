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

import java.util.Dictionary;
import java.util.Hashtable;

import net.sourceforge.jtds.jdbc.Driver;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.jdbc.DataSourceFactory;

/**
 * Registers a {@link DataSourceFactory} for JTDS database driver.
 */
public class Activator implements BundleActivator {

  private ServiceRegistration<DataSourceFactory> jtdsDataSourceFactorySR;

  @Override
  public void start(final BundleContext context) throws Exception {
    DataSourceFactory jtdsDataSourceFactory = new JtdsDataSourceFactory();
    Dictionary<String, String> props = new Hashtable<String, String>();

    props.put(DataSourceFactory.OSGI_JDBC_DRIVER_CLASS, Driver.class.getName());
    props.put(DataSourceFactory.OSGI_JDBC_DRIVER_NAME, "Jtds");
    props.put(DataSourceFactory.OSGI_JDBC_DRIVER_VERSION, Driver.getVersion());
    jtdsDataSourceFactorySR = context.registerService(DataSourceFactory.class,
        jtdsDataSourceFactory, props);
  }

  @Override
  public void stop(final BundleContext context) throws Exception {
    if (jtdsDataSourceFactorySR != null) {
      jtdsDataSourceFactorySR.unregister();
    }
  }

}
