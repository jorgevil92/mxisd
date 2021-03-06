/*
 * mxisd - Matrix Identity Server Daemon
 * Copyright (C) 2017 Maxime Dor
 *
 * https://max.kamax.io/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.kamax.mxisd.threepid.notification.phone;

import io.kamax.matrix.ThreePidMedium;
import io.kamax.mxisd.config.threepid.medium.PhoneConfig;
import io.kamax.mxisd.threepid.connector.phone.IPhoneConnector;
import io.kamax.mxisd.threepid.notification.GenericNotificationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneNotificationHandler extends GenericNotificationHandler<IPhoneConnector, IPhoneNotificationGenerator> {

    private PhoneConfig cfg;

    @Autowired
    public PhoneNotificationHandler(PhoneConfig cfg, List<IPhoneConnector> connectors, List<IPhoneNotificationGenerator> generators) {
        this.cfg = cfg;
        process(connectors, generators);
    }

    @Override
    public String getId() {
        return "raw";
    }

    @Override
    public String getMedium() {
        return ThreePidMedium.PhoneNumber.getId();
    }

    @Override
    protected String getConnectorId() {
        return cfg.getConnector();
    }

    @Override
    protected String getGeneratorId() {
        return cfg.getGenerator();
    }

    @Override
    protected void send(IPhoneConnector connector, String recipient, String content) {
        connector.send(recipient, content);
    }

}
