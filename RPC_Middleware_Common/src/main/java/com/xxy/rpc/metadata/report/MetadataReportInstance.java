/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xxy.rpc.metadata.report;

import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.constants.CommonConstants;

import com.xxy.rpc.metadata.report.support.Constants;
import com.xxy.rpc.common.URLBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 2019-08-09
 */
public class MetadataReportInstance {

    private static AtomicBoolean init = new AtomicBoolean(false);

    private static MetadataReport metadataReport;

    public static void init(URL metadataReportURL) {
        if (init.get()) {
            return;
        }
        MetadataReportFactory metadataReportFactory = null;
        if (Constants.METADATA_REPORT_KEY.equals(metadataReportURL.getProtocol())) {
            String protocol = metadataReportURL.getParameter(Constants.METADATA_REPORT_KEY, CommonConstants.DEFAULT_DIRECTORY);
            metadataReportURL = URLBuilder.from(metadataReportURL)
                    .setProtocol(protocol)
                    .removeParameter(Constants.METADATA_REPORT_KEY)
                    .build();
        }
        metadataReport = metadataReportFactory.getMetadataReport(metadataReportURL);
        init.set(true);
    }

    public static MetadataReport getMetadataReport() {
        return getMetadataReport(false);
    }

    public static MetadataReport getMetadataReport(boolean checked) {
        if (checked) {
            checkInit();
        }
        return metadataReport;
    }

    private static void checkInit() {
        if (!init.get()) {
            throw new IllegalStateException("the metadata report was not inited.");
        }
    }
}
