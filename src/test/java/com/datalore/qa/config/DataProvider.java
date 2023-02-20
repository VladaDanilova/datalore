package com.datalore.qa.config;

import org.aeonbits.owner.ConfigCache;

/**
 * Provides access to test data and properties.
 */
public class DataProvider {
    public static TestDataAndProperties get() {
        // http://owner.aeonbits.org/docs/singleton/
        return ConfigCache.getOrCreate(TestDataAndProperties.class);
    }
}