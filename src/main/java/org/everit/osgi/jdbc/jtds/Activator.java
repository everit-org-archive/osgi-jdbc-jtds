package org.everit.osgi.jdbc.jtds;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import java.util.Dictionary;
import java.util.Hashtable;

import net.sourceforge.jtds.jdbc.Driver;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.jdbc.DataSourceFactory;

/**
 * Registers a {@link DataSourceFactory} for JTDS database driver.
 */
public class Activator implements BundleActivator {

    private ServiceRegistration jtdsDataSourceFactorySR;

    @Override
    public void start(final BundleContext context) throws Exception {
        DataSourceFactory jtdsDataSourceFactory = new JtdsDataSourceFactory();
        Dictionary<String, String> props = new Hashtable<String, String>();

        Bundle pgBundle = FrameworkUtil.getBundle(Driver.class);
        String version = "unknown";
        if (pgBundle != null) {
            version = pgBundle.getVersion().toString();
        }
        props.put(DataSourceFactory.OSGI_JDBC_DRIVER_CLASS, Driver.class.getName());
        props.put(DataSourceFactory.OSGI_JDBC_DRIVER_NAME, "Jtds");
        props.put(DataSourceFactory.OSGI_JDBC_DRIVER_VERSION, version);
        jtdsDataSourceFactorySR = context.registerService(DataSourceFactory.class.getName(),
                jtdsDataSourceFactory, props);
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        if (jtdsDataSourceFactorySR != null) {
            jtdsDataSourceFactorySR.unregister();
        }
    }

}
